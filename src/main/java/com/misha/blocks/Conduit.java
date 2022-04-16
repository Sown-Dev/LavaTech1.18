package com.misha.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.List;

public class Conduit extends Block implements EntityBlock {

/*    static final VoxelShape SHAPE= Stream.of(
            Block.box(4, 0, 4, 12, 16, 12),
            Block.box(4, 4, 0, 12, 12, 16),
            Block.box(0, 4, 4, 16, 12, 12),
            Block.box(3, 3, 3, 13, 13, 13)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();*/
    public Conduit() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .dynamicShape()
                .strength(2.0f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ConduitBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        } else {
            return (level1, pos, state1, tile) -> {
                if (tile instanceof ConduitBE conduit) {
                    conduit.tickServer(state1);
                }
            };
        }
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        int energy = stack.hasTag() ? stack.getTag().getInt("energy") : 0;
        list.add(new TranslatableComponent("message.conduit", Integer.toString(energy)).withStyle(ChatFormatting.DARK_GRAY));
    }



}