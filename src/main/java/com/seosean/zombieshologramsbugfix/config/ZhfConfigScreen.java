package com.seosean.zombieshologramsbugfix.config;

import com.seosean.zombieshologramsbugfix.ZombiesHologramsbugFixClient;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ZhfConfigScreen {
    private ZhfConfigScreen() {}

    public static Screen create(Screen parent) {
        ZhfConfig config = ZombiesHologramsbugFixClient.config;
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("ZHF Configuration"));
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));
        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Component.literal("(DANGER) Ignore Block Reactions"), config.ignoreBlockReactions)
                .setDefaultValue(false)
                .setTooltip(Component.literal("Ignore Block Reactions"))
                .setSaveConsumer(value -> config.ignoreBlockReactions = value)
                .build());
        general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Component.literal("Disable Right Click Swinging"), config.disableRightClickSwinging)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Disable Right Click Swinging"))
                .setSaveConsumer(value -> config.disableRightClickSwinging = value)
                .build());
        builder.setSavingRunnable(config::save);
        return builder.build();
    }
}
