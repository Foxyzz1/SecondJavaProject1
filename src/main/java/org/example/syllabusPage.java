package org.example;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.url;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.nio.file.Paths;


public class syllabusPage {

    public syllabusPage(String downloadName) {
        try {
                open("https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan");

                // Find an element by XPath and click on it
                var xpath2 = $x("//a[@href='#' and text()='V23']");
                xpath2.shouldBe(visible);
                xpath2.click();

                // Verify the URL
                String expectedUrl = "https://www.ltu.se/edu/course/I00/I0015N/I0015N-Test-av-IT-system-1.81215?kursView=kursplan&termin=V23";
                String actualUrl = url();
                Assertions.assertEquals(expectedUrl, actualUrl);

                String downloadPath = "C:\\Users\\Felle\\IdeaProjects\\Second Java Project1\\target\\download\\";

                SelenideElement transcriptDownload1 = $x("//a[@class='utbplan-pdf-link']");

                // Create directory if it does not exist
                File direc = new File(downloadPath);
                if (!direc.exists()) {
                    direc.mkdirs();
                    System.out.println("Directory did not exist, created new one.");

                    SelenideElement downloadLink = $(transcriptDownload1);

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

                }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

