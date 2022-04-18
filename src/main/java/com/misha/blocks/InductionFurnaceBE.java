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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
  
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class InductionFurnaceBE extends BlockEntity {

    int active = 0;
    public static int capacity = 50000;
    public int usage = 20;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage = 40;
    public static int baseTime = 500;
    int products = 12;

    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public int counter = 0;
    public int ccounter = 0;
    public int cactive = 0;

    public InductionFurnaceBE(BlockPos pos, BlockState state) {
        //RecipeType.register("inductionfurnace")
        super(Registration.INDUCTIONFURNACE_BE.get(), pos, state);
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


        if (this.active > 0) {
            this.usage = baseUsage * this.active;
            if (hasEnoughPowerToWork()) {


                ItemStack stack = itemHandler.getStackInSlot(0);
                ItemStack output = itemHandler.getStackInSlot(1);

                if (!stack.isEmpty() && stack.getItem() == Blocks.RAW_IRON_BLOCK.asItem()
                        && (output.isEmpty() || output.getItem() == Items.IRON_INGOT.asItem())
                        && output.getCount() < 64 - products) {
                    energyStorage.consumeEnergy(usage);
                    if (counter >= baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack iron = new ItemStack(Items.IRON_INGOT.asItem(), products);
                            itemHandler.setStackInSlot(1, iron);
                            itemHandler.extractItem(0, 1, false);
                        } else {
                            ItemStack iron = output;
                            iron.setCount(output.getCount() + products);
                            itemHandler.setStackInSlot(1, iron);
                            itemHandler.extractItem(0, 1, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }


                    setChanged();
                } else {
                    if (!stack.isEmpty() && stack.getItem() == Blocks.RAW_GOLD_BLOCK.asItem()
                            && (output.isEmpty() || output.getItem() == Items.GOLD_INGOT.asItem())
                            && output.getCount() < 64 - products) {
                        energyStorage.consumeEnergy(usage);
                        if (counter >= baseTime / active) {
                            if (output.isEmpty()) {
                                ItemStack iron = new ItemStack(Items.GOLD_INGOT.asItem(), products);
                                itemHandler.setStackInSlot(1, iron);
                                itemHandler.extractItem(0, 1, false);
                            } else {
                                ItemStack iron = output;
                                iron.setCount(output.getCount() + products);
                                itemHandler.setStackInSlot(1, iron);
                                itemHandler.extractItem(0, 1, false);
                            }
                            counter = 0;
                        } else {
                            counter++;
                        }


                        setChanged();
                    } else {
                        if (!stack.isEmpty() && stack.getItem() == Blocks.RAW_COPPER_BLOCK.asItem()
                                && (output.isEmpty() || output.getItem() == Items.COPPER_INGOT.asItem())
                                && output.getCount() < 64 - products) {
                            energyStorage.consumeEnergy(usage);
                            if (counter >= baseTime / active) {
                                if (output.isEmpty()) {
                                    ItemStack iron = new ItemStack(Items.COPPER_INGOT.asItem(), products);
                                    itemHandler.setStackInSlot(1, iron);
                                    itemHandler.extractItem(0, 1, false);
                                } else {
                                    ItemStack iron = output;
                                    iron.setCount(output.getCount() + products);
                                    itemHandler.setStackInSlot(1, iron);
                                    itemHandler.extractItem(0, 1, false);
                                }
                                counter = 0;
                            } else {
                                counter++;
                            }


                            setChanged();
                        } else {
                            counter = 0;
                        }
                    }
                }
            }
        }

        level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, active > 0),
                Block.UPDATE_ALL);


    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }

        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public   void saveAdditional(CompoundTag tag) {
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
                    return (stack.getItem() == Blocks.RAW_IRON_BLOCK.asItem() || stack.getItem() == Blocks.RAW_COPPER_BLOCK.asItem() || stack.getItem() == Blocks.RAW_GOLD_BLOCK.asItem());
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
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(),Block.UPDATE_CLIENTS);
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