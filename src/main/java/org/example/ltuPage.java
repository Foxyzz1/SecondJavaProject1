package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class ltuPage {

    private String screenshotPath = "C:\\Users\\Krill\\IdeaProjects\\SecondJavaProject1\\target\\screenshots\\";

    // This is the constructor of the ltuPage class which opens the LTU website upon instantiation.
    public ltuPage() {
        Selenide.open("https://ltu.se");
    }

    // This method navigates to the Kronox page by sequentially clicking on the necessary buttons.
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

    // This method logs into Kronox using the provided email and password.
    public void enterEmailAndPassword(String email, String password) {
        SelenideElement kronoxLoginButton = $x("//a[@class='signin']");
        SelenideElement kronoxEmailField = $x("//input[@id='login_username']");
        SelenideElement kronoxPasswordField = $x("//input[@id='login_password']");

        kronoxLoginButton.click();
        kronoxEmailField.sendKeys(email);
        kronoxPasswordField.sendKeys(password);
        kronoxPasswordField.sendKeys(Keys.RETURN);
    }

    // This method takes a screenshot and saves it in a specific folder with the provided name.
    public void screenshot(String name) {
        Configuration.reportsFolder = screenshotPath;
        Selenide.screenshot(name);
    }

    // This method checks whether a course with the given courseCode has a final examination scheduled.
    public boolean validateTenta(String courseCode) {
        SelenideElement akti = $x("//a[@href='/aktivitetsanmalan.jsp?']");
        SelenideElement tentaDate = $x("//b[contains(text(), " + courseCode + ")]/parent::div/following-sibling::div[contains(text(), 'Datum')]");
        akti.click();
        System.out.println(tentaDate.getText());
        screenshot("final_examination");

        // Returns true if the final examination date is displayed, otherwise false.
        return tentaDate.isDisplayed();
    }
}
