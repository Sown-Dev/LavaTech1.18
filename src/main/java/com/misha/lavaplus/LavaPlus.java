package com.misha.lavaplus;

import com.misha.setup.ClientSetup;
import com.misha.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LavaPlus.MODID)
public class LavaPlus
{
    public static final String MODID = "lavaplus";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public LavaPlus() {
        Registration.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(ClientSetup::setup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void enqueueIMC(final InterModEnqueueEvent event){
        /*InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.HANDS.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                ()-> SlotTypePreset.NECKLACE.getMessageBuilder().build());*/
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }




}
