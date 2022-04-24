package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CopperPickaxe extends PickaxeItem {

    public CopperPickaxe(Properties p) {
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
                    return Ingredient.of(ItemTags.create(new ResourceLocation("forge:ingots/bronze")));
                }
            }, 1, -2.8f, p);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.copperpickaxe").withStyle(ChatFormatting.DARK_GRAY));
    }
}
