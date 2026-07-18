package varmite.bilicrash;

import java.awt.Desktop;
import java.net.URI;
import net.minecraftforge.fml.common.Mod;

@Mod("bilicrash")
public class BiliCrash {
    public BiliCrash() {
        openBrowser();
        throw new RuntimeException("Never Gonna Give You Up");
    }

    private static void openBrowser() {
        String url = "https://www.bilibili.com/video/BV1GJ411x7h7";
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                return;
            }
        } catch (Throwable ignored) {}
        String os = System.getProperty("os.name", "").toLowerCase();
        try {
            if (os.contains("win")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "", url});
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
            } else {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            }
        } catch (Throwable ignored) {}
    }
}
