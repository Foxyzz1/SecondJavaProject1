package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.io.File;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;

public class transcriptPage {

    public transcriptPage() {
        Selenide.open("https://ltu.se");
    }

    public void acceptCookies() {
        SelenideElement acceptCookiesButton = $x("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
        acceptCookiesButton.click();
    }

    public void navigateToLTU() {
        SelenideElement studentButton = $x("//a[text()='Student']");
        SelenideElement mittLtuButton = $x("//a[text()='Mitt LTU']");

        studentButton.click();
        mittLtuButton.click();
    }

    public void enterEmailAndPassword(String email, String password) {
        SelenideElement ltuEmailField = $x("//input[@id='username']");
        SelenideElement ltuPasswordField = $x("//input[@id='password']");

        ltuEmailField.setValue(email);
        ltuEmailField.sendKeys(Keys.ESCAPE);
        ltuPasswordField.setValue(password);
        ltuPasswordField.sendKeys(Keys.RETURN);
    }


    public void navigateToLadok() {
        SelenideElement ladokButton = $x("//a[text()=' Intyg »']");
        open(ladokButton.attr("href"));
    }

    public void loginToLadok() {
        SelenideElement ladokBLUEBIGBUTTON = $x("//a[@href='/student/login?ret=/app/studentwebb']");
        SelenideElement searchInput = $x("//input[@id='searchinput']");
        SelenideElement luleaField = $x("//div[text()='Lulea University of Technology']");

        ladokBLUEBIGBUTTON.click();
        searchInput.setValue("Luleå");
        luleaField.click();
    }

    public void createTranscript() {
        SelenideElement transcriptField = $x("//a[@href='/student/app/studentwebb/intyg']");
        SelenideElement transcriptCreateButton = $x("//button[@title='Skapa intyg']");
        SelenideElement selectionField = $x("//select[@id='intygstyp']");
        SelenideElement programmeButton = $x("//input[@id='allaRegistreringarGrupperdePaProgramRadio']");
        SelenideElement certificateCreate = $x("//span[text()='Skapa']");

        transcriptField.click();
        transcriptCreateButton.click();

        selectionField.click();
        selectionField.sendKeys(Keys.ARROW_DOWN);
        selectionField.sendKeys(Keys.RETURN);

        programmeButton.click();
        certificateCreate.click();
    }

    public void downloadTranscript(String downloadName) {
        try {
            SelenideElement transcriptDownload = $x("//*[contains(text(),'Registreringsintyg')]");

            utils.delay(2000);
            String downloadPath = "C:\\temp\\";

            // Create directory if it does not exist
            File direc = new File(downloadPath);
            if (!direc.exists()) {
                direc.mkdirs();
                System.out.println("Directory did not exist, created new one.");
            }

            SelenideElement downloadLink = $(transcriptDownload);

            // Download the file
            File downloadedFile = downloadLink.download();

            // Move the file to desired directory and rename

            File oldFile = new File(downloadPath + downloadName);
            if (oldFile.exists()) {
                oldFile.delete();
            }
            File newFile = Paths.get(downloadPath, downloadName).toFile();
            boolean success = downloadedFile.renameTo(newFile);

            if (success) {
                System.out.println("File successfully downloaded and moved to: " + newFile.getPath());
            } else {
                System.out.println("Failed to move the file to: " + newFile.getPath());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
