package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class InfernalSword extends SwordItem {
    public InfernalSword( Properties p_43272_) {
        super(Tiers.NETHERITE, 5, -2.3F, p_43272_);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.infernalsword").withStyle(ChatFormatting.DARK_GRAY));
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity entity2){
           entity.setSecondsOnFire(10);
        return super.hurtEnemy(stack, entity,entity2);
    }
}
