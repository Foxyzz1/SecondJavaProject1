// Importing necessary classes and packages
package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.io.File;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;

public class transcriptPage {

    // Constructor that opens a specific URL when an instance of this class is created
    public transcriptPage() {
        Selenide.open("https://ltu.se");
    }

    // Method to navigate to a specific URL and click on certain elements
    public void navigateToLTU() {
        open("https://ltu.se");
        SelenideElement studentButton = $x("//a[text()='Student']");
        SelenideElement mittLtuButton = $x("//a[text()='Mitt LTU']");

        studentButton.click();
        mittLtuButton.click();
    }

    // Method to enter email and password in the respective fields on a webpage
    public void enterEmailAndPassword(String email, String password) {
        SelenideElement ltuEmailField = $x("//input[@id='username']");
        SelenideElement ltuPasswordField = $x("//input[@id='password']");

        ltuEmailField.setValue(email);
        ltuEmailField.sendKeys(Keys.ESCAPE);
        ltuPasswordField.setValue(password);
        ltuPasswordField.sendKeys(Keys.RETURN);
    }

    // Method to navigate to another URL by clicking on a link
    public void navigateToLadok() {
        SelenideElement ladokButton = $x("//a[text()=' Intyg »']");
        open(ladokButton.attr("href"));
    }

    // Method to login to a specific website
    public void loginToLadok() {
        SelenideElement ladokBLUEBIGBUTTON = $x("//a[@href='/student/login?ret=/app/studentwebb']");
        SelenideElement searchInput = $x("//input[@id='searchinput']");
        SelenideElement luleaField = $x("//div[text()='Lulea University of Technology']");

        ladokBLUEBIGBUTTON.click();
        searchInput.setValue("Luleå");
        luleaField.click();

        SelenideElement transcriptField = $x("//a[@href='/student/app/studentwebb/intyg']");
        transcriptField.click();
    }

    // Method to create a transcript
    public boolean createTranscript() {
        SelenideElement transcriptCreateButton = $x("//button[@title='Skapa intyg']");
        SelenideElement selectionField = $x("//select[@id='intygstyp']");
        SelenideElement programmeButton = $x("//input[@id='allaRegistreringarGrupperdePaProgramRadio']");
        SelenideElement certificateCreate = $x("//span[text()='Skapa']");

        transcriptCreateButton.click();

        selectionField.click();
        selectionField.sendKeys(Keys.ARROW_DOWN);
        selectionField.sendKeys(Keys.RETURN);

        programmeButton.click();

        // Checking if the 'certificateCreate' button is displayed and clickable
        if(certificateCreate.isDisplayed()){
            certificateCreate.click();
            return true;
        } else {
            return false;
        }
    }

    // Method to download a transcript
    public boolean downloadTranscript(String downloadName) {
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

            // If the file was successfully moved, return true
            if (success) {
                System.out.println("File successfully downloaded and moved to: " + newFile.getPath());
                return true;
            }

            // If the file was not successfully moved, print an error message and return false
            System.out.println("Failed to move the file to: " + newFile.getPath());

        } catch (Exception e) {
            // If an exception occurs during the download process, print the exception message
            System.out.println(e.getMessage());
        }
        // If the download process fails, return false
        return false;

    }
}
