package com.tamashenning.audiomanager;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundSetupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundEventsStaticHandler {

    @SubscribeEvent
    public static void onSoundSetupEvent(SoundSetupEvent event) {
        try {
            SoundSystemConfig.removeLibrary(LibraryLWJGLOpenAL.class);
            SoundSystemConfig.addLibrary(DeviceLibraryOpenAL.class);
            SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
        }
        catch (Exception e) {
            AudioManager.logger.error(e);
        }
        /*if (AL.isCreated() == false || AL.getDevice() == null) {
            try {
                AL.create();
            }
            catch (Exception e) {
                return;
            }
        }

        String[] devices = ALC10.alcGetString(null, ALC11.ALC_ALL_DEVICES_SPECIFIER).split("\0");
        for (String device : devices) {
            AudioManager.logger.info(device);
        }
        AL.destroy();

        String device = devices[1];
        int contextFrequency = 44100;
        int contextRefresh = 60;
        boolean contextSynchronized = false;

        try {
            AL.create(device, contextFrequency, contextRefresh, contextSynchronized);
            event.getManager().reloadSoundSystem();
        }
        catch (Exception e) {
            AudioManager.logger.error(e);
        }*/
    }
}
