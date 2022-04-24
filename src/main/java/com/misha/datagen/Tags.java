package com.misha.datagen;

import com.misha.lavaplus.LavaPlus;
import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class Tags extends BlockTagsProvider {

    public Tags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, LavaPlus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.BLOCKBURNER.get())
                .add(Registration.LAVAVENT.get())
                .add(Registration.COALINFUSER.get())
                .add(Registration.COMPRESSOR.get())
                .add(Registration.INDUCTIONFURNACE.get())
                .add(Registration.CENTRIFUGE.get())
                .add(Registration.HEATER.get())
                .add(Registration.HEATEDMAGMABLOCK.get())
                .add(Registration.LAVAGENERATOR.get())
                .add(Registration.CONDUIT.get())
                .add(Registration.BATTERY.get())
                .add(Registration.MACHINEFRAME.get())
                .add(Registration.UPGRADER.get())
                .add(Registration.REINFORCEDFRAME.get())
                .add(Registration.HYDROPONICS.get())
                .add(Registration.CRANK.get())
                .add(Registration.BASICFRAME.get())
                .add(Registration.BASICCONDUIT.get())
                .add(Registration.WATERMILL.get())
                .add(Registration.ALLOYSMELTER.get())
                .add(Registration.COPPERDRILL.get())
                .add(Registration.CARBONINFUSER.get());
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(Registration.LAVAVENT.get())
                .add(Registration.COMPRESSOR.get())
                .add(Registration.INDUCTIONFURNACE.get())
                .add(Registration.CENTRIFUGE.get())
                .add(Registration.HEATER.get())
                .add(Registration.HEATEDMAGMABLOCK.get())
                .add(Registration.LAVAGENERATOR.get())
                .add(Registration.BATTERY.get())
                 .add(Registration.COALINFUSER.get());

    }

    @Override
    public String getName(){
        return "LavaTech tags";
    }
}
