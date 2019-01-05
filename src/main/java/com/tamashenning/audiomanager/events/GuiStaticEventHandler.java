package com.tamashenning.audiomanager.events;

import com.tamashenning.audiomanager.AudioManager;
import com.tamashenning.audiomanager.guis.GuiAudioDevices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class GuiStaticEventHandler {

    public static int audioButtonID = 978855;

    @SubscribeEvent
    public static void onModifyGuiEvent(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() != null && event.getGui() instanceof GuiOptions) {
            GuiOptions gui = (GuiOptions)event.getGui();
            List<GuiButton> buttons = event.getButtonList();

            GuiButton button = new GuiButton(audioButtonID, gui.width / 2 + 5, gui.height / 6 + 144 - 6, 150, 20, "Audio Devices");
            buttons.add(button);

            event.setButtonList(buttons);
        }
    }

    @SubscribeEvent
    public static void onModifiedGuiButtonClickedEvent(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.getGui() != null && event.getGui() instanceof GuiOptions) {
            if (event.getButton().id == audioButtonID) {
                GuiAudioDevices gui = new GuiAudioDevices(event.getGui());
                Minecraft.getMinecraft().displayGuiScreen(gui);
            }
        }
    }
}
