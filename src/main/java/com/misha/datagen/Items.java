package com.misha.datagen;

import com.misha.lavaplus.LavaPlus;
import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, LavaPlus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(
                Registration.LAVABRICK.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/lavabrick"));
        singleTexture(
                Registration.INFERNALPICKAXE.get().getRegistryName().getPath(),
                new ResourceLocation("item/handheld"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/infernalpickaxe"));
        singleTexture(
                Registration.INFERNALBRICK.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/infernalbrick"));
        singleTexture(
                Registration.INFERNALSWORD.get().getRegistryName().getPath(),
                new ResourceLocation("item/handheld"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/infernalsword"));
        singleTexture(
                Registration.INFERNALBOOTS.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/infernalboots"));

        singleTexture(
                Registration.LAVAGUIDE.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/lavaguide"));

        singleTexture(
                Registration.MAGMACOAL.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/magmacoal"));
        singleTexture(
                Registration.COPPERSWORD.get().getRegistryName().getPath(),
                new ResourceLocation("item/handheld"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/coppersword"));
        singleTexture(
                Registration.COPPERPICKAXE.get().getRegistryName().getPath(),
                new ResourceLocation("item/handheld"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/copperpickaxe"));


        singleTexture(
                Registration.COPPERDRILLBIT.get().getRegistryName().getPath(),
                new ResourceLocation("item/generated"),
                "layer0",
                new ResourceLocation(LavaPlus.MODID, "item/copperdrillbit"));



        withExistingParent(Registration.BLOCKBURNER_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/blockburner"));

        withExistingParent(Registration.MACHINEFRAME_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/machineframe"));

        withExistingParent(Registration.LAVAVENT_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/lavavent"));

        withExistingParent(Registration.COALINFUSER_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/coalinfuser"));
        withExistingParent(Registration.INDUCTIONFURNACE_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/inductionfurnace"));

        withExistingParent(Registration.LAVAGENERATOR_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/lavagenerator"));

        withExistingParent(Registration.HEATEDMAGMABLOCK_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/heatedmagmablock"));

        withExistingParent(Registration.BATTERY_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/battery"));

        withExistingParent(Registration.CENTRIFUGE_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/centrifuge"));

        withExistingParent(Registration.COMPRESSOR_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/compressor"));

        withExistingParent(Registration.UPGRADER.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/upgrader"));

        withExistingParent(Registration.HEATER.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/heater"));

        withExistingParent(Registration.CRATE.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/crate"));

        withExistingParent(Registration.HEALER.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/healer"));

        withExistingParent(Registration.BENBLOCK.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/benblock"));

        withExistingParent(Registration.REINFORCEDFRAME.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/reinforcedframe"));

        withExistingParent(Registration.COPPERDRILL.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/copperdrill"));

        withExistingParent(Registration.BASICFRAME.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/basicframe"));

        withExistingParent(Registration.ALLOYSMELTER.get().getRegistryName().getPath(),
                new ResourceLocation(LavaPlus.MODID, "block/alloysmelter"));





    }
}
