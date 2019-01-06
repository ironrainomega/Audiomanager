package com.tamashenning.audiomanager.guis;

import java.io.IOException;

import com.tamashenning.audiomanager.AudioManager;
import com.tamashenning.audiomanager.GetAudioDevices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAudioDevices extends GuiScreen
{
    /** A reference to the screen object that created this. Used for navigating between screens. */
    private final GuiScreen parentScreen;
    protected String screenTitle = "Audio Devices";
    /** The ID of the button that has been pressed. */
    public String device;
    public GuiButton button;
    public long time;
    private GuiAudioDevicesList audioDevicesList;

    public GuiAudioDevices(GuiScreen screen)
    {
        this.parentScreen = screen;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.audioDevicesList = new GuiAudioDevicesList(this, this.mc);
        this.buttonList.add(new GuiButton(200, this.width / 2 - 155 + 160, this.height - 29, 150, 20, I18n.format("gui.done")));

        this.screenTitle = "Audio devices";
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.audioDevicesList.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 200)
        {
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (mouseButton != 0 || !this.audioDevicesList.mouseClicked(mouseX, mouseY, mouseButton))
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
        else if (this.button != null)
        {
            if (this.device.equals("Default")) {
                GetAudioDevices.SelectAudioDevice("");
            }
            else {
                GetAudioDevices.SelectAudioDevice(this.device);
            }

            this.button.displayString = TextFormatting.YELLOW + this.device;
            this.updateScreen();
        }
        else if (mouseButton != 0 || !this.audioDevicesList.mouseClicked(mouseX, mouseY, mouseButton))
        {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    /**
     * Called when a mouse button is released.
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state != 0 || !this.audioDevicesList.mouseReleased(mouseX, mouseY, state))
        {
            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.audioDevicesList.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 8, 16777215);
        boolean flag = false;

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}