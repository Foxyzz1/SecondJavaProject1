package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Configuration;
import java.util.HashMap;

public class driverSetup {

    public static void getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("plugins.always_open_pdf_externally", true); //set this property to download PDFs automatically
        chromePrefs.put("download.default_directory", "C:\\temp\\"); //set this property to set the download directory
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriverManager.chromedriver().setup();

        // Set browser to Chrome
        Configuration.browser = "chrome";

        // Set browser size to maximized
        Configuration.startMaximized = true;

        // Set options
        Configuration.browserCapabilities = options;


    }
}