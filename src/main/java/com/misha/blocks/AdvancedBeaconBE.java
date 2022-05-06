package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdvancedBeaconBE extends BlockEntity {
    public static int capacity = 100000;
    int transfer=400;
    public static int baseUsage=80;
    int usage=baseUsage;
    short effect=0;
    short effect2=0;



    private final CustomEnergyStorage energyStorage = createEnergy();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int counter;

    public AdvancedBeaconBE(BlockPos pos, BlockState state) {
        super(Registration.ADVANCEDBEACON_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        energy.invalidate();
    }
    int count=0;
    public void tickServer(BlockState state) {
        if(hasEnoughPowerToWork()) {
            energyStorage.consumeEnergy(usage);
            count++;
            if(count>=10) {
                count=0;
                Player pl = level.getNearestPlayer((double) worldPosition.getX(), (double) worldPosition.getY(), (double) worldPosition.getZ(), 10.0, EntitySelector.NO_SPECTATORS);
                if (pl instanceof Player && pl != null) {
                    if(effect==0) {
                        pl.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 219, 2, true, true));
                    }
                    if(effect==1) {
                        pl.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 219, 2, true, true));
                    }
                    if(effect==2) {
                        pl.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 219, 2, true, true));
                    }
                    if(effect==3) {
                        pl.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 219, 2, true, true));
                    }
                    if(effect==4) {
                        pl.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 219, 2, true, true));
                    }
                    if(effect==5) {
                        pl.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 219, 2, true, true));
                    }
                    if(effect==6) {
                        pl.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 219, 2, true, true));
                    }
                    if(effect==7) {
                        pl.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 219, 2, true, true));
                    }
                }
            }
        }
    }
    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }





    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }
        effect =tag.getShort("effect");
        super.load(tag);
    }

    @Override
    public   void saveAdditional(CompoundTag tag) {
        tag.put("energy", energyStorage.serializeNBT());

        tag.putShort("effect",effect);

    }


    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(capacity, transfer) {
            @Override
            protected void onEnergyChanged() {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
                setChanged();
            }

        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}