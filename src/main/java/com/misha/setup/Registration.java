package com.misha.setup;

import com.misha.blocks.*;
import com.misha.items.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.misha.lavaplus.LavaPlus.MODID;
public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);


    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCKENTITIES.register(bus);
        CONTAINERS.register(bus);
    }

    //items:
    public static final RegistryObject<LavaBrick> LAVABRICK = ITEMS.register("lavabrick", () -> new LavaBrick(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<InfernalBrick> INFERNALBRICK = ITEMS.register("infernalbrick", () -> new InfernalBrick(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> INFERNALPICKAXE = ITEMS.register("infernalpickaxe", () -> new InfernalPickaxe(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> INFERNALSWORD = ITEMS.register("infernalsword", () -> new InfernalSword(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> MAGMACOAL = ITEMS.register("magmacoal", () -> new MagmaCoal(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> INFERNALBOOTS = ITEMS.register("infernalboots", () -> new InfernalBoots(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> LAVAGUIDE= ITEMS.register("lavaguide", () -> new LavaGuide(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COPPERPICKAXE= ITEMS.register("copperpickaxe", () -> new CopperPickaxe(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPERSWORD= ITEMS.register("coppersword", () -> new CopperSword(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    //blocks:
    public static final RegistryObject<BlockBurner> BLOCKBURNER = BLOCKS.register("blockburner",BlockBurner::new);
    public static final RegistryObject<Block> MACHINEFRAME = BLOCKS.register("machineframe", MachineFrame::new);
    public static final RegistryObject<Block> LAVAVENT = BLOCKS.register("lavavent", LavaVent::new);
    public static final RegistryObject<Block> COALINFUSER = BLOCKS.register("coalinfuser", CoalInfuser::new);
    public static final RegistryObject<Block> INDUCTIONFURNACE = BLOCKS.register("inductionfurnace", InductionFurnace::new);
    public static final RegistryObject<Block> CONDUIT = BLOCKS.register("conduit", Conduit::new);
    public static final RegistryObject<Block> LAVAGENERATOR = BLOCKS.register("lavagenerator", LavaGenerator::new);
    public static final RegistryObject<Block> HEATEDMAGMABLOCK = BLOCKS.register("heatedmagmablock", HeatedMagmaBlock::new);
    public static final RegistryObject<Block> BATTERY = BLOCKS.register("battery", Battery::new);
    public static final RegistryObject<Block> CENTRIFUGE = BLOCKS.register("centrifuge", Centrifuge::new);
    public static final RegistryObject<Block> COMPRESSOR = BLOCKS.register("compressor", Compressor::new);
    public static final RegistryObject<Block> UPGRADER = BLOCKS.register("upgrader", Upgrader::new);
    public static final RegistryObject<Block> HEATER= BLOCKS.register("heater", Heater::new);
    public static final RegistryObject<Block> CRATE= BLOCKS.register("crate", Crate::new);
    public static final RegistryObject<Block> HEALER= BLOCKS.register("healer", Healer::new);
    public static final RegistryObject<Block> BENBLOCK= BLOCKS.register("benblock", BenBlock::new);
    public static final RegistryObject<Block> REINFORCEDFRAME= BLOCKS.register("reinforcedframe", ReinforcedFrame::new);


    //block items:

    public static final RegistryObject<Item> LAVAVENT_ITEM = ITEMS.register("lavavent", () -> new BlockItem(LAVAVENT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BLOCKBURNER_ITEM = ITEMS.register("blockburner", () -> new BlockItem(BLOCKBURNER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> MACHINEFRAME_ITEM = ITEMS.register("machineframe", () -> new BlockItem(MACHINEFRAME.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> COALINFUSER_ITEM = ITEMS.register("coalinfuser", () -> new BlockItem(COALINFUSER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> INDUCTIONFURNACE_ITEM = ITEMS.register("inductionfurnace", () -> new BlockItem(INDUCTIONFURNACE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CONDUIT_ITEM = ITEMS.register("conduit", () -> new BlockItem(CONDUIT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> LAVAGENERATOR_ITEM = ITEMS.register("lavagenerator", () -> new BlockItem(LAVAGENERATOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> HEATEDMAGMABLOCK_ITEM = ITEMS.register("heatedmagmablock", () -> new BlockItem(HEATEDMAGMABLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BATTERY_ITEM = ITEMS.register("battery", () -> new BlockItem(BATTERY.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CENTRIFUGE_ITEM = ITEMS.register("centrifuge", () -> new BlockItem(CENTRIFUGE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> COMPRESSOR_ITEM = ITEMS.register("compressor", () -> new BlockItem(COMPRESSOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> UPGRADER_ITEM = ITEMS.register("upgrader", () -> new BlockItem(UPGRADER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> HEATER_ITEM = ITEMS.register("heater", () -> new BlockItem(HEATER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CRATE_ITEM = ITEMS.register("crate", () -> new BlockItem(CRATE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> HEALER_ITEM = ITEMS.register("healer", () -> new BlockItem(HEALER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BENBLOCK_ITEM = ITEMS.register("benblock", () -> new BlockItem(BENBLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> REINFORCEDFRAME_ITEM = ITEMS.register("reinforcedframe", () -> new BlockItem(REINFORCEDFRAME.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));


    public static final RegistryObject<BlockEntityType<BlockBurnerBE>> BLOCKBURNER_BE = BLOCKENTITIES.register("blockburner",
            () -> BlockEntityType.Builder.of(BlockBurnerBE::new, BLOCKBURNER.get()).build(null));
    public static final RegistryObject<BlockEntityType<LavaVentBE>> LAVAVENT_BE = BLOCKENTITIES.register("lavavent",
            () -> BlockEntityType.Builder.of(LavaVentBE::new, LAVAVENT.get()).build(null));
    public static final RegistryObject<BlockEntityType<CoalInfuserBE>> COALINFUSER_BE = BLOCKENTITIES.register("coalinfuser",
            () -> BlockEntityType.Builder.of(CoalInfuserBE::new, COALINFUSER.get()).build(null));
    public static final RegistryObject<BlockEntityType<InductionFurnaceBE>> INDUCTIONFURNACE_BE = BLOCKENTITIES.register("inductionfurnace",
            () -> BlockEntityType.Builder.of(InductionFurnaceBE::new, INDUCTIONFURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ConduitBE>> CONDUIT_BE = BLOCKENTITIES.register("conduit",
            () -> BlockEntityType.Builder.of(ConduitBE::new, CONDUIT.get()).build(null));
    public static final RegistryObject<BlockEntityType<LavaGeneratorBE>> LAVAGENERATOR_BE = BLOCKENTITIES.register("lavagenerator",
            () -> BlockEntityType.Builder.of(LavaGeneratorBE::new, LAVAGENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<BatteryBE>> BATTERY_BE = BLOCKENTITIES.register("battery",
            () -> BlockEntityType.Builder.of(BatteryBE::new, BATTERY.get()).build(null));
    public static final RegistryObject<BlockEntityType<CentrifugeBE>> CENTRIFUGE_BE = BLOCKENTITIES.register("centrifuge",
            () -> BlockEntityType.Builder.of(CentrifugeBE::new, CENTRIFUGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CompressorBE>> COMPRESSOR_BE = BLOCKENTITIES.register("compressor",
            () -> BlockEntityType.Builder.of(CompressorBE::new, COMPRESSOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<UpgraderBE>> UPGRADER_BE = BLOCKENTITIES.register("upgrader",
            () -> BlockEntityType.Builder.of(UpgraderBE::new, UPGRADER.get()).build(null));
    public static final RegistryObject<BlockEntityType<HeaterBE>> HEATER_BE = BLOCKENTITIES.register("heater",
            () -> BlockEntityType.Builder.of(HeaterBE::new, HEATER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrateBE>> CRATE_BE = BLOCKENTITIES.register("crate",
            () -> BlockEntityType.Builder.of(CrateBE::new, CRATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<HealerBE>> HEALER_BE = BLOCKENTITIES.register("healer",
            () -> BlockEntityType.Builder.of(HealerBE::new, HEALER.get()).build(null));



    public static final RegistryObject<MenuType<BlockBurnerContainer>> BLOCKBURNER_CONTAINER = CONTAINERS.register("blockburner", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new BlockBurnerContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CoalInfuserContainer>> COALINFUSER_CONTAINER = CONTAINERS.register("coalinfuser", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CoalInfuserContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<MenuType<InductionFurnaceContainer>> INDUCTIONFURNACE_CONTAINER = CONTAINERS.register("inductionfurnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new InductionFurnaceContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<MenuType<LavaGeneratorContainer>> LAVAGENERATOR_CONTAINER = CONTAINERS.register("lavagenerator", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new LavaGeneratorContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<BatteryContainer>> BATTERY_CONTAINER = CONTAINERS.register("battery", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new BatteryContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CentrifugeContainer>> CENTRIFUGE_CONTAINER = CONTAINERS.register("centrifuge", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CentrifugeContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CompressorContainer>> COMPRESSOR_CONTAINER = CONTAINERS.register("compressor", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CompressorContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<UpgraderContainer>> UPGRADER_CONTAINER = CONTAINERS.register("upgrader", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new UpgraderContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CrateContainer>> CRATE_CONTAINER = CONTAINERS.register("crate", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CrateContainer(windowId, world, pos, inv, inv.player);
    }));


}
