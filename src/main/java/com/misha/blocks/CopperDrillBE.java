package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class CopperDrillBE extends BlockEntity {

    public static int capacity = 40000;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage = 20;
    int usage = baseUsage;

    static int basetime = 140;
    int time = basetime;

    int depth = 1;
    //short cdepth=depth;

    //private final TileFluidHandler fluidHandler= createFluid();
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public int counter = 0;
    public int ccounter = 0;

    int errorCode = -1;
    /*
    -1 : Off
    0 : Working
    1 : Bedrock
    2 : Deepslate
    3 : Void
    4 : No Power
    5 : No Inventory Space
    6 :

     */

    public CopperDrillBE(BlockPos pos, BlockState state) {
        super(Registration.COPPERDRILL_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        energy.invalidate();
    }

    public static boolean isValid(ItemStack i) {
        return true;

    }


    public void tickServer(BlockState state) {

        BlockPos drillPos = new BlockPos(worldPosition.getX(), worldPosition.getY() - depth, worldPosition.getZ());
        if ((level.getBlockState(drillPos).isAir()
                || level.getFluidState(drillPos).getType() instanceof LavaFluid
                || level.getFluidState(drillPos).getType() instanceof WaterFluid)&& drillPos.getY()>-65) {
            depth++;
            drillPos = new BlockPos(worldPosition.getX(), worldPosition.getY() + depth, worldPosition.getZ());
        }
        ItemTags.create(new ResourceLocation("forge/ingots/iron"));

        boolean canWork = true;

        //errorcode calculation:
        Block nextMine = level.getBlockState(drillPos).getBlock();
        if(nextMine == Blocks.BEDROCK){
            errorCode=1;
        }
        else if(nextMine == Blocks.DEEPSLATE){
            errorCode=2;
        }
        else if(drillPos.getY()<=-64){
            errorCode=3;
        }
        else if(!hasEnoughPowerToWork()){
            errorCode=4;
        }
        else if(!isEmpty()){
            errorCode=5;
        }
        else{
            errorCode=0;
        }



        if (hasEnoughPowerToWork() && errorCode==0) {
            energyStorage.consumeEnergy(usage);

            if (counter >= basetime) {
                if (isEmpty()) {
                    ItemStack newItem = new ItemStack(level.getBlockState(drillPos).getBlock(), 1);
                        int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                        newItem.setCount(num + newItem.getCount());
                        itemHandler.setStackInSlot(findNext(newItem), newItem);
                        level.destroyBlock(drillPos, false);

                    counter = 0;
                }
            } else {
                counter++;
            }

        }


    }

    public int findNext(ItemStack stack) {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (itemHandler.getStackInSlot(i).getItem() == stack.getItem()) {
                return i ;
            }
        }
        return nextEmpty();
    }

    public int nextEmpty() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (itemHandler.getStackInSlot(i).isEmpty()) {
                return i ;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return nextEmpty() != -1;
    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("depth",depth);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        ccounter = tag.getInt("counter");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        //tag.putShort("depth",depth);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);

    }


    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));

        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }
        counter = tag.getInt("counter");
        depth = tag.getShort("depth");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("counter", counter);
        //tag.putShort("depth",depth);

    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(15) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return isValid(stack);

            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if ((stack.isEmpty())) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }


    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(capacity, transfer) {
            @Override
            protected void onEnergyChanged() {

                boolean newHasPower = hasEnoughPowerToWork();
                if (newHasPower != hasPower) {
                    hasPower = newHasPower;
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
                }
                setChanged();
            }
        };
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

}