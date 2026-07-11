package com.seosean.zombieshologramsbugfix;

import com.seosean.zombieshologramsbugfix.config.ZhfConfig;
import com.seosean.zombieshologramsbugfix.config.ZhfConfigScreen;
import com.seosean.zombieshologramsbugfix.renderer.ZhfHudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class ZombiesHologramsbugFixClient implements ClientModInitializer {
    public static final String MOD_ID = "zombieshologramsbugfix";
    public static boolean toggleZHF;
    public static ZhfConfig config;
    private static KeyMapping toggleKey;
    private static KeyMapping configKey;

    @Override
    public void onInitializeClient() {
        config = ZhfConfig.load();
        toggleKey = KeyBindingHelper.registerKeyBinding(KeyBindingCompat.create(
                "key.zombieshologramsbugfix.toggle", GLFW.GLFW_KEY_Z));
        configKey = KeyBindingHelper.registerKeyBinding(KeyBindingCompat.create(
                "key.zombieshologramsbugfix.config", GLFW.GLFW_KEY_UNKNOWN));
        ClientTickEvents.START_CLIENT_TICK.register(ZombiesHologramsbugFixClient::onClientTick);
        HudRenderCallback.EVENT.register(ZhfHudRenderer::render);
    }

    private static void onClientTick(Minecraft client) {
        DelayedTask.tick();
        if (client.screen != null) return;
        while (toggleKey.consumeClick()) {
            toggleZHF = !toggleZHF;
            client.hitResult = null;
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal("Toggled ZHF: ").withStyle(ChatFormatting.YELLOW)
                        .append(Component.literal(toggleZHF ? "ON" : "OFF")
                                .withStyle(toggleZHF ? ChatFormatting.GREEN : ChatFormatting.RED)), false);
            }
        }
        while (configKey.consumeClick()) {
            DelayedTask.runLater(2, () -> client.setScreen(ZhfConfigScreen.create(null)));
        }
    }
}
