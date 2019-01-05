package com.tamashenning.audiomanager.proxies;

import com.tamashenning.audiomanager.AudioManager;
import com.tamashenning.audiomanager.SoundEventsStaticHandler;
import com.tamashenning.audiomanager.events.GuiStaticEventHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.swing.text.JTextComponent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(SoundEventsStaticHandler.class);
        MinecraftForge.EVENT_BUS.register(GuiStaticEventHandler.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ConfigManager.sync(AudioManager.MODID, Config.Type.INSTANCE);
    }

}
