package com.tamashenning.audiomanager;

import com.tamashenning.audiomanager.proxies.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;

@Mod(modid = AudioManager.MODID, name = AudioManager.NAME, version = AudioManager.VERSION, acceptableRemoteVersions = "*", clientSideOnly = true)
public class AudioManager
{
    public static final String MODID = "audiomanager";
    public static final String NAME = "Audio Manager";
    public static final String VERSION = "0.0.2";

    public static Logger logger;

    @Mod.Instance(AudioManager.MODID)
    public static AudioManager instance;

    @SidedProxy(clientSide = "com.tamashenning.audiomanager.proxies.ClientProxy", serverSide = "com.tamashenning.audiomanager.proxies.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
