package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class MagmaCoal extends Item{


    public MagmaCoal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.magmacoal").withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType){
        return 3200;
    }


    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }


}