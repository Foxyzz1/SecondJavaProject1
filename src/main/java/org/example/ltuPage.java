package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.security.Key;

import static com.codeborne.selenide.Selenide.*;

public class ltuPage {

    public ltuPage() {
        Selenide.open("https://ltu.se");
    }

    public void gotoKronox() {
        SelenideElement studentButton = $x("//a[text()='Student']");
        SelenideElement studeraButton = $x("//a[text()='Studera']");
        SelenideElement tentaButton = $x("//a[@href='/student/Studera/Tentamen']");
        SelenideElement kronoxButton = $x("//span[text()='Tentamensanmälan - länk till KronoX']");

        studentButton.click();
        studeraButton.click();
        tentaButton.click();
        kronoxButton.click();
    }

    public void enterEmailAndPassword(String email, String password) {
        SelenideElement kronoxLoginButton = $x("//a[@class='signin']");
        SelenideElement kronoxEmailField = $x("//input[@id='login_username']");
        SelenideElement kronoxPasswordField = $x("//input[@id='login_password']");

        kronoxLoginButton.click();
        kronoxEmailField.sendKeys(email);
        kronoxPasswordField.sendKeys(password);
        kronoxPasswordField.sendKeys(Keys.RETURN);
    }

    public void screenshot(String name) {
        Configuration.reportsFolder = "C:\\Users\\Felle\\IdeaProjects\\Second Java Project1\\target\\screenshots\\";
        Selenide.screenshot(name);
    }

    public void validateTenta(String courseCode) {
        SelenideElement akti = $x("//a[@href='/aktivitetsanmalan.jsp?']");
        SelenideElement tenta = $x("//b[contains(text(), " + courseCode + ")]/parent::div/following-sibling::div[contains(text(), 'Datum')]");
        akti.click();
        System.out.println(tenta.getText());
        screenshot("final_examination");
    }
}
