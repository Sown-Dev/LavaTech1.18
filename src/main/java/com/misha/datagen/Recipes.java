package com.misha.datagen;

import com.misha.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);

    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(Registration.LAVABRICK.get())
                .pattern("xdx")
                .pattern("ccc")
                .pattern("xdx")
                .define('x', Tags.Items.INGOTS_NETHER_BRICK)
                .define('c', Items.COPPER_INGOT)
                .define('d', Tags.Items.GEMS_DIAMOND)
                .group("lavabrick")
                .unlockedBy("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND.asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.BLOCKBURNER.get())
                .pattern("shs")
                .pattern("x x")
                .pattern("sxs")
                .define('s', Tags.Items.STONE)
                .define('x', Registration.MACHINEFRAME.get())
                .define('h', Blocks.HOPPER)
                .group("blockburner")
                .unlockedBy("machineframe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.MACHINEFRAME.get(), 4)
                .pattern(" x ")
                .pattern("xix")
                .pattern(" x ")
                .define('x', Blocks.SMOOTH_STONE)
                .define('i', Tags.Items.INGOTS_IRON)
                .group("machineframe")
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.LAVAVENT.get())
                .pattern("xix")
                .pattern("lol")
                .pattern("xlx")
                .define('x', Registration.MACHINEFRAME.get().asItem())
                .define('l',  Items.COPPER_INGOT)
                .define('o',  Tags.Items.OBSIDIAN)
                .define('i',  Tags.Items.INGOTS_IRON)
                .group("misha")
                .unlockedBy("machineframe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.CONDUIT.get())
                .pattern(" x ")
                .pattern("xix")
                .pattern(" x ")
                .define('x', Tags.Items.DUSTS_REDSTONE)
                .define('i',  Registration.MACHINEFRAME.get().asItem())
                .group("stuff")
                .unlockedBy("machineframe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.HEATEDMAGMABLOCK.get())
                .pattern("cxc")
                .pattern("xix")
                .pattern("cxc")
                .define('x', Blocks.MAGMA_BLOCK)
                .define('i',  Items.DIAMOND)
                .define('c', Registration.MAGMACOAL.get().asItem())
                .group("heatedmagma")
                .unlockedBy("magmacoal", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MAGMACOAL.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.BATTERY.get())
                .pattern(" c ")
                .pattern("mrm")
                .pattern(" c ")
                .define('r', Blocks.REDSTONE_BLOCK)
                .define('c', Registration.CONDUIT.get().asItem())
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("battery")
                .unlockedBy("conduit", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.CONDUIT.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.CENTRIFUGE.get())
                .pattern("mhm")
                .pattern("did")
                .pattern("mhm")
                .define('d', Registration.LAVABRICK.get())
                .define('i', Blocks.IRON_BLOCK)
                .define('h', Registration.HEATEDMAGMABLOCK.get().asItem())
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("battery")
                .unlockedBy("centrifuge", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LAVABRICK.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.COMPRESSOR.get())
                .pattern("mnm")
                .pattern("dbd")
                .pattern("mnm")
                .define('d', Registration.LAVABRICK.get().asItem())
                .define('b', Registration.BLOCKBURNER.get().asItem())
                .define('n', Items.NETHERITE_INGOT)
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("compressor")
                .unlockedBy("lavabrick", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LAVABRICK.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.INDUCTIONFURNACE.get())
                .pattern("mnm")
                .pattern("idi")
                .pattern("mnm")
                .define('n', Registration.MAGMACOAL.get().asItem())
                .define('i', Items.IRON_INGOT)
                .define('d', Items.DIAMOND)
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("inductionfurnace")
                .unlockedBy("magmacoal", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MAGMACOAL.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.UPGRADER.get())
                .pattern("mim")
                .pattern("ini")
                .pattern("mdm")
                .define('d', Registration.LAVABRICK.get().asItem())
                .define('i', Blocks.IRON_BLOCK)
                .define('n', Items.NETHERITE_INGOT)
                .define('m',  Registration.REINFORCEDFRAME.get().asItem())
                .group("upgrader")
                .unlockedBy("lavabrick", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LAVABRICK.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.HEATER.get())
                .pattern("mgm")
                .pattern("chc")
                .pattern("mgm")
                .define('h', Registration.HEATEDMAGMABLOCK.get().asItem())
                .define('c', Blocks.COPPER_BLOCK)
                .define('g', Blocks.TINTED_GLASS)
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("heater")
                .unlockedBy("heatedmagma", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEATEDMAGMABLOCK.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.COALINFUSER.get())
                .pattern("mim")
                .pattern("ici")
                .pattern("mfm")
                .define('c', Blocks.COAL_BLOCK)
                .define('i', Items.IRON_INGOT)
                .define('f', Items.FLINT_AND_STEEL)
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("coalinf")
                .unlockedBy("machineframe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.LAVAGENERATOR.get())
                .pattern("mim")
                .pattern("gfg")
                .pattern("mcm")
                .define('g', Blocks.TINTED_GLASS)
                .define('f', Blocks.BLAST_FURNACE)
                .define('i', Items.REDSTONE)
                .define('c', Items.COPPER_INGOT)
                .define('m',  Registration.MACHINEFRAME.get().asItem())
                .group("lavagen")
                .unlockedBy("machineframe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.CRATE.get())
                .pattern(" s ")
                .pattern("sps")
                .pattern(" s ")
                .define('p', Blocks.OAK_PLANKS)
                .define('s', Items.STICK)
                .group("crate")
                .unlockedBy("stick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.COPPERPICKAXE.get())
                .pattern("ccc")
                .pattern(" s ")
                .pattern(" s ")
                .define('c',Items.COPPER_INGOT)
                .define('s', Items.STICK)
                .group("cpick")
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.COPPERSWORD.get())
                .pattern(" c ")
                .pattern(" c ")
                .pattern(" s ")
                .define('c',Items.COPPER_INGOT)
                .define('s', Items.STICK)
                .group("csowrd")
                .unlockedBy("copper", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.HEALER.get())
                .pattern("mdm")
                .pattern("sgs")
                .pattern("msm")
                .define('d', Blocks.DISPENSER)
                .define('g', Items.GOLDEN_APPLE)
                .define('s', Items.IRON_INGOT)
                .define('m', Registration.MACHINEFRAME.get().asItem())
                .group("healer")
                .unlockedBy("frame", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.REINFORCEDFRAME.get(), 2)
                .pattern("gmg")
                .pattern("mom")
                .pattern("gmg")
                .define('o', Blocks.OBSIDIAN)
                .define('g', Items.GOLD_INGOT)
                .define('m', Registration.MACHINEFRAME.get().asItem())
                .group("frame")
                .unlockedBy("obsidian", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.OBSIDIAN.asItem()))
                .save(consumer);

                /* no
        ShapedRecipeBuilder.shaped(Registration.BENBLOCK.get())
                .pattern(" b ")
                .pattern(" b ")
                .pattern(" b ")
                .define('b', Items.EGG.asItem())
                .define('b', Blocks.LIME_STAINED_GLASS_PANE.asItem())
                .define('c', Blocks.ZOMBIE_HEAD.asItem())
                .define('d', Items.COPPER_INGOT.asItem())
                .define('e', Blocks.TNT.asItem())
                .define('f', Items.ELYTRA.asItem())
                .define('g', Items.MAGENTA_BANNER.asItem())
                .define('h', Items.CROSSBOW.asItem())
                //.define('i', Registration.BENBLOCK.get().asItem())
                .group("bblock")
                .unlockedBy("frame", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MACHINEFRAME.get().asItem()))
                .save(consumer);
*/



    }
}