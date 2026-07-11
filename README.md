# Zombies Holograms-bug Fix

Zombies Holograms-bug Fix（ZHF）是一个纯客户端 Fabric Mod，用于修复 Minecraft 服务器中由全息文字 Armor Stand 引起的目标选择和右键交互问题。

许多服务器使用隐形 Armor Stand 显示名称、血量和提示文字。这些实体可能挡住玩家准星，使后方的实体或方块无法正常被选中。开启 ZHF 后，Armor Stand 将不会参与客户端准星实体检测，同时 Mod 会保持正常的实体、方块和物品使用流程。

## 功能

- 按 `Z` 开启或关闭 ZHF
- 在准星目标检测中忽略 Armor Stand
- 保持实体交互、方块交互及物品使用逻辑
- 在屏幕右上角显示当前开关状态
- 切换状态时在聊天栏显示提示
- 可选择忽略方块右键反应
- 可选择禁用右键方块时的挥手动画
- 支持通过 Mod Menu 和快捷键打开配置界面
- 配置自动保存到游戏配置目录
- 纯客户端运行，不要求服务端安装

## 支持版本

| Minecraft 版本 | Mod 文件 |
| --- | --- |
| 1.21.5 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.5.jar` |
| 1.21.6–1.21.8 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.6-1.21.8.jar` |
| 1.21.9–1.21.10 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.9-1.21.10.jar` |
| 1.21.11 | `ZombiesHologramsbugFix-Fabric-2.0.0+1.21.11.jar` |

## 依赖

- Fabric Loader
- Fabric API
- Cloth Config API
- Mod Menu（可选，用于从 Mod 列表打开配置界面）

请安装与当前 Minecraft 版本对应的依赖版本。

## 安装

1. 安装对应 Minecraft 版本的 Fabric Loader。
2. 下载适用于该版本的 ZHF JAR。
3. 将 ZHF、Fabric API 和 Cloth Config API 放入 `.minecraft/mods`。
4. 如需从 Mod 列表打开配置界面，同时安装 Mod Menu。
5. 启动游戏。

## 使用

默认按键：

- `Z`：开启或关闭 ZHF
- `Config`：默认未绑定，可在游戏按键设置中自行绑定

HUD 会在屏幕右上角显示：

- `ZHF: ON`：功能已开启
- `ZHF: OFF`：功能已关闭

## 配置

配置界面提供以下选项：

### Ignore Block Reactions

忽略准星所指方块的右键反应，使物品使用不被方块交互抢占。该选项默认关闭。

### Disable Right Click Swinging

禁用成功右键方块时的客户端挥手动画。该选项默认开启。

配置文件位于：

```text
.minecraft/config/zombieshologramsbugfix.json
```

## 构建

项目使用 Java 21、Gradle 和 Fabric Loom，并采用 Mojang mappings。

默认构建目标为 Minecraft 1.21.5：

```bash
./gradlew build
```

指定 Minecraft 与 Fabric API 版本：

```bash
./gradlew build -Pminecraft_version=1.21.11 -Pfabric_version=0.141.4+1.21.11
```

构建产物位于：

```text
build/libs
```
