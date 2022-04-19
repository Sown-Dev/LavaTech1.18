package com.misha.blocks;

import com.misha.setup.Registration;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class Hydroponics extends Block {
    public Hydroponics(){
        super(BlockBehaviour.Properties.of(Material.WOOD)
                .sound(SoundType.STONE)
                .strength(0.5f));
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("message.copperdrill").withStyle(ChatFormatting.DARK_GRAY));
    }
    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer(){
        ItemBlockRenderTypes.setRenderLayer(Registration.HYDROPONICS.get(), RenderType.translucent());
    }
}
