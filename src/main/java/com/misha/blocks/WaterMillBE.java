package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class WaterMillBE extends BlockEntity {
    public static int capacity = 50000;
    int transfer=200;

    static int generate=5;

    private final CustomEnergyStorage energyStorage = createEnergy();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int counter;
    int cactive = 0;
    int cgenerate=0;

    //how much flowing water is around it
    short flows=0;

    public WaterMillBE(BlockPos pos, BlockState state) {
        super(Registration.WATERMILL_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        energy.invalidate();
    }


    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        return  ClientboundBlockEntityDataPacket.create(this);
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

    public void tickServer(BlockState state) {
        BlockPos side1 = new BlockPos(this.getBlockPos().getX()+1,this.getBlockPos().getY(),this.getBlockPos().getZ());
        BlockPos side2 = new BlockPos(this.getBlockPos().getX()-1,this.getBlockPos().getY(),this.getBlockPos().getZ());
        BlockPos side3 = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY(),this.getBlockPos().getZ()-1);
        BlockPos side4 = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY(),this.getBlockPos().getZ()+1);

        BlockPos top = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY()+1,this.getBlockPos().getZ());

        int flowing=0;
        level.sendBlockUpdated(top, getBlockState(),getBlockState(), Block.UPDATE_CLIENTS);

        if( level.getFluidState(side1).getType() instanceof WaterFluid && !level.getFluidState(side1).isSource()){
            flowing+=1;
        }
        if(level.getFluidState(side2).getType() instanceof WaterFluid && !level.getFluidState(side2).isSource()){
            flowing+=1;
        }
        if(level.getFluidState(side3).getType() instanceof WaterFluid && !level.getFluidState(side3).isSource()){
            flowing+=1;
        }
        if(level.getFluidState(side4).getType() instanceof WaterFluid && !level.getFluidState(side4).isSource()){
            flowing+=1;
        }

        if(flows != flowing){
            flows =(short) flowing;
        }

        if(flows>0){
            int gen = this.generate*flows;
            if(energyStorage.getEnergyStored()<capacity-(gen-1)){
                energyStorage.addEnergy(gen);
            }
        }


        sendOutPower();
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity te = level.getBlockEntity(worldPosition.relative(direction));
                if (te != null) {
                    boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(),transfer), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }

        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("energy", energyStorage.serializeNBT());

        tag.putInt("counter", counter);

    }



    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(capacity, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}