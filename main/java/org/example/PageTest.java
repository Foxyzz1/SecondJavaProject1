package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class PageTest {

    private static transcriptPage transcriptPage;
    private static syllabusPage syllabusPage;
    private static ltuPage ltuPage;
    private static credentialsFile credentialsFile;
    private static String email;
    private static String password;

    // Set up method for all tests, executed once before all tests.
    @BeforeAll
    public static void setUp() {
        try {
            transcriptPage = new transcriptPage();
            syllabusPage = new syllabusPage();
            ltuPage = new ltuPage();
            credentialsFile = new credentialsFile();

            email = credentialsFile.getEmail();                   // "email" fetched
            password = credentialsFile.getPassword();             // "password" fetched
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Test method to see if we can find the final exam information
    @Test
    public void testFinalExamInfo(){
        ltuPage.gotoKronox();
        ltuPage.enterEmailAndPassword(email, password);
        ltuPage.screenshot("final_examination");
        ltuPage.validateTenta("I0016N");
        // Add assertions to verify
    }

    // Test method to check if we can create a transcript.
    @Test
    public void testCreateTranscript() {
        transcriptPage.acceptCookies();
        transcriptPage.navigateToLTU();
        transcriptPage.enterEmailAndPassword(email, password);
        transcriptPage.navigateToLadok();
        transcriptPage.loginToLadok();
        transcriptPage.createTranscript();
        // Add assertions to verify transcript creation.
    }

    // Test method to check if we can download a transcript.
    @Test
    public void testDownloadTranscript() {
        transcriptPage.downloadTranscript("transcript.pdf");
        // Add assertions to verify transcript download.
    }

    // Test method to verify access to the 2023 course syllabus and download it.
    @Test
    public void testCourseSyllabus(){
        syllabusPage.downloadSyllabus("syllabus.pdf");
        //Add assertions to verify the syllabys download
    }
}
