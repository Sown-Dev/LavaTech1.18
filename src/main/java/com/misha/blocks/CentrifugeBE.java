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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class CentrifugeBE extends BlockEntity implements IFluidTank {

    int active = 0;
    public static int capacity = 50000;
    public int usage = 20;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage = 30;
    static int basetime = 1500;

    int time = basetime;

    //private final TileFluidHandler fluidHandler= createFluid();
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public int counter = 0;
    public int ccounter = 0;
    public int cactive = 0;
    public int cfilled = 0;

    public CentrifugeBE(BlockPos pos, BlockState state) {
        super(Registration.CENTRIFUGE_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        energy.invalidate();
    }

    public void tickServer(BlockState state) {
        BlockPos below = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());

        BlockState blockState = level.getBlockState(worldPosition);

        if (level.getBlockState(below).getBlock() instanceof LavaVent) {
            this.active = ((LavaVentBE) level.getBlockEntity(below)).active;
        } else {
            this.active = 0;
        }
        if (active > 0) {
            this.usage = baseUsage * this.active;

            if (this.filled >= 500 && isEmpty()) {
                if (hasEnoughPowerToWork()) {
                    if (counter >= basetime / active) {
                        int random = (int) ((Math.random() * (double) 100) + 1);
                        System.out.println(random);
                        //the numbers indicate the chances of recieving an item
                        if (46 >= random && random > 27) {
                            ItemStack newItem = new ItemStack(Items.RAW_IRON.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (54 >= random && random > 41) {
                            ItemStack newItem = new ItemStack(Items.RAW_GOLD.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (60 > random && random > 52) {
                            ItemStack newItem = new ItemStack(Items.IRON_NUGGET.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            int ran = (int) ((Math.random() * 3.0) + 1.5);
                            newItem.setCount(num + ran);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (71 >= random && random > 60) {
                            ItemStack newItem = new ItemStack(Blocks.SAND.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (76 >= random && random > 71) {
                            ItemStack newItem = new ItemStack(Blocks.CALCITE.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (80 >= random && random > 76) {
                            ItemStack newItem = new ItemStack(Blocks.COBBLESTONE.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (89 >= random && random > 80) {
                            ItemStack newItem = new ItemStack(Items.QUARTZ.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        if (100 >= random && random > 89) {
                            ItemStack newItem = new ItemStack(Blocks.OBSIDIAN.asItem(), 1);
                            int num = itemHandler.getStackInSlot(findNext(newItem)).getCount();
                            newItem.setCount(num + 1);
                            itemHandler.setStackInSlot(findNext(newItem), newItem);
                        }
                        counter = 0;
                        filled -= 500;
                    } else {
                        counter++;
                        this.energyStorage.consumeEnergy(usage);
                    }
                }
            } else {
                counter = 0;
            }
        }

        level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, active > 0),
                Block.UPDATE_ALL);

    }

    public int findNext(ItemStack stack) {
        for (int i = 0; i < 9; i++) {
            if (itemHandler.getStackInSlot(i).getItem() == stack.getItem()) {
                return i;
            }
        }
        return nextEmpty();
    }

    public int nextEmpty() {
        for (int i = 0; i < 9; i++) {
            if (itemHandler.getStackInSlot(i).isEmpty()) {
                return i;
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
        tag.putInt("active", active);
        tag.putInt("filled", filled);
        return new ClientboundBlockEntityDataPacket(worldPosition, 1, tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
        cfilled = tag.getInt("filled");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        tag.putInt("filled", filled);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
        cfilled = tag.getInt("filled");
    }


    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.get("energy"));
        filled = tag.getInt("filled");
        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public   void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("filled", filled);
        tag.putInt("counter", counter);
         
    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(9) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return false;

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

    private TileFluidHandler createFluid() {
        return new TileFluidHandler(null, worldPosition, getBlockState()) {

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

    int filled = 0;
    int fluidcap = 8000;

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return new FluidStack(Fluids.LAVA.getFlowing(), filled);
    }

    @Override
    public int getFluidAmount() {
        return filled;
    }

    @Override
    public int getCapacity() {
        return fluidcap;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return stack.getFluid() == Fluids.LAVA.getFlowing();
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        if (isFluidValid(resource) && resource.getAmount() < (getCapacity() - this.filled)) {
            this.filled += resource.getAmount();
            return resource.getAmount();
        } else {
            int ret = getCapacity() - this.filled;
            this.filled = getCapacity();
            return ret;
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        if (this.getFluidAmount() > maxDrain) {
            this.filled -= maxDrain;
            return new FluidStack(Fluids.LAVA.getFlowing(), maxDrain);
        } else {
            int ret = getFluidAmount();
            filled = 0;
            return new FluidStack(Fluids.LAVA.getFlowing(), ret);
        }
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if (this.getFluidAmount() > resource.getAmount()) {
            this.filled -= resource.getAmount();
            return new FluidStack(Fluids.LAVA.getFlowing(), resource.getAmount());
        } else {
            int ret = getFluidAmount();
            filled = 0;
            return new FluidStack(Fluids.LAVA.getFlowing(), ret);
        }
    }
}