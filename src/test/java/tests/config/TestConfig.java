package tests.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestConfig {

    public static void applyConfiguration() {
        Configuration.browserSize = getResolution();
        Configuration.baseUrl = getBaseUrl();
        Configuration.browser = getBrowser();
        Configuration.browserVersion = getBrowserVersion();
        Configuration.headless = isHeadless();

        String remoteUrl = getRemoteUrl();
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
            setupRemoteCapabilities();
        }

        printConfiguration();
    }

    private static String getBaseUrl() {
        return System.getProperty("base.url", "https://demoqa.com");
    }

    private static String getRemoteUrl() {
        return System.getProperty("remote.url");
    }

    private static String getBrowser() {
        return System.getProperty("browser", "chrome");
    }

    private static String getBrowserVersion() {
        return System.getProperty("browser.version", "latest");
    }

    private static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    private static String getResolution() {
        return System.getProperty("resolution", "1920x1080");
    }

    private static void setupRemoteCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", Boolean.parseBoolean(System.getProperty("video.enabled", "false"))
        ));
        Configuration.browserCapabilities = capabilities;
    }

    private static void printConfiguration() {
        System.out.println("=== Test Configuration ===");
        System.out.println("Base URL: " + Configuration.baseUrl);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Browser Version: " + Configuration.browserVersion);
        System.out.println("Resolution: " + Configuration.browserSize);
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("Remote: " + (Configuration.remote != null ? Configuration.remote : "local"));
        System.out.println("==========================");
    }
}
