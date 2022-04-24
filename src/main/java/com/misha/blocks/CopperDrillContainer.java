package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CopperDrillContainer extends AbstractContainerMenu {

    CopperDrillBE blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;
    boolean active=false;
    int counter = 0;

    short dat = (short) counter;

    public CopperDrillContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.COPPERDRILL_CONTAINER.get(), windowId);
        blockEntity = (CopperDrillBE) world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                for(int i =0;i<15; i++){
                    addSlot(new SlotItemHandler(h, i, 34+((i%5) *18), 6+((i/5)*18)));
                }

            });
        }
        layoutPlayerInventorySlots(6, 88);
        trackPower();
        trackVars();

    }




    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.COPPERDRILL.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index > 0 && index<15) {
                if (!this.moveItemStackTo(stack, 15, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if(index>=15){
                    if (!this.moveItemStackTo(stack, 0, 14, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
    private void trackPower() {

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((CustomEnergyStorage)h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((CustomEnergyStorage)h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }

    public int getEnergy() {
        return blockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
    public int getCounter(){
        return blockEntity.counter;
    }
    public int getErrCode(){
        return blockEntity.errorCode;
    }
    public int getDepth(){
        return blockEntity.depth;
    }


    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }


    private void trackVars() {
        // Unfortunatelly on a dedicated server ints are actually truncated to short so we need
        // to split our integer here (split our 32 bit integer into two 16 bit integers)
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getCounter() & 0xffff;
            }

            @Override
            public void set(int value) {
                int counter = blockEntity.counter & 0xffff0000;
                blockEntity.counter = counter+ (value & 0xffff);
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getCounter() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                int counter = blockEntity.counter & 0x0000ffff;
                blockEntity.counter = counter+ (value <<16);
            }
        });


        //depth
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getDepth() & 0xffff;
            }

            @Override
            public void set(int value) {
                int depth = blockEntity.depth & 0xffff0000;
                blockEntity.depth = depth+ (value & 0xffff);
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getDepth() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                int depth = blockEntity.depth & 0x0000ffff;
                blockEntity.depth = depth+ (value <<16);
            }
        });

        //errorcode
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getErrCode() & 0xffff;
            }

            @Override
            public void set(int value) {
                int err= blockEntity.errorCode & 0xffff0000;
                blockEntity.errorCode = err+ (value & 0xffff);
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getErrCode() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                int err = blockEntity.errorCode & 0x0000ffff;
                blockEntity.errorCode = err+ (value <<16);
            }
        });

    }
}