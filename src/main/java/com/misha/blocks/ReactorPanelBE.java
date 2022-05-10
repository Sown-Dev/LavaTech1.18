package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
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

public class ReactorPanelBE extends BlockEntity {
    int energy = 0;
    public static int capacity = ReactorPortBE.capacity;
    int baseGenerate = 10000;
    boolean built = false;
    short heat = 300;
    static int heatcap = 5000;
    short fuel = 0;
    public static int fuelcap = 800;
    private final ItemStackHandler itemHandler = createHandler();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);


    int carbon = 0;
    static int carbUsage = 5;
    public static int carbonCap = 10000;

    int counter = 0;

    public ReactorPanelBE(BlockPos pos, BlockState state) {
        super(Registration.REACTORPANEL_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
    }

    public static boolean isValid(ItemStack i) {
        return true;

    }

    public void messageBuilt() {
        Player pl = level.getNearestPlayer((double) worldPosition.getX(), (double) worldPosition.getY(), (double) worldPosition.getZ(), 30.0, EntitySelector.NO_SPECTATORS);
        pl.displayClientMessage(new TranslatableComponent("Reactor is not built"), true);
    }



    public void tickServer(BlockState state) {
        ItemStack input = itemHandler.getStackInSlot(0);
        ItemStack coal = itemHandler.getStackInSlot(1);


        //check if any surrounding blocks are cores, then see if they are built
        BlockPos side1 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY(), this.getBlockPos().getZ());
        BlockPos side2 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY(), this.getBlockPos().getZ());
        BlockPos side3 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ() - 1);
        BlockPos side4 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ() + 1);
        BlockPos top = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
        BlockPos bottom = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());

        BlockPos core = worldPosition;
        BlockPos portpos = worldPosition;
        int corenum = 0;
        if (level.getBlockState(side1).getBlock() == Registration.REACTORCORE.get()) {
            core = side1;
            corenum++;
        }
        if (level.getBlockState(side2).getBlock() == Registration.REACTORCORE.get()) {
            core = side2;
            corenum++;
        }
        if (level.getBlockState(side3).getBlock() == Registration.REACTORCORE.get()) {
            core = side3;
            corenum++;
        }
        if (level.getBlockState(side4).getBlock() == Registration.REACTORCORE.get()) {
            core = side4;
            corenum++;
        }
        if (level.getBlockState(top).getBlock() == Registration.REACTORCORE.get()) {
            core = top;
            corenum++;
        }
        if (level.getBlockState(bottom).getBlock() == Registration.REACTORCORE.get()) {
            core = bottom;
            corenum++;
        }

        if (corenum == 1) {
            if (((ReactorCoreBE) level.getBlockEntity(core)).built) {
                built = true;
                portpos = ((ReactorCoreBE) level.getBlockEntity(core)).portpos;
                //System.out.println("you built it!");
            } else {
                built = false;

            }
        } else {
            built = false;
        }


        if (built && level.getBlockEntity(portpos) instanceof ReactorPortBE) {
            if (!coal.isEmpty()) {
                if (coal.getItem() == Items.COAL) {
                    if (carbon <= carbonCap - 100) {
                        carbon += 100;
                        itemHandler.extractItem(1, 1, false);
                    }
                }
                if (coal.getItem() == Blocks.COAL_BLOCK.asItem()) {
                    if (carbon <= carbonCap - 900) {
                        carbon += 900;
                        itemHandler.extractItem(1, 1, false);
                    }
                }
            }

            if (input.getItem() == Registration.FUELCELL.get() && fuel <= 0) {
                itemHandler.extractItem(0, 1, false);
                fuel += fuelcap;
            }
            if (fuel > 0) {

                if (carbon >= carbUsage) {
                    carbon -= carbUsage;
                    fuel -= 2;
                } else {
                    fuel -= 3;
                }
                //creates heat
                if (heat <= heatcap - 6) {
                    heat += 6;
                } else if (heat < heatcap) {
                    heat = (short) heatcap;
                }
            }


            if (fuel < 0) {
                fuel = 0;
            }
            if (heat >= 1400) {
                int generate = (int) (((double) heat / (double) heatcap) * baseGenerate);
                if (((ReactorPortBE) level.getBlockEntity(portpos)).getPortEnergy() < capacity - generate) {
                    ((ReactorPortBE) level.getBlockEntity(portpos)).addPortEnergy(generate);
                } else if (((ReactorPortBE) level.getBlockEntity(portpos)).getPortEnergy() < capacity) {
                    ((ReactorPortBE) level.getBlockEntity(portpos)).setPortEnergy(capacity);
                }
            }
            if (heat > 300 && fuel == 0) {
                heat -= 8;
                /*if(counter==10) {
                    heat -= 2;
                    counter=0;
                }else{
                    counter++;
                }*/
            }
            if (level.getBlockEntity(portpos) instanceof ReactorPortBE) {
                energy = ((ReactorPortBE) level.getBlockEntity(portpos)).getPortEnergy();
            }
        }

        level.setBlock(worldPosition, state.setValue(BlockStateProperties.POWERED, heat>1400  ),
                Block.UPDATE_ALL);
    }


    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
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
        carbon = tag.getInt("carbon");
        heat = tag.getShort("heat");
        fuel= tag.getShort("fuel");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.putInt("carbon", carbon);
        tag.putShort("heat", heat);
        tag.putShort("fuel",fuel);


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


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }


}