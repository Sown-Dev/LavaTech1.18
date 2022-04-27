package com.misha.blocks;

import com.misha.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
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

public class AlloySmelterBE extends BlockEntity {

    int active = 0;
    public static int baseTime = 200;


    private final ItemStackHandler itemHandler = createHandler();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);


    public short counter = 0;
    public int cactive = 0;

    short fuel = 0;

    public AlloySmelterBE(BlockPos pos, BlockState state) {
        super(Registration.ALLOYSMELTER_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();

    }

    public void tickServer(BlockState state) {

        ItemStack in1 = itemHandler.getStackInSlot(0);
        ItemStack in2 = itemHandler.getStackInSlot(1);
        ItemStack in3 = itemHandler.getStackInSlot(2);
        ItemStack fslot = itemHandler.getStackInSlot(3);
        ItemStack output = itemHandler.getStackInSlot(4);

        if (fuel > 0 || fslot.getItem() == Items.COAL) {
            if (in1.is(ItemTags.create(new ResourceLocation("forge:ingots/aluminum")))
                    && in2.getItem() == Items.IRON_INGOT
                    && in3.is(ItemTags.create(new ResourceLocation("forge:ingots/tin")))
                    && (output.isEmpty() || output.getItem() == Registration.FERROUSALLOY.get())) {
                if (fuel == 0) {
                    itemHandler.extractItem(3, 1, false);
                    fuel += baseTime + 1;
                }
                if (fuel > 0) {

                    fuel--;
                    if (counter >= baseTime) {

                        counter = 0;
                        itemHandler.extractItem(0, 1, false);
                        itemHandler.extractItem(1, 1, false);
                        itemHandler.extractItem(2, 1, false);

                        ItemStack newItem = new ItemStack(Registration.FERROUSALLOY.get(), output.getCount() + 2);
                        itemHandler.setStackInSlot(4, newItem);
                    } else {
                        counter++;
                    }
                }
            } else if (in1.is(ItemTags.create(new ResourceLocation("forge:ingots/tin")))
                    &&  in3.is(ItemTags.create(new ResourceLocation("forge:ingots/copper")))
                    && in3.is(ItemTags.create(new ResourceLocation("forge:ingots/copper")))
                    && (output.isEmpty() || output.getItem() == Registration.BRONZE.get())) {
                if (fuel == 0) {
                    itemHandler.extractItem(3, 1, false);
                    fuel += baseTime + 1;
                }
                if (fuel > 0) {

                    fuel--;
                    if (counter >= baseTime) {

                        counter = 0;
                        itemHandler.extractItem(0, 1, false);
                        itemHandler.extractItem(1, 1, false);
                        itemHandler.extractItem(2, 1, false);

                        ItemStack newItem = new ItemStack(Registration.BRONZE.get(), output.getCount() + 3);
                        itemHandler.setStackInSlot(4, newItem);
                    } else {
                        counter++;
                    }
                }
            } else {
                counter = 0;
            }
        } else {
            counter = 0;
        }

        level.setBlock(worldPosition, state.setValue(BlockStateProperties.POWERED, fuel > 0),
                Block.UPDATE_ALL);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));

        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());


    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(5) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 4) {
                    return false;
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