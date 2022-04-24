package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CopperSword extends SwordItem {
    public CopperSword( Properties p_43272_) {
        super(Tiers.IRON, 3, -2.6F, p_43272_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags) {
        super.appendHoverText(stack, level, list, flags);
        list.add(new TranslatableComponent("message.coppersword").withStyle(ChatFormatting.DARK_GRAY));
    }
}
