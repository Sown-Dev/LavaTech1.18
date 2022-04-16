package com.misha.datagen;


import com.misha.lavaplus.LavaPlus;
import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid= "lavaplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()){

            generator.addProvider(new Recipes(generator));
            generator.addProvider(new LootTables(generator));
            generator.addProvider(new Tags(generator, event.getExistingFileHelper()));
        }

        if (event.includeClient()){
           generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
           generator.addProvider(new Items(generator, event.getExistingFileHelper()));

        }
    }
}
