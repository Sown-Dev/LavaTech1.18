package com.misha.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class ReactorFrame extends Block {
    public ReactorFrame() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .sound(SoundType.METAL)
                .noOcclusion()
                .strength(0.5f));
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("message.reactorframe").withStyle(ChatFormatting.DARK_GRAY));
    }


}
