package com.seosean.zombieshologramsbugfix.renderer;

import com.seosean.zombieshologramsbugfix.ZombiesHologramsbugFixClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.DeltaTracker;

public final class ZhfHudRenderer {
    private ZhfHudRenderer() {}

    public static void render(GuiGraphics graphics, DeltaTracker tickCounter) {
        Minecraft client = Minecraft.getInstance();
        int labelX = graphics.guiWidth() - client.font.width("ZHF:  OFF");
        int stateX = graphics.guiWidth() - client.font.width("OFF");
        graphics.drawString(client.font, "ZHF:", labelX, 1, 0xFFFFFF55, false);
        graphics.drawString(client.font, ZombiesHologramsbugFixClient.toggleZHF ? "ON" : "OFF",
                stateX, 1, ZombiesHologramsbugFixClient.toggleZHF ? 0xFF55FF55 : 0xFFFF5555, false);
    }
}
