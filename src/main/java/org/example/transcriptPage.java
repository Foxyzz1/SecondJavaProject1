package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class transcriptPage {

    private WebDriver driver;
    private By acceptCookiesButton = By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
    private By studentButton = By.xpath("//a[text()='Student']");
    private By mittLtuButton = By.xpath("//a[text()='Mitt LTU']");

    private By ltuEmailField = By.xpath("//input[@id='username']");
    private By ltuPasswordField = By.xpath("//input[@id='password']");
    private By ltuLoginButton = By.xpath("//input[@name='submit']");

    private By ladokButton = By.xpath("//a[text()=' Intyg »']");
    private By ladokBLUEBIGBUTTON = By.xpath("//a[@href='/student/login?ret=/app/studentwebb']");

    private By searchInput = By.xpath("//input[@id='searchinput']");
    private By luleaField = By.xpath("//div[text()='Lulea University of Technology']");

    // Issue here
    private By transcriptField = By.xpath("//a[@href='/student/app/studentwebb/intyg']");
    private By transcriptCreateButton = By.xpath("//button[@title='Skapa intyg']");
    private By selectionField = By.xpath("//select[@id='intygstyp']");
    //private By certificateOfRegisteration = By.xpath("//option[text()='Registreringsintyg']");
    private By programmeButton = By.xpath("//input[@id='allaRegistreringarGrupperdePaProgramRadio']");
    private By certificateCreate = By.xpath("//span[text()='Skapa']");

    private By transcriptDownload = By.xpath("//*[contains(text(),'Registreringsintyg')]");


    public transcriptPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://ltu.se");
    }

    public void acceptCookies(){
        try {
            utils.delay(2000);
            WebElement acceptCookies = driver.findElement(acceptCookiesButton);
            acceptCookies.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void navigateToLTU() {
        WebElement studentButtonField = driver.findElement(studentButton);
        studentButtonField.click();

        WebElement ltuButton = driver.findElement(mittLtuButton);
        ltuButton.click();
    }

    public void enterEmailAndPassword(String email, String password) {
        try {
            utils.delay(2000);
            WebElement emailField = driver.findElement(ltuEmailField);
            emailField.click();
            emailField.sendKeys(email);

            utils.delay(2000);
            WebElement passwordField = driver.findElement(ltuPasswordField);
            passwordField.click();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            utils.delay(2000);
            WebElement loginBTN = driver.findElement(ltuLoginButton);
            loginBTN.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void navigateToLadok() {
        try {
            utils.delay(2000);
            WebElement ladokBTN = driver.findElement(ladokButton);
            driver.get(ladokBTN.getAttribute("href"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginToLadok() {
        try {
            utils.delay(2000);
            WebElement blueButton = driver.findElement(ladokBLUEBIGBUTTON);
            blueButton.click();

            utils.delay(2000);
            WebElement searchField = driver.findElement(searchInput);
            searchField.click();
            searchField.sendKeys("Luleå");

            utils.delay(2000);
            WebElement lulea = driver.findElement(luleaField);
            lulea.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTranscript(){
        try {
            utils.delay(1000);
            WebElement transcriptBTN = driver.findElement(transcriptField);
            transcriptBTN.click();

            utils.delay(1000);
            WebElement transcriptCreate = driver.findElement(transcriptCreateButton);
            transcriptCreate.click();

            utils.delay(1000);
            WebElement selection = driver.findElement(selectionField);
            selection.click();

            selection.sendKeys(Keys.ARROW_DOWN);
            selection.sendKeys(Keys.RETURN);

            //utils.delay(1000);
            //WebElement registrationtranscript  = driver.findElement(certificateOfRegisteration);
            //registrationtranscript.click();

            utils.delay(1000);
            WebElement programmeBTN = driver.findElement(programmeButton);
            programmeBTN.click();

            utils.delay(1000);
            WebElement createCert = driver.findElement(certificateCreate);
            createCert.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void downloadTranscript(String downloadName){
        try{
            utils.delay(2000);
           String downloadPath = "C:\\temp\\";
           String url;

           WebElement download = driver.findElement(transcriptDownload);
           url = download.getAttribute("href");

           WebDriverManager.chromedriver().setup();
           Configuration.reportsFolder = downloadPath;
           open(url);
           File downloadFile = $(By.tagName("body")).download(Long.parseLong(downloadName));
           downloadFile.createNewFile();
           downloadFile.exists(); // returns true if exisstrs

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
