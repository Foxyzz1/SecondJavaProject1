package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.io.File;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.*;

public class transcriptPage {

    // Constructor: When a new object of this class is created, it opens the specified URL in the browser.
    public transcriptPage() {
        Selenide.open("https://ltu.se");
    }

    // Method to accept cookies by clicking on the "Accept Cookies" button.
    public void acceptCookies() {
        SelenideElement acceptCookiesButton = $x("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
        acceptCookiesButton.click();
    }

    // Method to navigate to the LTU page by clicking on the "Student" button and then the "Mitt LTU" button.
    public void navigateToLTU() {
        SelenideElement studentButton = $x("//a[text()='Student']");
        SelenideElement mittLtuButton = $x("//a[text()='Mitt LTU']");

        studentButton.click();
        mittLtuButton.click();
    }

    // Method to enter email and password into their respective fields and then press Enter.
    public void enterEmailAndPassword(String email, String password) {
        SelenideElement ltuEmailField = $x("//input[@id='username']");
        SelenideElement ltuPasswordField = $x("//input[@id='password']");

        ltuEmailField.setValue(email);
        ltuEmailField.sendKeys(Keys.ESCAPE);
        ltuPasswordField.setValue(password);
        ltuPasswordField.sendKeys(Keys.RETURN);
    }

    // Method to navigate to the Ladok page by opening the URL contained in the href attribute of the "Ladok" button.
    public void navigateToLadok() {
        SelenideElement ladokButton = $x("//a[text()=' Intyg »']");
        open(ladokButton.attr("href"));
    }

    // Method to login to Ladok by clicking on the "Ladok Blue Big Button", entering "Luleå" in the search field, and clicking on the "Lulea University of Technology" field.
    public void loginToLadok() {
        SelenideElement ladokBLUEBIGBUTTON = $x("//a[@href='/student/login?ret=/app/studentwebb']");
        SelenideElement searchInput = $x("//input[@id='searchinput']");
        SelenideElement luleaField = $x("//div[text()='Lulea University of Technology']");

        ladokBLUEBIGBUTTON.click();
        searchInput.setValue("Luleå");
        luleaField.click();
    }

    // Method to create a transcript by clicking on various buttons and fields.
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

        // Click on the "Programme" button.
        programmeButton.click();

        // Click on the "Create Certificate" button.
        certificateCreate.click();
    }

    // Method to download a transcript.
    public void downloadTranscript(String downloadName) {
        try {
            // Define the link element for the transcript download.
            SelenideElement transcriptDownload = $x("//*[contains(text(),'Registreringsintyg')]");

            // Pause the script execution for 2000 milliseconds.
            utils.delay(2000);

            // Define the path where the downloaded files should be stored.
            String downloadPath = "C:\\temp\\";

            // Create the directory if it does not exist.
            File direc = new File(downloadPath);
            if (!direc.exists()) {
                direc.mkdirs();
                System.out.println("Directory did not exist, created new one.");
            }

            // Create an instance of the SelenideElement for the download link.
            SelenideElement downloadLink = $(transcriptDownload);

            // Download the file using Selenide's download() method.
            File downloadedFile = downloadLink.download();

            // Delete the old file if it exists, then move the downloaded file to the specified directory and rename it.
            File oldFile = new File(downloadPath + downloadName);
            if (oldFile.exists()) {
                oldFile.delete();
            }

            File newFile = Paths.get(downloadPath, downloadName).toFile();
            boolean success = downloadedFile.renameTo(newFile);

            // Print a message indicating whether the file was successfully moved.
            if (success) {
                System.out.println("File successfully downloaded and moved to: " + newFile.getPath());
            } else {
                System.out.println("Failed to move the file to: " + newFile.getPath());
            }

        } catch (Exception e) {
            // Print any exceptions that occur during the execution of this method.
            System.out.println(e.getMessage());
        }
    }
}
