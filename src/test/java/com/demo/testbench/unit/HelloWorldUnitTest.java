package com.demo.testbench.unit;

import com.demo.application.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.testbench.unit.SpringUIUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloWorldUnitTest extends SpringUIUnitTest {

    @Test
    public void setText_clickButton_notificationIsShown() {
        final HelloWorldView helloWorldView = navigate(HelloWorldView.class);


        // Find the first button (<vaadin-button>) on the page
        Button button = $(Button.class).first();

        // Click it
        button.click();

        // Check for the notification
        Notification notification = $(Notification.class).first();

        // Check that notification is displayed
        Assertions.assertTrue(notification.isOpened());

        // Check that notification says "Hello Anonymous"
        Assertions.assertEquals("Hello Anonymous", test(notification).getText());
    }
}
