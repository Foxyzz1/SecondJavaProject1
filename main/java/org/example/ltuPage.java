package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;

public class ltuPage {

    // Constructor: Opens the specified URL in the browser when a new object of this class is created
    public ltuPage() {
        Selenide.open("https://ltu.se");
    }

    // Method to navigate to the Kronox page by clicking on the "Student", "Studera", "Tentamen" and "Kronox" buttons in sequence
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

    // Method to enter email and password into their respective fields and then press Enter to log into Kronox
    public void enterEmailAndPassword(String email, String password) {
        SelenideElement kronoxLoginButton = $x("//a[@class='signin']");
        SelenideElement kronoxEmailField = $x("//input[@id='login_username']");
        SelenideElement kronoxPasswordField = $x("//input[@id='login_password']");

        kronoxLoginButton.click();
        kronoxEmailField.sendKeys(email);
        kronoxPasswordField.sendKeys(password);
        kronoxPasswordField.sendKeys(Keys.RETURN);
    }

    // Method to take a screenshot of the current page and save it with the specified name in the reports folder
    public void screenshot(String name) {
        Configuration.reportsFolder = "C:\\Users\\Felle\\IdeaProjects\\Second Java Project1\\target\\screenshots\\";
        Selenide.screenshot(name);
    }

    // Method to validate a course exam by clicking on the "Aktivitet" link, printing the exam date, and taking a screenshot
    public void validateTenta(String courseCode) {
        SelenideElement akti = $x("//a[@href='/aktivitetsanmalan.jsp?']");
        SelenideElement tenta = $x("//b[contains(text(), " + courseCode + ")]/parent::div/following-sibling::div[contains(text(), 'Datum')]");
        akti.click();
        System.out.println(tenta.getText());
        screenshot("final_examination");
    }
}
