package com.tamashenning.audiomanager;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;

public class GetAudioDevices {

    public static int contextFrequency = 44100;
    public static int contextRefresh = 60;
    public static boolean contextSynchronized = false;

    public static String[] GetAudioDevices() {
        boolean locallyCreated = false;

        if (AL.isCreated() == false || AL.getDevice() == null) {
            try {
                AL.create();
                locallyCreated = true;
            }
            catch (Exception e) {
                if (AudioManager.logger != null)
                    AudioManager.logger.error(e);
                return null;
            }
        }

        String[] devices = null;

        try {
            devices = ALC10.alcGetString(null, ALC11.ALC_ALL_DEVICES_SPECIFIER).split("\0");
            for (String device : devices) {
                if (AudioManager.logger != null)
                    AudioManager.logger.info(device);
            }

        }
        catch (Exception e) {
            if (AudioManager.logger != null)
                AudioManager.logger.error(e);
        }
        finally {
            if (locallyCreated)
                AL.destroy();
        }

        return devices;
    }

    // This always return null per OpenAL docs.
    public static String GetDefaultAudioDevice() {
        return null;
    }

    public static String GetSelectedAudioDevice() {
        return AudioManagerConfig.DefaultAudioDevice;
    }

    public static void SelectAudioDevice(String device) {
        AudioManager.logger.info("Swapping to: " + device);
        AudioManagerConfig.DefaultAudioDevice = device;
        ConfigManager.sync(AudioManager.MODID, Config.Type.INSTANCE);
        Minecraft.getMinecraft().refreshResources();
    }
}
