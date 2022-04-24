package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CopperPickaxe extends PickaxeItem {

    public CopperPickaxe(Properties p) {
        super(Tiers.IRON,2, -4.0F, p);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.copperpickaxe").withStyle(ChatFormatting.DARK_GRAY));
    }
}
