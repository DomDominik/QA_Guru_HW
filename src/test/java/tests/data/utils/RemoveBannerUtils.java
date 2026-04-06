package tests.data.utils;

import com.codeborne.selenide.Selenide;

public class RemoveBannerUtils {
    public static void removeAdElements() {
        Selenide.executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
        """);
    }
}
