package com.misha.datagen;

import com.misha.lavaplus.LavaPlus;
import com.misha.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {


    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LavaPlus.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        registerBlockBurnerBlock();
        registerLavaVentBlock();
        registerCoalInfuserBlock();
        registerInductionFurnaceBlock();
        registerLavaGeneratorBlock();
        registerBatteryBlock();
        registerCentrifugeBlock();
        registerCompressorBlock();
        registerHealerBlock();
        registerUpgraderBlock();
        registerCopperDrillBlock();
        simpleBlock(Registration.MACHINEFRAME.get());
        simpleBlock(Registration.HEATEDMAGMABLOCK.get());
        simpleBlock(Registration.HEATER.get());
        simpleBlock(Registration.CRATE.get());
        simpleBlock(Registration.BENBLOCK.get());
        simpleBlock(Registration.REINFORCEDFRAME.get());
        simpleBlock(Registration.BASICFRAME.get());
        registerBlock("alloysmelter",Registration.ALLOYSMELTER.get());

    }

    private void registerUpgraderBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/upgrader_fronton");
        ResourceLocation sideon = side;
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/machineframe");
        BlockModelBuilder modelUpgrader = models().cube("upgrader", top, top, new ResourceLocation(LavaPlus.MODID, "block/upgrader_fronton"), side,side,side )
                .texture("particle",side);
        BlockModelBuilder modelUpgraderPowered = models().cube("upgrader_on", top, top, new ResourceLocation(LavaPlus.MODID, "block/upgrader_fronton"), sideon,sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.UPGRADER.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelUpgraderPowered;
            } else {
                return modelUpgrader;
            }
        });
    }
    private void registerCopperDrillBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/copperdrill_side");
        ResourceLocation sideon = side;
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/copperdrill_top");
        BlockModelBuilder modelUpgrader = models().cube("copperdrill", top, top, side, side,side,side )
                .texture("particle",side);
        BlockModelBuilder modelUpgraderPowered = models().cube("copperdrill_on", top, top, sideon, sideon,sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.COPPERDRILL.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelUpgraderPowered;
            } else {
                return modelUpgrader;
            }
        });
    }

    private void registerHealerBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/healer_side");
        ResourceLocation sideon = side;
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/healer_top");
        BlockModelBuilder modelHealer = models().cube("healer", top, top, side, side,side,side )
                .texture("particle",side);

        BlockModelBuilder modelHealerPowered = models().cube("healer_on", top, top, sideon, sideon,sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.HEALER.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelHealerPowered;
            } else {
                return modelHealer;
            }
        });
    }

    private void registerCentrifugeBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/centrifuge_sideon");
        ResourceLocation sideon = new ResourceLocation(LavaPlus.MODID, "block/centrifuge_sideon");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/centrifuge_topon");
        BlockModelBuilder modelCentrifuge = models().cube("centrifuge", top, top, new ResourceLocation(LavaPlus.MODID, "block/centrifuge_fronton"), side,side,side )
                .texture("particle",side);
        BlockModelBuilder modelCentrifugePowered = models().cube("centrifuge_fronton", top, top, new ResourceLocation(LavaPlus.MODID, "block/centrifuge_fronton"), sideon,sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.CENTRIFUGE.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelCentrifugePowered;
            } else {
                return modelCentrifuge;
            }
        });
    }
    private void registerCompressorBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/compressor_front");
        ResourceLocation sideon = new ResourceLocation(LavaPlus.MODID, "block/compressor_front");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/machineframe");
        BlockModelBuilder modelCompressor = models().cube("compressor", top, top, new ResourceLocation(LavaPlus.MODID, "block/compressor_front"), side,side,side )
                .texture("particle",side);
        BlockModelBuilder modelCompressorPowered = models().cube("compressor_fronton", top, top, new ResourceLocation(LavaPlus.MODID, "block/compressor_front"), sideon,sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.COMPRESSOR.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelCompressorPowered;
            } else {
                return modelCompressor;
            }
        });
    }
    private void registerBlockBurnerBlock() {
        ResourceLocation txt = new ResourceLocation(LavaPlus.MODID, "block/blockburner_side");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/blockburner_top");
        BlockModelBuilder modelBlockBurner = models().cube("blockburner", top, top, new ResourceLocation(LavaPlus.MODID, "block/blockburner_front"), txt, txt, txt)
                .texture("particle",txt);
        BlockModelBuilder modelBlockBurnerPowered = models().cube("blockburner_fronton", top, top, new ResourceLocation(LavaPlus.MODID, "block/blockburner_fronton"), txt, txt, txt)
        .texture("particle",txt);
        orientedBlock(Registration.BLOCKBURNER.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelBlockBurnerPowered;
            } else {
                return modelBlockBurner;
            }
        });
    }

    private void registerLavaGeneratorBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/lavagenerator_side");
        ResourceLocation sideon = new ResourceLocation(LavaPlus.MODID, "block/lavagenerator_sideon");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/lavagenerator_top");
        BlockModelBuilder modelLavaGenerator = models().cube("lavagenerator", top, top, side, side,side, side)
                .texture("particle",side);
        BlockModelBuilder modelLavaGeneratorPowered = models().cube("lavagenerator_sideon", top, top,sideon, sideon, sideon, sideon)
                .texture("particle",side);
        orientedBlock(Registration.LAVAGENERATOR.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelLavaGeneratorPowered;
            } else {
                return modelLavaGenerator;
            }
        });
    }
    private void registerBatteryBlock() {
        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/battery_side");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/battery_top");

        BlockModelBuilder modelBattery = models().cube("battery", top, top, side, side,side, side)
                .texture("particle",side);
        BlockModelBuilder modelBatteryPowered = models().cube("battery_sideon", top, top,side, side, side, side)
            .texture("particle",side);
        orientedBlock(Registration.BATTERY.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelBatteryPowered;
            } else {
                return modelBattery;
            }
        });
    }
    private void registerLavaVentBlock() {

        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/lavavent_side");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/lavavent_top");
        BlockModelBuilder modelLavaVent = models().cube("lavavent", top,new ResourceLocation(LavaPlus.MODID, "block/lavavent_top"),side, side, side, side)
                .texture("particle",side);
        BlockModelBuilder modelLavaVentPowered = models().cube("lavavent_topon", top,new ResourceLocation(LavaPlus.MODID, "block/lavavent_topon"), side, side, side,side)
                .texture("particle",side);
        orientedBlock(Registration.LAVAVENT.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelLavaVentPowered;
            } else {
                return modelLavaVent;
            }
        });
    }
    private void registerCoalInfuserBlock() {

        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_side");
        ResourceLocation sideon = new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_sideon");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_top");
        BlockModelBuilder modelCoalInfuser = models().cube("coalinfuser", top,top,new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_front"), side, side, side)
                .texture("particle",side);
        BlockModelBuilder modelCoalInfuserPowered = models().cube("coalinfuser_fronton", top,top, new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_fronton"), sideon, sideon,sideon)
                .texture("particle",side);
        orientedBlock(Registration.COALINFUSER.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelCoalInfuserPowered;
            } else {
                return modelCoalInfuser;
            }
        });
    }

    private void registerInductionFurnaceBlock() {

        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/inductionfurnace_side");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/coalinfuser_top");
        BlockModelBuilder modelInductionFurnace = models().cube("inductionfurnace", top,top,new ResourceLocation(LavaPlus.MODID, "block/inductionfurnace_front"), side, side, side)
                .texture("particle",side);
        BlockModelBuilder modelInductionFurnacePowered = models().cube("inductionfurnace_fronton", top,top, new ResourceLocation(LavaPlus.MODID, "block/inductionfurnace_fronton"), side, side,side)
                .texture("particle",side);
        orientedBlock(Registration.INDUCTIONFURNACE.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelInductionFurnacePowered;
            } else {
                return modelInductionFurnace;
            }
        });
    }

    private void registerBlock(String name, Block block) {

        ResourceLocation side = new ResourceLocation(LavaPlus.MODID, "block/"+name+"_side");
        ResourceLocation top= new ResourceLocation(LavaPlus.MODID, "block/"+name+"_top");
        BlockModelBuilder modelBlock= models().cube(name, top,top,new ResourceLocation(LavaPlus.MODID, "block/"+name+"_front"), side, side, side)
                .texture("particle",side);
        BlockModelBuilder modelBlockPowered = models().cube(name+"on", top,top, new ResourceLocation(LavaPlus.MODID, "block/"+name+"_fronton"), side, side,side)
                .texture("particle",side);
        orientedBlock(block, state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelBlockPowered;
            } else {
                return modelBlock;
            }
        });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}
