package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class utils {

    public static void delay(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    public static void acceptCookies() {
        SelenideElement acceptCookiesButton = $x("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
        acceptCookiesButton.click();
    }

}
