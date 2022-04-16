package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
  
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class HealerBE extends BlockEntity {
    int capacity = 10000;
    int transfer=200;
    int usage=1000;
    boolean stepped=false;
    private final CustomEnergyStorage energyStorage = createEnergy();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:

    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int counter;

    public HealerBE(BlockPos pos, BlockState state) {
        super(Registration.HEALER_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        energy.invalidate();
    }
int step=0;
    public void tickServer(BlockState state) {
        if(step>10){
            stepped=false;
            step=0;
        }else{
            step++;
        }
        level.setBlock(worldPosition, state.setValue(BlockStateProperties.POWERED,stepped ),
                Block.UPDATE_ALL);
    }
    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public void heal (Entity entity){

    if(hasEnoughPowerToWork()) {
        if (entity instanceof Player) {
            if(counter >= 10) {
            if (((Player) entity).getHealth() < ((Player) entity).getMaxHealth()) {
                stepped=true;
                energyStorage.consumeEnergy(usage);
                ((Player) entity).heal(0.5F);
                LivingEntity e = (LivingEntity) entity;
                counter = 0;
            }
            }else {
                counter++;
            }
        }
    }
    }



    @Override
    public void load(CompoundTag tag) {
        energyStorage.deserializeNBT(tag.get("energy"));

        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public   void saveAdditional(CompoundTag tag) {
        tag.put("energy", energyStorage.serializeNBT());

        tag.putInt("counter", counter);
         
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