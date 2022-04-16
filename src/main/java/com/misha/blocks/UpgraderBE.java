package com.misha.blocks;

import com.misha.items.InfernalBoots;
import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import com.misha.setup.Registration;
import com.misha.tools.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
  
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UpgraderBE extends BlockEntity {

    int active = 0;
    public static int capacity = 100000;
    public int usage = 20;
    int transfer = 200;
    boolean hasPower = false;
    public static final int baseUsage=50;

    //ticks it takes to create item
    public static int baseTime=1000;
    private final ItemStackHandler itemHandler = createHandler();
    private final CustomEnergyStorage energyStorage = createEnergy();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);


    public int counter= 0;
    public int ccounter=0;
    public int cactive=0;

    public UpgraderBE(BlockPos pos, BlockState state) {
        //RecipeType.register("upgrader")
        super(Registration.UPGRADER_BE.get(), pos, state);
    }

    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        energy.invalidate();
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
            this.usage= baseUsage*this.active;
            if (hasEnoughPowerToWork()) {


                ItemStack stack = itemHandler.getStackInSlot(0);
                ItemStack ingot = itemHandler.getStackInSlot(1);
                ItemStack output = itemHandler.getStackInSlot(2);

                if (!stack.isEmpty() && stack.getItem() == Items.NETHERITE_SWORD.asItem()
                        && ingot.getItem() == Registration.INFERNALBRICK.get().asItem()
                        && (output.isEmpty()) ) {
                    energyStorage.consumeEnergy(usage);
                    if (counter >= baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack out= new ItemStack(Registration.INFERNALSWORD.get().asItem(), 1);
                            out.setTag(output.getTag());
                            itemHandler.setStackInSlot(2, out);
                            itemHandler.extractItem(0, 1, false);
                            itemHandler.extractItem(1, 1, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }

                    setChanged();

                }
                if (!stack.isEmpty() && stack.getItem() == Items.NETHERITE_PICKAXE.asItem()
                        && ingot.getItem() == Registration.INFERNALBRICK.get().asItem()
                        && (output.isEmpty()) ) {
                    energyStorage.consumeEnergy(usage);
                    if (counter >= baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack out= new ItemStack(Registration.INFERNALPICKAXE.get().asItem(), 1);
                            out.setTag(output.getTag());
                            itemHandler.setStackInSlot(2, out);
                            itemHandler.extractItem(0, 1, false);
                            itemHandler.extractItem(1, 1, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }



                    setChanged();


                }
                // Disabled until fixed
                if (!stack.isEmpty() && stack.getItem() == Items.NETHERITE_BOOTS.asItem()
                        && ingot.getItem() == Registration.INFERNALBRICK.get().asItem()
                        && ingot.getCount()>=4
                        && (output.isEmpty()) ) {
                    energyStorage.consumeEnergy(usage);
                    if (counter >= baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack out= new ItemStack(Registration.INFERNALBOOTS.get().asItem(), 1);
                            //out.setTag(stack.getTag());
                            itemHandler.setStackInSlot(2, out);
                            itemHandler.extractItem(0, 1, false);
                            itemHandler.extractItem(1, 4, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }

                /*  DISABLED... for now  //upgrading boots
                if (!stack.isEmpty() && stack.getItem() == Registration.INFERNALBOOTS.get().asItem()
                        && ingot.getItem() == Registration.INFERNALBRICK.get().asItem()
                        && ingot.getCount()>=3
                        && (output.isEmpty()) ) {
                    energyStorage.consumeEnergy(usage);
                    if (counter >= baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack out= itemHandler.getStackInSlot(0);
                            final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
                            UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[EquipmentSlot.FEET.getIndex()];
                            int upgrades = stack.getTag().getInt("upgrades");
                            System.out.println(upgrades);
                            stack.getTag().putInt("upgrades",upgrades+1);
                            out.getAttributeModifiers(EquipmentSlot.FEET).clear();
                            out.addAttributeModifier(Attributes.MAX_HEALTH,new AttributeModifier(uuid, "Health modifier", (double) (2*(upgrades+1)), AttributeModifier.Operation.ADDITION), EquipmentSlot.FEET);
                            out.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double) InfernalBoots.sdefense, AttributeModifier.Operation.ADDITION), EquipmentSlot.FEET);
                            out.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double) InfernalBoots.stoughness, AttributeModifier.Operation.ADDITION), EquipmentSlot.FEET);
                            out.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double) InfernalBoots.sknockback, AttributeModifier.Operation.ADDITION), EquipmentSlot.FEET);


                            itemHandler.setStackInSlot(2, out);
                            itemHandler.extractItem(0, 1, false);
                            itemHandler.extractItem(1, 3, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }
*/


                //Infernal Ingot
                if (!stack.isEmpty() && stack.getItem() == Registration.LAVABRICK.get().asItem()
                        && stack.getCount()>=9
                        && ingot.getItem() == Items.NETHERITE_INGOT.asItem()
                        && (output.isEmpty()) ) {
                    energyStorage.consumeEnergy(usage);
                    if (counter == baseTime / active) {
                        if (output.isEmpty()) {
                            ItemStack out= new ItemStack(Registration.INFERNALBRICK.get().asItem(), 1);
                            out.setTag(output.getTag());
                            itemHandler.setStackInSlot(2, out);
                            itemHandler.extractItem(0, 9, false);
                            itemHandler.extractItem(1, 1, false);
                        }
                        counter = 0;
                    } else {
                        counter++;
                    }
                    setChanged();
                }
            }
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    Block.UPDATE_ALL);
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false),
                    Block.UPDATE_ALL);
        }

    }

    private boolean hasEnoughPowerToWork() {
        return energyStorage.getEnergyStored() >= usage;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("counter", counter);
        tag.putInt("active", active);
        return new ClientboundBlockEntityDataPacket(worldPosition, 1, tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        ccounter=tag.getInt("counter");
        cactive=tag.getInt("active");
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

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.get("energy"));

        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());

        tag.putInt("counter", counter);

    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 2) {
                    return false;
                }
                if(slot==1){
                    return (stack.getItem() == Registration.INFERNALBRICK.get().asItem()
                            || stack.getItem() == Items.NETHERITE_INGOT.asItem());
                }
                if (slot == 0) {
                    return (stack.getItem() == Items.NETHERITE_SWORD.asItem()
                            || stack.getItem() == Items.NETHERITE_PICKAXE.asItem()
                            || stack.getItem() == Items.NETHERITE_BOOTS.asItem()
                            || stack.getItem() == Registration.LAVABRICK.get().asItem()
                            || stack.getItem() == Registration.INFERNALBOOTS.get().asItem());
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


    private CustomEnergyStorage createEnergy() {
        return new CustomEnergyStorage(capacity, transfer) {
            @Override
            protected void onEnergyChanged() {

                boolean newHasPower = hasEnoughPowerToWork();
                if (newHasPower != hasPower) {
                    hasPower = newHasPower;
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                }
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull  Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}