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

public class CompressorBE extends BlockEntity {

    int active = 0;
   public static int capacity = 50000;
    public int usage = 20;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage=20;
    int products=11;
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


public static int baseTime=1000;
int time = baseTime;
int ctime=time;
    public int counter= 0;
    public int ccounter=0;
    public int cactive=0;

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

    public void tickServer(BlockState state) {
        BlockPos below = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());

        BlockState blockState = level.getBlockState(worldPosition);

        if (level.getBlockState(below).getBlock() instanceof LavaVent) {
            this.active = ((LavaVentBE) level.getBlockEntity(below)).active;
        } else {
            this.active = 0;
        }


        if (this.active > 0) {
            this.usage= baseUsage*this.active;
            if (hasEnoughPowerToWork()) {


                ItemStack stack = itemHandler.getStackInSlot(0);
                ItemStack output = itemHandler.getStackInSlot(1);

                if (!stack.isEmpty() && stack.getItem() == Blocks.COAL_BLOCK.asItem()
                        && stack.getCount()>=16
                        && (output.isEmpty() || output.getItem() == Items.DIAMOND.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    //how long the recipe takes
                    time=baseTime*4;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Items.DIAMOND.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 16, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 16, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }

                //Obsidian
                if (!stack.isEmpty() && stack.getItem() == Blocks.OBSIDIAN.asItem()
                        && stack.getCount()>=4
                        && (output.isEmpty() || output.getItem() == Blocks.CRYING_OBSIDIAN.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Blocks.CRYING_OBSIDIAN.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 4, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 4, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }

                //Ender Pearl
                if (!stack.isEmpty() && stack.getItem() == Blocks.END_STONE.asItem()
                        && stack.getCount()>=32
                        && (output.isEmpty() || output.getItem() == Items.ENDER_PEARL.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Items.ENDER_PEARL.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 32, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 32, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }


                // Ice
                if (!stack.isEmpty() && stack.getItem() == Blocks.ICE.asItem()
                        && stack.getCount()>=8
                        && (output.isEmpty() || output.getItem() == Blocks.PACKED_ICE.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime/2;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Blocks.PACKED_ICE.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 8, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 8, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }

                // Blue Ice
                if (!stack.isEmpty() && stack.getItem() == Blocks.PACKED_ICE.asItem()
                        && stack.getCount()>=8
                        && (output.isEmpty() || output.getItem() == Blocks.BLUE_ICE.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime/2;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Blocks.BLUE_ICE.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 8, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 8, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }


                // Dirt -> Gravel
                if (!stack.isEmpty() && stack.getItem() == Blocks.DIRT.asItem()
                        && stack.getCount()>=4
                        && (output.isEmpty() || output.getItem() == Blocks.GRAVEL.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime/2;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Blocks.GRAVEL.asItem(), 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 4, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 1);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 4, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }




                //Netherite
                if (!stack.isEmpty() && stack.getItem() == Items.NETHERITE_SCRAP.asItem()
                        && stack.getCount()>=7
                        && (output.isEmpty() || output.getItem() == Items.NETHERITE_INGOT.asItem())
                        && output.getCount() <= 63) {
                    energyStorage.consumeEnergy(usage);
                    time=baseTime*3;
                    if (counter >= time / active) {
                        if (output.isEmpty()) {
                            ItemStack item = new ItemStack(Items.NETHERITE_INGOT.asItem(), 2);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 7, false);
                        } else {
                            ItemStack item = output;
                            item.setCount(output.getCount() + 2);
                            itemHandler.setStackInSlot(1, item);
                            itemHandler.extractItem(0, 7, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }



            }


            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    Block.UPDATE_ALL);
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false),
                    Block.UPDATE_ALL);
        }

    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        tag.putInt("time",time);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
ctime=tag.getInt("time");
        ccounter=tag.getInt("counter");
        cactive=tag.getInt("active");
    }
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        tag.putInt("time",time);
        return tag;
    }
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
        ctime=tag.getInt("time");
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.get("energy"));

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