package com.misha.blocks;

import com.misha.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.LavaFluid;
  

public class LavaVentBE extends BlockEntity {


    public int active=0;

    public LavaVentBE(BlockPos pos, BlockState state) {
        super(Registration.LAVAVENT_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed

    }
    public void tickServer(BlockState state){
        BlockPos side1 = new BlockPos(this.getBlockPos().getX()+1,this.getBlockPos().getY(),this.getBlockPos().getZ());
        BlockPos side2 = new BlockPos(this.getBlockPos().getX()-1,this.getBlockPos().getY(),this.getBlockPos().getZ());
        BlockPos side3 = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY(),this.getBlockPos().getZ()-1);
        BlockPos side4 = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY(),this.getBlockPos().getZ()+1);

        BlockPos top = new BlockPos(this.getBlockPos().getX(),this.getBlockPos().getY()+1,this.getBlockPos().getZ());
        int tempactive=0;
        level.sendBlockUpdated(top, getBlockState(),getBlockState(), Block.UPDATE_CLIENTS);
        if( level.getFluidState(side1).getType() instanceof LavaFluid && level.getFluidState(side1).isSource()){
            tempactive+=1;
        }
                if(level.getFluidState(side2).getType() instanceof LavaFluid && level.getFluidState(side2).isSource()){
                    tempactive+=1;
                }
        if(level.getFluidState(side3).getType() instanceof LavaFluid && level.getFluidState(side3).isSource()){
            tempactive+=1;
        }
        if(level.getFluidState(side4).getType() instanceof LavaFluid && level.getFluidState(side4).isSource()){
            tempactive+=1;
        }
        // Check if it is heated magma block
        if(level.getBlockState(side1).getBlock() instanceof HeatedMagmaBlock){
            tempactive+=2;
        }
        if(level.getBlockState(side2).getBlock() instanceof HeatedMagmaBlock){
            tempactive+=2;
        }
        if(level.getBlockState(side3).getBlock() instanceof HeatedMagmaBlock){
            tempactive+=2;
        }
        if(level.getBlockState(side4).getBlock() instanceof HeatedMagmaBlock){
            tempactive+=2;
        }


        if(level.getBlockState(side1).getBlock() instanceof Heater && ((HeaterBE) level.getBlockEntity(side1) ).active){
            tempactive+=4;
        }
        if(level.getBlockState(side2).getBlock() instanceof Heater && ((HeaterBE) level.getBlockEntity(side2) ).active){
            tempactive+=4;
        }
        if(level.getBlockState(side3).getBlock() instanceof Heater && ((HeaterBE) level.getBlockEntity(side3) ).active){
            tempactive+=4;
        }
        if(level.getBlockState(side4).getBlock() instanceof Heater && ((HeaterBE) level.getBlockEntity(side4) ).active){
            tempactive+=4;
        }

if(active !=tempactive){
    active=tempactive;
}
if(active>0){
            BlockState blockState = level.getBlockState(worldPosition);
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    Block.UPDATE_ALL);

            setChanged();
        }else{
            BlockState blockState = level.getBlockState(worldPosition);
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false),
                    Block.UPDATE_ALL);

        }



    }
}
