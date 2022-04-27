package com.misha.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class BenBlock extends TntBlock {
    public BenBlock() {
        super(BlockBehaviour.Properties.of(Material.GLASS)
                .sound(SoundType.GLASS)
                .strength(1.0f));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        int energy = stack.hasTag() ? stack.getTag().getInt("energy") : 0;
        list.add(new TranslatableComponent("message.benblock", Integer.toString(energy)).withStyle(ChatFormatting.DARK_GRAY));
    }
}
