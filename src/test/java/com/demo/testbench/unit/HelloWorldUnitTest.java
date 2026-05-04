package com.demo.testbench.unit;

import com.demo.application.views.helloworld.HelloWorldView;
import com.vaadin.browserless.SpringBrowserlessTest;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloWorldUnitTest extends SpringBrowserlessTest {

    @Test
    public void setText_clickButton_notificationIsShown() {
        final HelloWorldView helloWorldView = navigate(HelloWorldView.class);

        // Find the button (<vaadin-button>) on the page
        Button button = $(Button.class).withCaption("Say hello").single();

        // Click it
        test(button).click();

        // Check for the notification
        Notification notification = $(Notification.class).single();

        // Check that notification is displayed
        Assertions.assertTrue(notification.isOpened());

        // Check that notification says "Hello Anonymous"
        Assertions.assertEquals("Hello Anonymous", test(notification).getText());
    }
}
