package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AdvancedBeaconBE extends BlockEntity {
    public static int capacity = 500000;
    int transfer = 1000;
    public static int baseUsage = 500;
    int usage = baseUsage;

    short effect = 0;
    short effect2 = 0;
    short effect3 = 0;
    //indicates strength of beacon effect
    int power = 2;

    //range of beacon
    int range = 64;


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

    int count = 0;
    boolean built=false;
    public void tickServer(BlockState state) {
        built=false;
        BlockPos[] posList = new BlockPos[9];
        posList[0] = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        posList[1] = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        posList[2] = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);
        posList[3] = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        posList[4] = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        posList[5] = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);
        posList[6] = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        posList[7] = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        posList[8] = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);

        int baseblocks=0;
        for(BlockPos position: posList){
            if(level.getBlockState(position).getBlock()==Registration.BEACONCOMP.get()){
                baseblocks++;
            }
        }
        if(baseblocks==9){
            built=true;
        }



        if(built) {
            if (hasEnoughPowerToWork()) {
                energyStorage.consumeEnergy(usage);
                count++;
                if (count >= 10) {
                    count = 0;
                    AABB bounds = new AABB(worldPosition.getX() - range, worldPosition.getY() - range, worldPosition.getZ() - range,
                            worldPosition.getX() + range, worldPosition.getY() + range, worldPosition.getZ() + range);

                    List<Player> playerList = level.getNearbyPlayers(TargetingConditions.forNonCombat(), null, bounds);

                    for (Player pl : playerList) {
                        if (pl instanceof Player && pl != null) {
                            if (getEffectFromNumber(effect2) != null && getEffectFromNumber(effect) != null && getEffectFromNumber(effect3) != null) {
                                pl.addEffect(new MobEffectInstance(getEffectFromNumber(effect), 219, power, true, true));
                                pl.addEffect(new MobEffectInstance(getEffectFromNumber(effect2), 219, power, true, true));
                                pl.addEffect(new MobEffectInstance(getEffectFromNumber(effect3), 219, power, true, true));
                            }


                        }
                    }
                }
            }
        }
    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public static MobEffect getEffectFromNumber(short effect2) {
        if (effect2 == 0) {
            return MobEffects.MOVEMENT_SPEED;
        }
        if (effect2 == 1) {
            return MobEffects.DIG_SPEED;
        }
        if (effect2 == 2) {
            return MobEffects.FIRE_RESISTANCE;
        }
        if (effect2 == 3) {
            return MobEffects.NIGHT_VISION;
        }
        if (effect2 == 4) {
            return MobEffects.WATER_BREATHING;
        }
        if (effect2 == 5) {
            return MobEffects.DAMAGE_BOOST;
        }
        if (effect2 == 6) {
            return MobEffects.REGENERATION;
        }
        if (effect2 == 7) {
            return MobEffects.INVISIBILITY;
        }

        return null;
    }


    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("energy")) {
            energyStorage.deserializeNBT(tag.get("energy"));
        }
        effect = tag.getShort("effect");
        effect2 =tag.getShort("effect2");
        effect3 =tag.getShort("effect3");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("energy", energyStorage.serializeNBT());

        tag.putShort("effect", effect);
        tag.putShort("effect2", effect2);
        tag.putShort("effect3", effect3);

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