package com.tamashenning.audiomanager;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AudioManager.MODID)
public class AudioManagerConfig {

    @Config.Comment("Default Audio device for Minecraft when starting up")
    public static String DefaultAudioDevice = "";

    @Mod.EventBusSubscriber(modid = AudioManager.MODID)
    private static class Handler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {

            if (event.getModID().equals(AudioManager.MODID)) {
                ConfigManager.sync(AudioManager.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
