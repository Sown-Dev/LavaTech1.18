package com.misha.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.misha.lavaplus.LavaPlus;
import com.misha.setup.Registration;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class InfernalBoots extends ArmorItem {
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    //private final Multimap<Attribute, AttributeModifier> defaultModifiers;


    public static double sdefense=13.0F;
    public static float stoughness=6.5F;
    public static float sknockback= 0.3F;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public InfernalBoots(Properties p){
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(EquipmentSlot p_40410_) {
                return 1000;
            }

            @Override
            public int getDefenseForSlot(EquipmentSlot p_40411_) {
                return (int)sdefense;
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
                return LavaPlus.MODID+":infernal";
            }

            @Override
            public float getToughness() {
                return stoughness;
            }

            @Override
            public float getKnockbackResistance() {
                return sknockback;
            }
        }, EquipmentSlot.FEET, p);


        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[EquipmentSlot.FEET.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)sdefense, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Health modifier", (double)16.0F, AttributeModifier.Operation.ADDITION));

        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", sknockback, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
    }
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot eslot) {
        return eslot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(eslot);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags) {
        super.appendHoverText(stack, level, list, flags);
        list.add(new TranslatableComponent("message.infernalboots").withStyle(ChatFormatting.DARK_GRAY));
    }
}
