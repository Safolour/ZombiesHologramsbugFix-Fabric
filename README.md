# Zombies Holograms-bug Fix

Zombies Holograms-bug Fix (ZHF) is a client-side Fabric mod that fixes targeting and right-click interaction problems caused by hologram armor stands on Minecraft servers.

Many servers use invisible armor stands to display names, health values, and other floating text. These entities can block the player's crosshair and prevent entities or blocks behind them from being targeted correctly. When ZHF is enabled, armor stands are excluded from client-side crosshair entity detection while normal entity, block, and item interactions are preserved.

## Features

- Toggle ZHF with the `Z` key
- Exclude armor stands from crosshair entity detection
- Preserve entity interactions, block interactions, and item use
- Display the current ZHF state in the top-right corner of the screen
- Show a chat message when ZHF is toggled
- Optionally ignore right-click block reactions
- Optionally disable the hand swing animation when right-clicking blocks
- Open the configuration screen through Mod Menu or a configurable key binding
- Save settings automatically in the game configuration directory
- Run entirely on the client without requiring server-side installation

## Supported Versions

| Minecraft version | Mod file |
| --- | --- |
| 1.21.5 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.5.jar` |
| 1.21.6–1.21.8 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.6-1.21.8.jar` |
| 1.21.9–1.21.10 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.9-1.21.10.jar` |
| 1.21.11 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.11.jar` |

## Dependencies

- Fabric Loader
- Fabric API
- Cloth Config API
- Mod Menu (optional, provides access to the configuration screen from the mod list)

Install dependency versions compatible with your Minecraft version.

## Installation

1. Install Fabric Loader for your Minecraft version.
2. Download the ZHF JAR that supports your Minecraft version.
3. Place ZHF, Fabric API, and Cloth Config API in `.minecraft/mods`.
4. Optionally install Mod Menu to access the configuration screen from the mod list.
5. Start the game.

## Usage

Default key bindings:

- `Z`: Enable or disable ZHF
- `Config`: Unbound by default; assign a key in the Minecraft Controls menu

The HUD in the top-right corner shows the current state:

- `ZHF: ON`: ZHF is enabled
- `ZHF: OFF`: ZHF is disabled

## Configuration

The configuration screen contains the following options.

### Ignore Block Reactions

Ignores right-click reactions from the block under the crosshair so block interaction does not take priority over item use. This option is disabled by default.

### Disable Right Click Swinging

Disables the client-side hand swing animation after a successful right-click block interaction. This option is enabled by default.

The configuration file is stored at:

```text
.minecraft/config/zombieshologramsbugfix.json
```

## Building

The project uses Java 21, Gradle, Fabric Loom, and Mojang mappings.

The default build target is Minecraft 1.21.5:

```bash
./gradlew build
```

To specify Minecraft and Fabric API versions:

```bash
./gradlew build -Pminecraft_version=1.21.11 -Pfabric_version=0.141.4+1.21.11
```

Build artifacts are generated in:

```text
build/libs
```
