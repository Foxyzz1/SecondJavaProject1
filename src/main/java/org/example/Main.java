package org.example;


import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

public class Main {
    public static void main(String[] args) {
        try {
            // Setup the driver before running the tests
            driverSetup.getDriver();

            // Create a Launcher using the LauncherFactory
            Launcher launcher = LauncherFactory.create();

            // Create a SummaryGeneratingListener to collect statistics of test execution
            SummaryGeneratingListener listener = new SummaryGeneratingListener();

            // Create a LauncherDiscoveryRequest which specifies which tests to run
            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(
                    // Select a specific class to run tests from
                    selectClass(PageTest.class)
            ).build();

            // Execute the request (run the tests) using the launcher and listen for events with listener
            launcher.execute(request, listener);

            // Print the total number of tests run
            System.out.println("Total tests run: " + listener.getSummary().getTestsFoundCount());
            // Print the total number of successful tests
            System.out.println("Total tests succeeded: " + listener.getSummary().getTestsSucceededCount());
            // Print the total number of failed tests
            System.out.println("Total tests failed: " + listener.getSummary().getTestsFailedCount());

            // Loop over each test failure and print the details
            listener.getSummary().getFailures().forEach(failure -> {
                System.out.println("Failure: " + failure.getTestIdentifier().getDisplayName());
                System.out.println("Error Message: " + failure.getException().getMessage());
                System.out.println("-----------");
            });

        } catch(Exception e) {
            // If anything goes wrong during this process, print the error message
            System.out.println(e.getMessage());
        }

    }
}
