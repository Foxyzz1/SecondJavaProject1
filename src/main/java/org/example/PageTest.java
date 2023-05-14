package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

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
            utils.acceptCookies();

            email = credentialsFile.getEmail();                   // "email" fetched
            password = credentialsFile.getPassword();             // "password" fetched
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Test method to see if we can find the final exam information
    @Test
    @Order(1)
    public void testFinalExamInfo(){
        ltuPage.gotoKronox();
        ltuPage.enterEmailAndPassword(email, password);
        System.out.println("1");
        // Locate and validate the final examination information
        assertTrue(ltuPage.validateTenta("I0015N"));
    }

    // Test method to check if we can create a transcript.
    @Test
    @Order(2)
    public void testCreateTranscript() {
        transcriptPage.navigateToLTU();
        transcriptPage.enterEmailAndPassword(email, password);
        transcriptPage.navigateToLadok();
        transcriptPage.loginToLadok();
        System.out.println("2");
        // Validate the accessibility of the button to generate your student transcript (certificate of Registration) and produce one.
        assertTrue(transcriptPage.createTranscript());
    }

    // Test method to check if we can download a transcript.
    @Test
    @Order(3)
    public void testDownloadTranscript() {
        // Download the previous created transcript
        assertTrue(transcriptPage.downloadTranscript("transcript.pdf"));
        System.out.println("3");
    }

    // Test method to verify access to the 2023 course syllabus and download it.
    @Test
    @Order(4)
    public void testCourseSyllabus(){
        System.out.println("4");
        // Validate access to the 2023 course syllabus and download it
        assertTrue(syllabusPage.downloadSyllabus("syllabus.pdf"));
    }
}
