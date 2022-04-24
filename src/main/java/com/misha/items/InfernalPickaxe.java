package com.misha.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class InfernalPickaxe extends PickaxeItem {

    public InfernalPickaxe(Properties p) {
        super(Tiers.NETHERITE,1, -2.8F, p);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list , TooltipFlag flags){
        super.appendHoverText(stack,level, list, flags);
        list.add(new TranslatableComponent("message.infernalpickaxe").withStyle(ChatFormatting.DARK_GRAY));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
        ItemStack stack= player.getItemInHand(hand);
        BlockPos pos =new BlockPos(player.getX(),player.getY(),player.getZ());
        level.setBlockAndUpdate(pos,Blocks.TORCH.defaultBlockState());
stack.setDamageValue(stack.getDamageValue()+5);
        return InteractionResultHolder.success(stack);
    }
}
