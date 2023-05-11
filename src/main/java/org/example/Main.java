package org.example;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = driverSetup.getDriver();
        driver.manage().window().maximize();

        transcriptPage transPage = new transcriptPage(driver);
        transPage.acceptCookies();

        credentialsFile credentials = new credentialsFile();
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        transPage.navigateToLTU();
        transPage.enterEmailAndPassword(email, password);
        transPage.clickLoginButton();

        transPage.navigateToLadok();
        transPage.loginToLadok();
        transPage.createTranscript();
        transPage.downloadTranscript("fileExample.pdf");

        /*
        ltuPage ltu = new ltuPage(driver);
        ltu.gotoKronox();
        ltu.enterEmailAndPassword(email, password);
        ltu.clickLoginButton();

        if (ltu.validateTenta("I0015N")) {
            System.out.println("exists");
        } else {
            System.out.println("does not exist");
        }


         */
        //driver.quit();

    }

}