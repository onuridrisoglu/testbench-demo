package com.demo.testbench.e2e;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.BrowserTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class HelloWorldIT extends AbstractIT {

    @BeforeEach
    public void setup() throws Exception {
        // Open the application
        getDriver().get("http://localhost:8080/");
    }

    @BrowserTest
    public void clickButton() {
        // Find the first button (<vaadin-button>) on the page
        ButtonElement button = $(ButtonElement.class).first();

        // Click it
        button.click();

        // Check for the notification
        NotificationElement notification = $(NotificationElement.class).first();

        // Check that notification is displayed
        Assertions.assertTrue(notification.isOpen());

        // Check that notification says "Hello Anonymous"
        Assertions.assertEquals("Hello Anonymous", notification.getText());
    }

    @BrowserTest
    public void writeNameAndClickButton() {
        TextFieldElement name = $(TextFieldElement.class).first();
        name.setValue("Onur");



        // Find the first button (<vaadin-button>) on the page
        ButtonElement button = $(ButtonElement.class).first();
        // Click it
        button.click();

        // Check for the notification
        NotificationElement notification = $(NotificationElement.class).first();

        // Check that notification is displayed
        Assertions.assertTrue(notification.isOpen());

        Assertions.assertEquals("Hello Onur", notification.getText());
    }

    @BrowserTest
    public void compareScreen() {
        try {
            Assertions.assertTrue(testBench().compareScreen("HelloWorld"));
        } catch (IOException e) {
            Assertions.fail("Screenshot comparison failed");
        }
    }
}