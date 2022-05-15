package com.misha.setup;

import com.misha.blocks.*;
import com.misha.items.*;
import com.misha.lavaplus.LavaPlus;
import com.misha.recipes.ModRecipes;
import com.misha.tools.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.misha.lavaplus.LavaPlus.MODID;
public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);


    static int id=1;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(LavaPlus.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public int nextID(){
        id++;
        return id;
    }
    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCKENTITIES.register(bus);
        CONTAINERS.register(bus);
        ModRecipes.register(bus);

    }


    //items:
    public static final RegistryObject<LavaBrick> LAVABRICK = ITEMS.register("lavabrick", () -> new LavaBrick(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<InfernalBrick> INFERNALBRICK = ITEMS.register("infernalbrick", () -> new InfernalBrick(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> INFERNALPICKAXE = ITEMS.register("infernalpickaxe", () -> new InfernalPickaxe(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> INFERNALSWORD = ITEMS.register("infernalsword", () -> new InfernalSword(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> MAGMACOAL = ITEMS.register("magmacoal", () -> new MagmaCoal(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> INFERNALBOOTS = ITEMS.register("infernalboots", () -> new InfernalBoots(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COPPERPICKAXE= ITEMS.register("copperpickaxe", () -> new CopperPickaxe(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPERSWORD= ITEMS.register("coppersword", () -> new CopperSword(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> COPPERDRILLBIT= ITEMS.register("copperdrillbit", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL= ITEMS.register("steel", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BRONZE= ITEMS.register("bronze", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STONEGEAR= ITEMS.register("stonegear", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static final RegistryObject<Item> FERROUSALLOY= ITEMS.register("ferrousalloy", () -> new FerrousAlloy(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FUELCELL= ITEMS.register("fuelcell", () -> new FuelCell(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COPPERHANDDRILL= ITEMS.register("copperhanddrill", () -> new CopperHanddrill(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COPPERGLOVES= ITEMS.register("coppergloves", () -> new CopperGloves(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    //blocks:
    public static final RegistryObject<Block> BLOCKBURNER = BLOCKS.register("blockburner",BlockBurner::new);
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

    public static final RegistryObject<Block> COPPERDRILL= BLOCKS.register("copperdrill", CopperDrill::new);
    public static final RegistryObject<Block> HYDROPONICS= BLOCKS.register("hydroponics", Hydroponics::new);
    public static final RegistryObject<Block> BASICFRAME= BLOCKS.register("basicframe", BasicFrame::new);
    public static final RegistryObject<Block> ALLOYSMELTER= BLOCKS.register("alloysmelter", AlloySmelter::new);
    public static final RegistryObject<Block> BASICCONDUIT= BLOCKS.register("basicconduit", BasicConduit::new);
    public static final RegistryObject<Block> WATERMILL= BLOCKS.register("watermill", WaterMill::new);
    public static final RegistryObject<Block> CARBONINFUSER= BLOCKS.register("carboninfuser", CarbonInfuser::new);
    public static final RegistryObject<Block> CRANK= BLOCKS.register("crank", Crank::new);
    public static final RegistryObject<Block> SIMPLEBEACON= BLOCKS.register("simplebeacon", SimpleBeacon::new);
    public static final RegistryObject<Block> GARDEN= BLOCKS.register("garden", Garden::new);

    public static final RegistryObject<Block> FUELPROCESSOR= BLOCKS.register("fuelprocessor", FuelProcessor::new);
    public static final RegistryObject<Block> FUELCOMP= BLOCKS.register("fuelcomp", FuelComp::new);
    //reactor blocks:
    public static final RegistryObject<Block> REACTORGLASS= BLOCKS.register("reactorglass", ReactorGlass::new);
    public static final RegistryObject<Block> REACTORFRAME= BLOCKS.register("reactorframe", ReactorFrame::new);
    public static final RegistryObject<Block> REACTORPANEL= BLOCKS.register("reactorpanel", ReactorPanel::new);
    public static final RegistryObject<Block> REACTORCORE= BLOCKS.register("reactorcore", ReactorCore::new);
    public static final RegistryObject<Block> REACTORPORT= BLOCKS.register("reactorport", ReactorPort::new);

    public static final RegistryObject<Block> IRONDRILL= BLOCKS.register("irondrill", IronDrill::new);
    public static final RegistryObject<Block> ADVANCEDBEACON= BLOCKS.register("advancedbeacon", AdvancedBeacon::new);
    public static final RegistryObject<Block> BEACONCOMP= BLOCKS.register("beaconcomp", BeaconComp::new);


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
    public static final RegistryObject<Item> COPPERDRILL_ITEM = ITEMS.register("copperdrill", () -> new BlockItem(COPPERDRILL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> HYDROPONICS_ITEM = ITEMS.register("hydroponics", () -> new BlockItem(HYDROPONICS.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BASICFRAME_ITEM = ITEMS.register("basicframe", () -> new BlockItem(BASICFRAME.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ALLOYSMELTER_ITEM = ITEMS.register("alloysmelter", () -> new BlockItem(ALLOYSMELTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BASICCONDUIT_ITEM = ITEMS.register("basicconduit", () -> new BlockItem(BASICCONDUIT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> WATERMILL_ITEM = ITEMS.register("watermill", () -> new BlockItem(WATERMILL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CARBONINFUSER_ITEM = ITEMS.register("carboninfuser", () -> new BlockItem(CARBONINFUSER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> CRANK_ITEM = ITEMS.register("crank", () -> new BlockItem(CRANK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> SIMPLEBEACON_ITEM = ITEMS.register("simplebeacon", () -> new BlockItem(SIMPLEBEACON.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> GARDEN_ITEM = ITEMS.register("garden", () -> new BlockItem(GARDEN.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> FUELPROCESSOR_ITEM = ITEMS.register("fuelprocessor", () -> new BlockItem(FUELPROCESSOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> FUELCOMP_ITEM = ITEMS.register("fuelcomp", () -> new BlockItem(FUELCOMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> REACTORGLASS_ITEM = ITEMS.register("reactorglass", () -> new BlockItem(REACTORGLASS.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> REACTORFRAME_ITEM = ITEMS.register("reactorframe", () -> new BlockItem(REACTORFRAME.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> REACTORPANEL_ITEM = ITEMS.register("reactorpanel", () -> new BlockItem(REACTORPANEL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> REACTORCORE_ITEM = ITEMS.register("reactorcore", () -> new BlockItem(REACTORCORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> REACTORPORT_ITEM = ITEMS.register("reactorport", () -> new BlockItem(REACTORPORT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> IRONDRILL_ITEM = ITEMS.register("irondrill", () -> new BlockItem(IRONDRILL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> ADVANCEDBEACON_ITEM = ITEMS.register("advancedbeacon", () -> new BlockItem(ADVANCEDBEACON.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> BEACONCOMP_ITEM = ITEMS.register("beaconcomp", () -> new BlockItem(BEACONCOMP.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));


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

    public static final RegistryObject<BlockEntityType<HydroponicsBE>> HYDROPONICS_BE = BLOCKENTITIES.register("hydroponics",
            () -> BlockEntityType.Builder.of(HydroponicsBE::new, HYDROPONICS.get()).build(null));

    public static final RegistryObject<BlockEntityType<CopperDrillBE>> COPPERDRILL_BE = BLOCKENTITIES.register("copperdrill",
            () -> BlockEntityType.Builder.of(CopperDrillBE::new, COPPERDRILL.get()).build(null));

    public static final RegistryObject<BlockEntityType<BasicConduitBE>> BASICCONDUIT_BE = BLOCKENTITIES.register("basicconduit",
            () -> BlockEntityType.Builder.of(BasicConduitBE::new, BASICCONDUIT.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlloySmelterBE>> ALLOYSMELTER_BE = BLOCKENTITIES.register("alloysmelter",
            () -> BlockEntityType.Builder.of(AlloySmelterBE::new, ALLOYSMELTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<WaterMillBE>> WATERMILL_BE = BLOCKENTITIES.register("watermill",
            () -> BlockEntityType.Builder.of(WaterMillBE::new, WATERMILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<CarbonInfuserBE>> CARBONINFUSER_BE = BLOCKENTITIES.register("carboninfuser",
            () -> BlockEntityType.Builder.of(CarbonInfuserBE::new, CARBONINFUSER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrankBE>> CRANK_BE = BLOCKENTITIES.register("crank",
            () -> BlockEntityType.Builder.of(CrankBE::new, CRANK.get()).build(null));
    public static final RegistryObject<BlockEntityType<SimpleBeaconBE>> SIMPLEBEACON_BE = BLOCKENTITIES.register("simplebeacon",
            () -> BlockEntityType.Builder.of(SimpleBeaconBE::new, SIMPLEBEACON.get()).build(null));
    public static final RegistryObject<BlockEntityType<GardenBE>> GARDEN_BE = BLOCKENTITIES.register("garden",
            () -> BlockEntityType.Builder.of(GardenBE::new, GARDEN.get()).build(null));
    public static final RegistryObject<BlockEntityType<FuelProcessorBE>> FUELPROCESSOR_BE = BLOCKENTITIES.register("fuelprocessor",
            () -> BlockEntityType.Builder.of(FuelProcessorBE::new, FUELPROCESSOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<ReactorPanelBE>> REACTORPANEL_BE = BLOCKENTITIES.register("reactorpanel",
            () -> BlockEntityType.Builder.of(ReactorPanelBE::new, REACTORPANEL.get()).build(null));

    public static final RegistryObject<BlockEntityType<ReactorCoreBE>> REACTORCORE_BE = BLOCKENTITIES.register("reactorcore",
            () -> BlockEntityType.Builder.of(ReactorCoreBE::new, REACTORCORE.get()).build(null));
    public static final RegistryObject<BlockEntityType<ReactorPortBE>> REACTORPORT_BE = BLOCKENTITIES.register("reactorport",
            () -> BlockEntityType.Builder.of(ReactorPortBE::new, REACTORPORT.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronDrillBE>> IRONDRILL_BE = BLOCKENTITIES.register("irondrill",
            () -> BlockEntityType.Builder.of(IronDrillBE::new, IRONDRILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<AdvancedBeaconBE>> ADVANCEDBEACON_BE = BLOCKENTITIES.register("advancedbeacon",
            () -> BlockEntityType.Builder.of(AdvancedBeaconBE::new, ADVANCEDBEACON.get()).build(null));


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
    public static final RegistryObject<MenuType<HydroponicsContainer>> HYDROPONICS_CONTAINER = CONTAINERS.register("hydroponics", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new HydroponicsContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CopperDrillContainer>>COPPERDRILL_CONTAINER = CONTAINERS.register("copperdrill", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CopperDrillContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<AlloySmelterContainer>>ALLOYSMELTER_CONTAINER = CONTAINERS.register("alloysmelter", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new AlloySmelterContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<WaterMillContainer>>WATERMILL_CONTAINER = CONTAINERS.register("watermill", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new WaterMillContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CarbonInfuserContainer>>CARBONINFUSER_CONTAINER = CONTAINERS.register("carboninfuser", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CarbonInfuserContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<SimpleBeaconContainer>>SIMPLEBEACON_CONTAINER = CONTAINERS.register("simplebeacon", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new SimpleBeaconContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<GardenContainer>>GARDEN_CONTAINER = CONTAINERS.register("garden", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new GardenContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<FuelProcessorContainer>>FUELPROCESSOR_CONTAINER = CONTAINERS.register("fuelprocessor", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new FuelProcessorContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<ReactorPanelContainer>>REACTORPANEL_CONTAINER = CONTAINERS.register("reactorpanel", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new ReactorPanelContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<IronDrillContainer>>IRONDRILL_CONTAINER = CONTAINERS.register("irondrill", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new IronDrillContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<MenuType<AdvancedBeaconContainer>>ADVANCEDBEACON_CONTAINER = CONTAINERS.register("advancedbeacon", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new AdvancedBeaconContainer(windowId, world, pos, inv, inv.player);
    }));


}
