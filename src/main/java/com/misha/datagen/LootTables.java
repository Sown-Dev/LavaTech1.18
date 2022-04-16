package com.misha.datagen;

import com.misha.setup.Registration;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.BLOCKBURNER.get(), createStandardTable("blockburner", Registration.BLOCKBURNER.get()));
        lootTables.put(Registration.LAVAVENT.get(), createStandardTable("lavavent", Registration.LAVAVENT.get()));
        lootTables.put(Registration.MACHINEFRAME.get(), createStandardTable("machineframe", Registration.MACHINEFRAME.get()));
        lootTables.put(Registration.COALINFUSER.get(), createStandardTable("coalinfuser", Registration.COALINFUSER.get()));
        lootTables.put(Registration.INDUCTIONFURNACE.get(), createStandardTable("inductionfurnace", Registration.INDUCTIONFURNACE.get()));
        lootTables.put(Registration.BATTERY.get(), createStandardTable("battery", Registration.BATTERY.get()));
        lootTables.put(Registration.HEATEDMAGMABLOCK.get(), createStandardTable("heatedmagmablock", Registration.HEATEDMAGMABLOCK.get()));
        lootTables.put(Registration.CENTRIFUGE.get(), createStandardTable("centrifuge", Registration.CENTRIFUGE.get()));
        lootTables.put(Registration.LAVAGENERATOR.get(), createStandardTable("lavagenerator", Registration.LAVAGENERATOR.get()));
        lootTables.put(Registration.CONDUIT.get(), createStandardTable("conduit", Registration.CONDUIT.get()));
        lootTables.put(Registration.COMPRESSOR.get(), createStandardTable("compressor", Registration.COMPRESSOR.get()));
        lootTables.put(Registration.HEATER.get(), createStandardTable("heater", Registration.HEATER.get()));
        lootTables.put(Registration.UPGRADER.get(), createStandardTable("conduit", Registration.UPGRADER.get()));
        lootTables.put(Registration.CRATE.get(), createStandardTable("crate", Registration.CRATE.get()));
        lootTables.put(Registration.HEALER.get(), createStandardTable("healer", Registration.HEALER.get()));

    }
}