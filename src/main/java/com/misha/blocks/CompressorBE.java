package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CompressorBE extends BlockEntity {

    short active = 0;
    public static int capacity = 50000;
    public int usage = 20;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage = 20;
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public static short baseTime = 1000;
    short time = baseTime;
    short counter = 0;

    public CompressorBE(BlockPos pos, BlockState state) {
        //RecipeType.register("compressor")
        super(Registration.COMPRESSOR_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        energy.invalidate();
    }


    short recipe = 0;
    short oldrecipe = 0;

    public void tickServer(BlockState state) {
        BlockPos below = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());

        BlockState blockState = level.getBlockState(worldPosition);

        if (level.getBlockState(below).getBlock() instanceof LavaVent) {
            this.active = (short) ((LavaVentBE) level.getBlockEntity(below)).active;
        } else {
            this.active = 0;
        }
        ItemStack input = itemHandler.getStackInSlot(0);
        ItemStack output = itemHandler.getStackInSlot(1);


        /*get recipes:
        1: Diamond
        2: Netherrite
        3: PACKED ICE
        4: BLUE ICE
        5: ENDERPEARL
        6: GRAVEL
         */
        if (input.getItem() == Blocks.COAL_BLOCK.asItem() && input.getCount() >= 10) {
            recipe = 1;
            time = (short) (baseTime * 3);
        } else if (input.getItem() == Items.NETHERITE_SCRAP && input.getCount() >= 6) {
            recipe = 2;
            time = baseTime;
        } else if (input.getItem() == Blocks.ICE.asItem() && input.getCount() >= 4) {
            recipe = 3;
            time = (short) (baseTime / 4);
        } else if (input.getItem() == Blocks.PACKED_ICE.asItem() && input.getCount() >= 4) {
            recipe = 4;
            time = (short) (baseTime / 2);
        } else if (input.getItem() == Blocks.END_STONE.asItem() && input.getCount() >= 32) {
            recipe = 5;
            time = baseTime;
        } else if (input.getItem() == Blocks.GRAVEL.asItem() && input.getCount() >= 8) {
            recipe = 6;
            time = (short) (baseTime / 2);
        } else {
            recipe = 0;
            counter = 0;
            time = baseTime;
        }

        //check if recipe has been changed
        if (recipe != oldrecipe) {
            counter = 0;
        }
        oldrecipe = recipe;


        if (this.active > 0 && hasEnoughPowerToWork() && recipe > 0) {
            if (counter >= time / active) {
                if (recipe == 1) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.DIAMOND, 1));
                        counter = 0;
                    }
                    if (output.getItem() == Items.DIAMOND.asItem() && output.getCount() <= 64 - 1) {
                        ItemStack stack = new ItemStack(Items.DIAMOND, output.getCount() + 1);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 10, false);
                        counter = 0;
                    }
                }
                if (recipe == 2) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.NETHERITE_INGOT, 2));
                        counter = 0;
                    }
                    if (output.getItem() == Items.PACKED_ICE.asItem() && output.getCount() <= 64 - 2) {
                        ItemStack stack = new ItemStack(Items.NETHERITE_INGOT, output.getCount() + 2);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 6, false);
                        counter = 0;
                    }
                }
                if (recipe == 3) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.PACKED_ICE, 1));
                        counter = 0;
                    }
                    if (output.getItem() == Items.PACKED_ICE.asItem() && output.getCount() <= 64 - 1) {
                        ItemStack stack = new ItemStack(Items.PACKED_ICE, output.getCount() + 1);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 4, false);
                        counter = 0;
                    }
                }
                if (recipe == 4) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.BLUE_ICE, 1));
                        counter = 0;
                    }
                    if (output.getItem() == Items.BLUE_ICE.asItem() && output.getCount() <= 64 - 1) {
                        ItemStack stack = new ItemStack(Items.BLUE_ICE, output.getCount() + 1);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 4, false);
                        counter = 0;
                    }
                }
                if (recipe == 5) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.ENDER_PEARL, 1));
                        counter = 0;
                    }
                    if (output.getItem() == Items.ENDER_PEARL.asItem() && output.getCount() <= 16 - 1) {
                        ItemStack stack = new ItemStack(Items.ENDER_PEARL, output.getCount() + 1);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 32, false);
                        counter = 0;
                    }
                }

                if (recipe == 6) {
                    if (output.isEmpty()) {
                        itemHandler.setStackInSlot(1, new ItemStack(Items.GRAVEL, 1));
                        counter = 0;
                    }
                    if (output.getItem() == Items.GRAVEL.asItem() && output.getCount() <= 64 - 1) {
                        ItemStack stack = new ItemStack(Items.GRAVEL, output.getCount() + 1);
                        itemHandler.setStackInSlot(1, stack);
                        itemHandler.extractItem(0, 8, false);
                        counter = 0;
                    }
                }


            } else {
                counter++;
            }
        }

    }


    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        tag.putInt("time", time);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        tag.putInt("time", time);
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
                if (slot == 1) {
                    return false;
                }
                if (slot == 0) {
                    return true;
                } else {
                    return true;
                }
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