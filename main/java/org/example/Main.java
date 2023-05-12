package org.example;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Set up the driver.
            driverSetup.getDriver();


            PageTest pagetest = new PageTest();

            // Use syllabusPage.


        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}