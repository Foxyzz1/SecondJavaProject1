package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ltuPage {

    private WebDriver driver;
    private By studentButton = By.xpath("//a[text()='Student']");
    private By studeraButton = By.xpath("//a[text()='Studera']");
    private By tentaButton = By.xpath("//a[@href='/student/Studera/Tentamen']");
    private By kronoxButton = By.xpath("//span[text()='Tentamensanmälan - länk till KronoX']");

    private By kronoxLoginButton = By.xpath("//a[@class='signin']");
    private By kronoxEmailField = By.xpath("//input[@id='login_username']");
    private By kronoxPasswordField = By.xpath("//input[@id='login_password']");
    private By loginButton = By.xpath("//input[@id='login_button']");

    private By akti = By.xpath("//a[@href='/aktivitetsanmalan.jsp?']");



    public ltuPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://ltu.se");
    }


    public void gotoKronox(){
        try {
            utils.delay(2000);
            WebElement menu = driver.findElement(studentButton);
            menu.click();

            WebElement studera = driver.findElement(studeraButton);
            studera.click();

            WebElement tenta = driver.findElement(tentaButton);
            tenta.click();

            WebElement kronox = driver.findElement(kronoxButton);
            kronox.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterEmailAndPassword(String email, String password) {
        try {
            utils.delay(2000);
            driver.findElement(kronoxLoginButton).click();

            utils.delay(1000);
            WebElement emailField = driver.findElement(kronoxEmailField);
            emailField.click();
            emailField.sendKeys(email);
            emailField.sendKeys(Keys.ESCAPE);

            utils.delay(1000);
            WebElement passwordField = driver.findElement(kronoxPasswordField);
            passwordField.click();
            passwordField.sendKeys(password);
            passwordField.sendKeys(Keys.ESCAPE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            utils.delay(2000);
            WebElement login = driver.findElement(loginButton);
            login.click();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean validateTenta(String courseCode){
        try {
            utils.delay(2000);
            By kursTenta = By.xpath("//b[contains(text(), " + courseCode + ")]/parent::div/following-sibling::div[contains(text(), 'Datum')]");

            WebElement aktivitet = driver.findElement(akti);
            aktivitet.click();

            WebElement tenta = driver.findElement(kursTenta);
            System.out.println(tenta.getText());

            return tenta.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}