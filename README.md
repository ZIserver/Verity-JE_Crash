# 安装Verity JE就崩溃

一个 Minecraft Forge 模组。安装后启动游戏会自动打开浏览器播放 Rickroll，并抛出异常导致游戏崩溃。

## 效果

游戏启动加载该 mod 时会发生两件事：

1. **自动打开浏览器**，跳转到 `https://www.bilibili.com/video/BV1GJ411x7h7`
2. **抛出 `Never Gonna Give You Up` 异常**，Forge 弹出模组加载错误窗口，游戏无法进入主界面

经典的 Rickroll 整蛊 mod。

## 环境要求

| 项 | 版本 |
|---|---|
| Minecraft | 1.20.1 |
| Forge | 47+ |
| Java | 17+ |

## 安装

将 [安装Verity JE就崩溃-1.0.0.jar](安装Verity JE就崩溃-1.0.0.jar) 放入 `.minecraft/mods` 目录，启动游戏即可。

> 本 mod 与 Verity JE 模组无任何代码关联，是独立的新 mod。原 Verity JE 模组可装可不装。

## 工作原理

主类 `varmite.bilicrash.BiliCrash` 带有 `@Mod("bilicrash")` 注解，Forge 在构造其实例时：

- 调用 `openBrowser()` 跨平台打开默认浏览器（Windows 用 `cmd /c start`，macOS 用 `open`，Linux 用 `xdg-open`，首选 `Desktop.browse`）
- 抛出 `RuntimeException("Never Gonna Give You Up")`，被 Forge 捕获并以模组加载错误形式崩溃

## 从源码构建

需要 JDK 17+。

```bash
# 下载 Forge 1.20.1-47.3.0 依赖（仅用于编译注解）
curl -O https://maven.minecraftforge.net/net/minecraftforge/javafmllanguage/1.20.1-47.3.0/javafmllanguage-1.20.1-47.3.0.jar
curl -O https://maven.minecraftforge.net/net/minecraftforge/forge/1.20.1-47.3.0/forge-1.20.1-47.3.0-universal.jar

# 编译
javac --release 17 \
  -cp "javafmllanguage-1.20.1-47.3.0.jar;forge-1.20.1-47.3.0-universal.jar" \
  -d build/classes \
  src/main/java/varmite/bilicrash/BiliCrash.java

# 打包成 jar
cd build
cp -r ../src/main/resources .
cp -r classes varmite
jar cfm 安装Verity JE就崩溃-1.0.0.jar \
  resources/META-INF/MANIFEST.MF \
  resources/META-INF/mods.toml \
  resources/pack.mcmeta \
  varmite
```

## 项目结构

```
src/main/java/varmite/bilicrash/BiliCrash.java   主类
src/main/resources/META-INF/mods.toml             Forge mod 声明
src/main/resources/META-INF/MANIFEST.MF          JAR 清单
src/main/resources/pack.mcmeta                    资源包描述
安装Verity JE就崩溃-1.0.0.jar                      构建产物
```

## 许可

MIT License — 详见 [LICENSE](LICENSE)。
