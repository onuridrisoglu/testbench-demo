package com.demo.testbench.e2e;

import com.demo.application.data.SamplePerson;
import com.demo.testbench.e2e.masterdetail.MasterDetailViewElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.testbench.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;

public class MasterDetailViewIT extends AbstractIT {

    @BeforeEach
    public void setup() throws Exception {
        // Open the application
        getDriver().get("http://localhost:8080/persons");
    }

    @BrowserTest
    public void saveNewUser() {
        SamplePerson person = createSamplePerson();
        MasterDetailViewElement view = $(MasterDetailViewElement.class).first();

        // Check that the form is empty before we enter any value
        Assertions.assertTrue(view.findFormEditorElement().isFormEmpty());

        // Enter person information and save the form
        view.savePerson(person);

        // Check that "Data updated notification is displayed"
        NotificationElement notification = $(NotificationElement.class).first();
        Assertions.assertTrue(notification.isOpen());
        Assertions.assertEquals("Data updated", notification.getText());

        // The form should be cleared after save
        Assertions.assertTrue(view.findFormEditorElement().isFormEmpty());

        // Scroll in the grid until we find the new record
        view.findPersonInGrid(person.getFirstName());

        // Check that the record is selected and the information is displayed in the form
        Assertions.assertEquals(person.getFirstName(), view.findFormEditorElement().getFirstName().getValue());
        Assertions.assertEquals(person.getLastName(), view.findFormEditorElement().getLastName().getValue());
    }

    private SamplePerson createSamplePerson() {
        SamplePerson person = new SamplePerson();
        person.setFirstName("Sample First Name");
        person.setLastName("Sample Last Name");
        person.setImportant(true);
        person.setOccupation("Sample Occupation");
        person.setRole("Sample Role");
        person.setEmail("sample@vaadin.com");
        person.setDateOfBirth(LocalDate.now().minusYears(20));
        person.setPhone("+1");

        return person;
    }


}
