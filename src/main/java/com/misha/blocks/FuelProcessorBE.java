package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FuelProcessorBE extends BlockEntity {

    public static int capacity = 25000;
    public int usage = 5;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage = 25;
    static int basetime = 60;
    int time = basetime;

    //private final TileFluidHandler fluidHandler= createFluid();
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public int counter = 0;
    public short counter2=0;
    static int time2=200;

    boolean built= false;
    short fuel=0;
    static int reqFuel=2000;
    public static short fuelCap=8000;

    public FuelProcessorBE(BlockPos pos, BlockState state) {
        super(Registration.FUELPROCESSOR_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        energy.invalidate();
    }

    public static boolean isValid(ItemStack i) {
        return i.isEdible();

    }




    public void tickServer(BlockState state) {
        ItemStack input = itemHandler.getStackInSlot(0);
        ItemStack output=itemHandler.getStackInSlot(1);

        BlockPos above = new BlockPos(worldPosition.getX(), worldPosition.getY()+1, worldPosition.getZ());
        BlockPos above2 = new BlockPos(worldPosition.getX(), worldPosition.getY()+2, worldPosition.getZ());
        if(level.getBlockState(above).getBlock() == Registration.FUELCOMP.get() && level.getBlockState(above2).getBlock() == Registration.FUELCOMP.get() ){
            built=true;
        }else{
            built=false;
        }
        //uses this to know if it should consume energy in the tick
boolean usePower=false;
        if(built){
            if(hasEnoughPowerToWork()){
                if(isValid(input) && input.getItem().getFoodProperties() != null){
                    if(counter>time) {
                        int amount = input.getItem().getFoodProperties().getNutrition() * 50;
                        if (fuel < fuelCap - amount) {
                            fuel += amount;
                            itemHandler.extractItem(0, 1, false);
                            counter=0;
                        }
                    }else{
                        usePower=true;
                        counter++;
                    }
                }else{
                    counter=0;
                }

                if(fuel>=reqFuel){
                    if(counter2>=time2) {
                        if (output.isEmpty()) {
                            itemHandler.setStackInSlot(1, new ItemStack(Registration.FUELCELL.get(), 1));
                            fuel -= reqFuel;
                            counter2 = 0;
                        }
                        if (output.getItem() == Registration.FUELCELL.get().asItem() && output.getCount() <= 64 - 1) {
                            ItemStack stack = new ItemStack(Registration.FUELCELL.get(), output.getCount() + 1);
                            itemHandler.setStackInSlot(1, stack);
                            fuel -= reqFuel;
                            counter2 = 0;
                        }
                    }
                    else{
                        usePower=true;
                        counter2++;
                    }
                }else{
                    counter2=0;
                }
            }
        }else{
            counter2=0;
            counter=0;
        }


        if(usePower){
            energyStorage.consumeEnergy(usage);
        }


    }


    public int nextEmpty() {
        for (int i = 0; i < 9; i++) {
            if (itemHandler.getStackInSlot(i + 1).isEmpty()) {
                return i + 1;
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
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
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
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("counter", counter);

    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {

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