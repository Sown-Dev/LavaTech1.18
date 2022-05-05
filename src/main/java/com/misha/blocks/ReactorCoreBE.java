package com.misha.blocks;

import com.misha.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class ReactorCoreBE extends BlockEntity {


    public int active = 0;
    boolean built = false;
    boolean prebuilt = false;
    BlockPos panelpos = worldPosition;
    BlockPos portpos = worldPosition;

    public ReactorCoreBE(BlockPos pos, BlockState state) {
        super(Registration.REACTORCORE_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed

    }

    public void tickClient(boolean built, boolean oldbuilt) {
        if (built && !oldbuilt) {
            for (int i = 1; i < 10; i++) {
                System.out.println("here");
                level.addAlwaysVisibleParticle(ParticleTypes.POOF, worldPosition.getX() + (1.0D / (double) i), worldPosition.getY()+2, worldPosition.getZ(), 0.0D, 0.15D, 0.0D);
                level.addAlwaysVisibleParticle(ParticleTypes.POOF, worldPosition.getX()+1.5D , worldPosition.getY() +(1.0D / (double) i), worldPosition.getZ(), 0.15D, 0.0D, 0.0D);

            }
        }
    }

    public void tickServer(BlockState state) {

        // naming: 23 -> 2: layer (1-3), 3:block (1-9):
        // block:     layer:
        // 123    X   1
        // 456   Z    2
        // 789        3
        BlockPos b11 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ() + 1);
        BlockPos b12 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
        BlockPos b13 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ() - 1);

        BlockPos b14 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ() + 1);
        BlockPos b15 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
        BlockPos b16 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ() - 1);

        BlockPos b17 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ() + 1);
        BlockPos b18 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
        BlockPos b19 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ() - 1);

//layer 2
        BlockPos b21 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY(), this.getBlockPos().getZ() + 1);
        BlockPos b22 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY(), this.getBlockPos().getZ());
        BlockPos b23 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY(), this.getBlockPos().getZ() - 1);

        BlockPos b24 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ() + 1);
        //CORE IS HERE
        BlockPos b26 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ() - 1);

        BlockPos b27 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY(), this.getBlockPos().getZ() + 1);
        BlockPos b28 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY(), this.getBlockPos().getZ());
        BlockPos b29 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY(), this.getBlockPos().getZ() - 1);

        //layer 3
        BlockPos b31 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        BlockPos b32 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        BlockPos b33 = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);

        BlockPos b34 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        BlockPos b35 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        BlockPos b36 = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);

        BlockPos b37 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() + 1);
        BlockPos b38 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
        BlockPos b39 = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() - 1, this.getBlockPos().getZ() - 1);


        //now check everything:
        if (
                level.getBlockState(b11).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b12).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b13).getBlock() == Registration.REACTORFRAME.get() &&

                        level.getBlockState(b14).getBlock() == Registration.REACTORFRAME.get() &&
                        (level.getBlockState(b15).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b15).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b15).getBlock() == Registration.REACTORFRAME.get()) &&
                        level.getBlockState(b16).getBlock() == Registration.REACTORFRAME.get() &&

                        level.getBlockState(b17).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b18).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b19).getBlock() == Registration.REACTORFRAME.get() &&


                        level.getBlockState(b21).getBlock() == Registration.REACTORFRAME.get() &&
                        (level.getBlockState(b22).getBlock() == Registration.REACTORPORT.get() || level.getBlockState(b22).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b22).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b22).getBlock() == Registration.REACTORFRAME.get()) &&
                        level.getBlockState(b23).getBlock() == Registration.REACTORFRAME.get() &&

                        (level.getBlockState(b24).getBlock() == Registration.REACTORPORT.get() || level.getBlockState(b24).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b24).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b24).getBlock() == Registration.REACTORFRAME.get()) &&
                        //   CORE
                        (level.getBlockState(b26).getBlock() == Registration.REACTORPORT.get() || level.getBlockState(b26).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b26).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b26).getBlock() == Registration.REACTORFRAME.get()) &&

                        level.getBlockState(b27).getBlock() == Registration.REACTORFRAME.get() &&
                        (level.getBlockState(b28).getBlock() == Registration.REACTORPORT.get() || level.getBlockState(b28).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b28).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b28).getBlock() == Registration.REACTORFRAME.get()) &&
                        level.getBlockState(b29).getBlock() == Registration.REACTORFRAME.get() &&

                        //layer 3:
                        level.getBlockState(b31).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b32).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b33).getBlock() == Registration.REACTORFRAME.get() &&

                        level.getBlockState(b34).getBlock() == Registration.REACTORFRAME.get() &&
                        (level.getBlockState(b35).getBlock() == Registration.REACTORPANEL.get() || level.getBlockState(b35).getBlock() == Registration.REACTORGLASS.get() || level.getBlockState(b35).getBlock() == Registration.REACTORFRAME.get()) &&
                        level.getBlockState(b36).getBlock() == Registration.REACTORFRAME.get() &&

                        level.getBlockState(b37).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b38).getBlock() == Registration.REACTORFRAME.get() &&
                        level.getBlockState(b39).getBlock() == Registration.REACTORFRAME.get()
        ) {
            //now check to see if there is only 1 panel:
            int panels = 0;
            if (level.getBlockState(b15).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b15;
            }
            if (level.getBlockState(b22).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b22;
            }
            if (level.getBlockState(b24).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b24;
            }
            if (level.getBlockState(b26).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b26;
            }
            if (level.getBlockState(b28).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b28;
            }
            if (level.getBlockState(b35).getBlock() == Registration.REACTORPANEL.get()) {
                panels++;
                panelpos = b35;
            }

            //check ports
            int ports = 0;
            if (level.getBlockState(b15).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b15;
            }
            if (level.getBlockState(b22).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b22;
            }
            if (level.getBlockState(b24).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b24;
            }
            if (level.getBlockState(b26).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b26;
            }
            if (level.getBlockState(b28).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b28;
            }
            if (level.getBlockState(b35).getBlock() == Registration.REACTORPORT.get()) {
                ports++;
                portpos = b35;
            }

            built = panels == 1 && ports == 1;


        } else {
            built = false;
        }

        //display particle effect if built:
        if (built && !prebuilt) {
            //particle stuff

        }
        prebuilt = built;


    }
}
