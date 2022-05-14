package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.List;

public class CopperHanddrill extends PickaxeItem {


    private final EnergyStorage energyStorage = new EnergyStorage(20000, 1000, 0, 1000);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public CopperHanddrill(Properties p) {
        super(new Tier() {
            public int getUses() {
                return 380;
            }

            public float getSpeed() {
                return 7.5f;
            }

            public float getAttackDamageBonus() {
                return 2.5f;
            }

            public int getLevel() {
                return 2;
            }

            public int getEnchantmentValue() {
                return 15;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(ItemTags.create(new ResourceLocation("forge:ingots/copper")));
            }
        }, 1, -2.8f, p);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
            list.add(new TextComponent("Energy:" + Integer.toString(h.getEnergyStored()) + "/" + h.getMaxEnergyStored()).withStyle(ChatFormatting.DARK_GRAY));
        });
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            energyStorage.extractEnergy(100, false);
        }

        return true;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {

        return super.isDamageable(stack);
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = super.getShareTag(stack);
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent( h-> {
            tag.put("energy", ((EnergyStorage)h).serializeNBT());
        });
        return tag;
    }
    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag tag) {
        if (tag.contains("energy")) {
            stack.getCapability(CapabilityEnergy.ENERGY).ifPresent( h-> {
                ((EnergyStorage)h).deserializeNBT(tag.get("h"));
            });
        }
        tag.remove("energy");
        super.readShareTag(stack,tag);
    }


}
