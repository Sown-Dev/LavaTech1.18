package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class InfernalBrick extends Item {


    public InfernalBrick(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.infernalbrick").withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int num, boolean bool) {
        if(!entity.isOnFire()) {
            entity.setSecondsOnFire(3);
        }

        super.inventoryTick(stack, level, entity, num, bool);
    }
}