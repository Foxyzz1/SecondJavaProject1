package org.example;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.*;

public class Main {
    public static void main(String[] args) {
        try {

            // Set up the driver.
            driverSetup.getDriver();

            credentialsFile credentials = new credentialsFile();     // The credentials are read from the credentials.json file
            String email = credentials.getEmail();                   // "email" fetched
            String password = credentials.getPassword();             // "password" fetched

            // Use transcriptPage.
            transcriptPage transcriptPageInstance = new transcriptPage();
            transcriptPageInstance.acceptCookies();
            transcriptPageInstance.navigateToLTU();
            transcriptPageInstance.enterEmailAndPassword(email, password);
            transcriptPageInstance.navigateToLadok();
            transcriptPageInstance.loginToLadok();
            transcriptPageInstance.createTranscript();
            transcriptPageInstance.downloadTranscript("transcriptFile.pdf");

            // Use ltuPage.
            ltuPage ltuPageInstance = new ltuPage();
            ltuPageInstance.gotoKronox();
            ltuPageInstance.enterEmailAndPassword(email, password);
            ltuPageInstance.validateTenta("courseCode");

            // Use syllabusPage.
            new syllabusPage("syllabus.pdf");

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}