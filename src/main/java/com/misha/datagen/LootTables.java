package com.misha.datagen;

import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {

        lootTables.put(Registration.MACHINEFRAME.get(), createStandardTable("machineframe", Registration.MACHINEFRAME.get(), Registration.BLOCKBURNER_BE.get()));


        lootTables.put(Registration.BLOCKBURNER.get(), createStandardTable("blockburner", Registration.BLOCKBURNER.get(), Registration.BLOCKBURNER_BE.get()));
        lootTables.put(Registration.LAVAVENT.get(), createStandardTable("lavavent", Registration.LAVAVENT.get(), Registration.LAVAVENT_BE.get()));
        lootTables.put(Registration.COALINFUSER.get(), createStandardTable("coalinfuser", Registration.COALINFUSER.get(), Registration.COALINFUSER_BE.get()));
        lootTables.put(Registration.INDUCTIONFURNACE.get(), createStandardTable("inductionfurnace", Registration.INDUCTIONFURNACE.get(), Registration.INDUCTIONFURNACE_BE.get()));
        lootTables.put(Registration.BATTERY.get(), createStandardTable("battery", Registration.BATTERY.get(), Registration.BATTERY_BE.get()));
        lootTables.put(Registration.CENTRIFUGE.get(), createStandardTable("centrifuge", Registration.CENTRIFUGE.get(), Registration.CENTRIFUGE_BE.get()));
        lootTables.put(Registration.LAVAGENERATOR.get(), createStandardTable("lavagenerator", Registration.LAVAGENERATOR.get(), Registration.LAVAGENERATOR_BE.get()));
        lootTables.put(Registration.CONDUIT.get(), createStandardTable("conduit", Registration.CONDUIT.get(), Registration.CONDUIT_BE.get()));
        lootTables.put(Registration.COMPRESSOR.get(), createStandardTable("compressor", Registration.COMPRESSOR.get(), Registration.COMPRESSOR_BE.get()));
        lootTables.put(Registration.HEATER.get(), createStandardTable("heater", Registration.HEATER.get(), Registration.HEATER_BE.get()));
        lootTables.put(Registration.UPGRADER.get(), createStandardTable("conduit", Registration.UPGRADER.get(), Registration.UPGRADER_BE.get()));
        lootTables.put(Registration.CRATE.get(), createStandardTable("crate", Registration.CRATE.get(), Registration.CRATE_BE.get()));
        lootTables.put(Registration.HEALER.get(), createStandardTable("healer", Registration.HEALER.get(), Registration.HEALER_BE.get()));

    }
}