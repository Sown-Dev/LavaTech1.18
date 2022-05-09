package com.misha.datagen;

import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);

    }

    @Override
    protected void addTables() {


        lootTables.put(Registration.BLOCKBURNER.get(), createStandardTable("blockburner", Registration.BLOCKBURNER.get(), Registration.BLOCKBURNER_BE.get()));
        lootTables.put(Registration.LAVAVENT.get(), createStandardTable("lavavent", Registration.LAVAVENT.get(), Registration.LAVAVENT_BE.get()));
        lootTables.put(Registration.COALINFUSER.get(), createStandardTable("coalinfuser", Registration.COALINFUSER.get(), Registration.COALINFUSER_BE.get()));
        lootTables.put(Registration.INDUCTIONFURNACE.get(), createStandardTable("inductionfurnace", Registration.INDUCTIONFURNACE.get(), Registration.INDUCTIONFURNACE_BE.get()));
        lootTables.put(Registration.BATTERY.get(), createStandardTable("battery", Registration.BATTERY.get(), Registration.BATTERY_BE.get()));
        lootTables.put(Registration.CENTRIFUGE.get(), createStandardTable("centrifuge", Registration.CENTRIFUGE.get(), Registration.CENTRIFUGE_BE.get()));
        lootTables.put(Registration.LAVAGENERATOR.get(), createStandardTable("lavagenerator", Registration.LAVAGENERATOR.get(), Registration.LAVAGENERATOR_BE.get()));
        lootTables.put(Registration.CONDUIT.get(), createStandardTable("conduit", Registration.CONDUIT.get()));
        lootTables.put(Registration.COMPRESSOR.get(), createStandardTable("compressor", Registration.COMPRESSOR.get(), Registration.COMPRESSOR_BE.get()));
        lootTables.put(Registration.HEATER.get(), createStandardTable("heater", Registration.HEATER.get(), Registration.HEATER_BE.get()));
        lootTables.put(Registration.UPGRADER.get(), createStandardTable("conduit", Registration.UPGRADER.get(), Registration.UPGRADER_BE.get()));
        lootTables.put(Registration.CRATE.get(), createStandardTable("crate", Registration.CRATE.get(), Registration.CRATE_BE.get()));
        lootTables.put(Registration.HEALER.get(), createStandardTable("healer", Registration.HEALER.get(), Registration.HEALER_BE.get()));

        lootTables.put(Registration.BASICCONDUIT.get(), createStandardTable("basicconduit", Registration.BASICCONDUIT.get()));
        lootTables.put(Registration.HYDROPONICS.get(), createStandardTable("hydroponics", Registration.HYDROPONICS.get(), Registration.HYDROPONICS_BE.get()));
        lootTables.put(Registration.CRANK.get(), createStandardTable("crank", Registration.CRANK.get(), Registration.CRANK_BE.get()));
        lootTables.put(Registration.COPPERDRILL.get(), createStandardTable("copperdrill", Registration.COPPERDRILL.get(), Registration.COPPERDRILL_BE.get()));
        lootTables.put(Registration.CARBONINFUSER.get(), createStandardTable("carboninfuser", Registration.CARBONINFUSER.get(), Registration.CARBONINFUSER_BE.get()));
        lootTables.put(Registration.ALLOYSMELTER.get(), createStandardTable("alloysmelter", Registration.ALLOYSMELTER.get(), Registration.ALLOYSMELTER_BE.get()));
        lootTables.put(Registration.WATERMILL.get(), createStandardTable("watermill", Registration.WATERMILL.get(), Registration.WATERMILL_BE.get()));
        lootTables.put(Registration.SIMPLEBEACON.get(), createStandardTable("simplebeacon", Registration.SIMPLEBEACON.get(), Registration.SIMPLEBEACON_BE.get()));

        lootTables.put(Registration.FUELPROCESSOR.get(), createStandardTable("fuelprocessor", Registration.FUELPROCESSOR.get(), Registration.FUELPROCESSOR_BE.get()));
        lootTables.put(Registration.REACTORPORT.get(), createStandardTable("reactorport", Registration.REACTORPORT.get(), Registration.REACTORPORT_BE.get()));
        lootTables.put(Registration.REACTORCORE.get(), createStandardTable("reactorcore", Registration.REACTORCORE.get(), Registration.REACTORCORE_BE.get()));
        lootTables.put(Registration.REACTORPANEL.get(), createStandardTable("reactorpanel", Registration.REACTORPANEL.get(), Registration.REACTORPANEL_BE.get()));
        lootTables.put(Registration.ADVANCEDBEACON.get(), createStandardTable("advancedbeacon", Registration.ADVANCEDBEACON.get(), Registration.ADVANCEDBEACON_BE.get()));
        lootTables.put(Registration.IRONDRILL.get(), createStandardTable("irondrill", Registration.IRONDRILL.get(), Registration.IRONDRILL_BE.get()));



        //blocks
        lootTables.put(Registration.HEATEDMAGMABLOCK.get(), createStandardTable("heatedmagmablock", Registration.HEATEDMAGMABLOCK.get()));
        lootTables.put(Registration.MACHINEFRAME.get(), createStandardTable("machineframe", Registration.MACHINEFRAME.get()));
        lootTables.put(Registration.BASICFRAME.get(), createStandardTable("basicframe", Registration.BASICFRAME.get()));
        lootTables.put(Registration.REINFORCEDFRAME.get(), createStandardTable("reinforcedframe", Registration.REINFORCEDFRAME.get()));

        lootTables.put(Registration.REACTORFRAME.get(), createStandardTable("reactorframe", Registration.REACTORFRAME.get()));
        lootTables.put(Registration.REACTORGLASS.get(), createStandardTable("reactorglass", Registration.REACTORGLASS.get()));
        lootTables.put(Registration.FUELCOMP.get(), createStandardTable("fuelcomp", Registration.FUELCOMP.get()));
        lootTables.put(Registration.BEACONCOMP.get(), createStandardTable("beaconcomp", Registration.BEACONCOMP.get()));


    }
}