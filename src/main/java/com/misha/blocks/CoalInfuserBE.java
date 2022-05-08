package com.misha.blocks;

import com.misha.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CoalInfuserBE extends BlockEntity {

    int active = 0;
    int cactive = 0;
    private final ItemStackHandler itemHandler = createHandler();


    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    int ccounter = 0;
    public int counter = 0;
    static int baseTime = 1500;

    public CoalInfuserBE(BlockPos pos, BlockState state) {
        //RecipeType.register("coalinfuser")
        super(Registration.COALINFUSER_BE.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
    }

    public void tickServer(BlockState state) {
        BlockPos below = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());

        BlockState blockState = level.getBlockState(worldPosition);

        if (level.getBlockState(below).getBlock() instanceof LavaVent) {
            this.active = ((LavaVentBE) level.getBlockEntity(below)).active;
        } else {
            this.active = 0;
        }

        if (this.active > 0) {

            ItemStack stack = itemHandler.getStackInSlot(0);
            ItemStack output = itemHandler.getStackInSlot(1);

            if (!stack.isEmpty() && stack.getItem() == Items.COAL.asItem()
                    && (output.isEmpty() || output.getItem() == Registration.MAGMACOAL.get().asItem())) {
                if (counter >= baseTime / active) {
                    if (output.isEmpty()) {
                        ItemStack coal = new ItemStack(Registration.MAGMACOAL.get().asItem(), 1);
                        itemHandler.setStackInSlot(1, coal);
                        itemHandler.extractItem(0, 1, false);
                    } else {
                        ItemStack coal = output;
                        coal.setCount(output.getCount() + 1);
                        itemHandler.setStackInSlot(1, coal);
                        itemHandler.extractItem(0, 1, false);
                    }
                    counter = 0;
                } else {
                    counter++;
                }


                setChanged();
            } else {
                counter = 0;
            }


            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    Block.UPDATE_ALL);
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false),
                    Block.UPDATE_ALL);
        }

    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        ccounter = tag.getInt("counter");
        cactive = tag.getInt("active");
    }

    public void tickClient(BlockState state) {

    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());

        tag.putInt("counter", counter);

    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) {
                    return (stack.getItem() == Items.COAL);
                } else {
                    return true;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if ((stack.isEmpty())) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}