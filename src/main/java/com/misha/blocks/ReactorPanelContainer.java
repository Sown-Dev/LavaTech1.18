package com.misha.blocks;

import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ReactorPanelContainer extends AbstractContainerMenu {

    ReactorPanelBE blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;
    boolean active = false;
    int counter = 0;

    short dat = (short) counter;

    public ReactorPanelContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.REACTORPANEL_CONTAINER.get(), windowId);
        blockEntity = (ReactorPanelBE) world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 78, 43));
                addSlot(new SlotItemHandler(h, 1, 121, 59));
            });
        }

        layoutPlayerInventorySlots(6, 85);
        trackPower();
        trackVars();
    }


    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.REACTORPANEL.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 3, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if(index>0 && index<3){
                    if (!this.moveItemStackTo(stack, 3, 37, true)) {
                        return ItemStack.EMPTY;
                    }
                }
                if (stack.getItem()==Registration.FUELCELL.get()) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
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
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void trackPower() {
        // Unfortunatelly on a dedicated server ints are actually truncated to short so we need
        // to split our integer here (split our 32 bit integer into two 16 bit integers)
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                int energyStored = blockEntity.energy & 0xffff0000;
                blockEntity.energy= (energyStored + (value & 0xffff));
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                int energyStored = blockEntity.energy& 0x0000ffff;
                blockEntity.energy= (energyStored | (value << 16));

            }
        });
    }

    public int getEnergy() { return blockEntity.energy;}
    public int getHeat() { return blockEntity.heat;}
    public int getCarbon() { return blockEntity.carbon;}
    public int getFuel() { return blockEntity.fuel;}
    public int getBuilt() { if(blockEntity.built){
    return 1;
    }else{
        return 0;
    }
    }


    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private void trackVars() {
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getCarbon();
            }

            @Override
            public void set(int value) {
                blockEntity.carbon=value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getHeat();
            }

            @Override
            public void set(int value) {
                blockEntity.heat=(short)value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getFuel();
            }

            @Override
            public void set(int value) {
                blockEntity.fuel=(short)value;
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getBuilt();
            }

            @Override
            public void set(int value) {
                blockEntity.built=value==1;
            }
        });


    }

}