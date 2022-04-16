package com.misha.tools;

import com.misha.setup.Registration;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class InfernalMaterial implements ArmorMaterial {
    public InfernalMaterial(){

    }
    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return 1000;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_) {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Registration.INFERNALBRICK.get());
    }

    @Override
    public String getName() {
        return "Infernal Brick";
    }

    @Override
    public float getToughness() {
        return 4.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.6F;
    }
}
