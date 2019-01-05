package com.tamashenning.audiomanager.guis;

import com.tamashenning.audiomanager.GetAudioDevices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class GuiAudioDevicesList extends GuiListExtended {

    private final GuiAudioDevices devicesScreen;
    private final Minecraft mc;
    private final GuiListExtended.IGuiListEntry[] listEntries;
    private int maxListLabelWidth;

    public GuiAudioDevicesList(GuiAudioDevices controls, Minecraft mcIn)
    {
        super(mcIn, controls.width + 45, controls.height, 63, controls.height - 32, 20);
        this.devicesScreen = controls;
        this.mc = mcIn;

        String[] devices = GetAudioDevices.GetAudioDevices();
        this.listEntries = new GuiListExtended.IGuiListEntry[devices.length];

        int i = 0;
        String s = null;

        for (String device : devices)
        {
            int j = mcIn.fontRenderer.getStringWidth(device);

            if (j > this.maxListLabelWidth)
            {
                this.maxListLabelWidth = j;
            }

            this.listEntries[i++] = new GuiAudioDevicesList.AudioEntry(device);
        }
    }

    protected int getSize()
    {
        return this.listEntries.length;
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public GuiListExtended.IGuiListEntry getListEntry(int index)
    {
        return this.listEntries[index];
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 35;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 32;
    }

    @SideOnly(Side.CLIENT)
    public class AudioEntry implements GuiListExtended.IGuiListEntry
    {
        /** The keybinding specified for this KeyEntry */
        private final String deviceName;
        /** The localized key description for this KeyEntry */
        private final GuiButton btnChangeAudioDevice;

        private AudioEntry(String name)
        {
            this.deviceName = name;
            this.btnChangeAudioDevice = new GuiButton(0, 0, 0, 95, 20, "Select");

        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks)
        {
            int currentTextWidth = GuiAudioDevicesList.this.mc.fontRenderer.getStringWidth(this.deviceName);
            // right align
            GuiAudioDevicesList.this.mc.fontRenderer.drawString(this.deviceName, x + 90 - GuiAudioDevicesList.this.maxListLabelWidth + (GuiAudioDevicesList.this.maxListLabelWidth - currentTextWidth), y + slotHeight / 2 - GuiAudioDevicesList.this.mc.fontRenderer.FONT_HEIGHT / 2, 16777215);

            this.btnChangeAudioDevice.x = x + 105;
            this.btnChangeAudioDevice.y = y;

            this.btnChangeAudioDevice.displayString = TextFormatting.WHITE + "Select";

            if (this.deviceName.equals(GetAudioDevices.GetSelectedAudioDevice()))
                this.btnChangeAudioDevice.displayString = TextFormatting.YELLOW + "Select";

            this.btnChangeAudioDevice.drawButton(GuiAudioDevicesList.this.mc, mouseX, mouseY, partialTicks);

        }

        /**
         * Called when the mouse is clicked within this entry. Returning true means that something within this entry was
         * clicked and the list should not be dragged.
         */
        public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY)
        {
            if (this.btnChangeAudioDevice.mousePressed(GuiAudioDevicesList.this.mc, mouseX, mouseY))
            {
                GuiAudioDevicesList.this.devicesScreen.device = this.deviceName;
                GuiAudioDevicesList.this.devicesScreen.button = this.btnChangeAudioDevice;
                return true;
            }
            else
            {
                return false;
            }
        }

        /**
         * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
         */
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.btnChangeAudioDevice.mouseReleased(x, y);
        }

        public void updatePosition(int slotIndex, int x, int y, float partialTicks)
        {
        }
    }
}
