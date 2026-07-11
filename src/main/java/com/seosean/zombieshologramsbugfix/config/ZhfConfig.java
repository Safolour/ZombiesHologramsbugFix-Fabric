package com.seosean.zombieshologramsbugfix.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ZhfConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("zombieshologramsbugfix.json");
    public boolean ignoreBlockReactions = false;
    public boolean disableRightClickSwinging = true;

    public static ZhfConfig load() {
        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH)) {
                ZhfConfig loaded = GSON.fromJson(reader, ZhfConfig.class);
                if (loaded != null) return loaded;
            } catch (IOException ignored) {
            }
        }
        ZhfConfig config = new ZhfConfig();
        config.save();
        return config;
    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(PATH)) {
            GSON.toJson(this, writer);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to save ZHF config", exception);
        }
    }
}
