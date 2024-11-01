package com.demo.testbench.e2e.masterdetail;

import com.demo.application.data.SamplePerson;
import com.google.common.base.Strings;
import com.vaadin.flow.component.checkbox.testbench.CheckboxElement;
import com.vaadin.flow.component.datepicker.testbench.DatePickerElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.annotations.Attribute;
import com.vaadin.testbench.elementsbase.Element;

@Element("div")
@Attribute(name = "id", value = "editor")
public class FormEditorElement extends TestBenchElement {

    public void fillPersonDetails(SamplePerson person) {
        getFirstName().setValue(person.getFirstName());
        getLastName().setValue(person.getLastName());
        getDateOfBirth().setDate(person.getDateOfBirth());
        getEmail().setValue(person.getEmail());
        getPhone().setValue(person.getPhone());
        getOccupation().setValue(person.getOccupation());
        getRole().setValue(person.getRole());
        getImportant().setChecked(person.isImportant());
    }

    public boolean isFormEmpty() {
        return Strings.isNullOrEmpty(getFirstName().getValue())
                && Strings.isNullOrEmpty(getLastName().getValue())
                && Strings.isNullOrEmpty(getEmail().getValue())
                && Strings.isNullOrEmpty(getPhone().getValue())
                && Strings.isNullOrEmpty(getRole().getValue())
                && Strings.isNullOrEmpty(getOccupation().getValue())
                && Strings.isNullOrEmpty(getDateOfBirth().getInputValue())
                && !getImportant().isChecked();
    }

    public TextFieldElement getFirstName() {
        return $(TextFieldElement.class).id("firstName");
    }

    public TextFieldElement getLastName() {
        return $(TextFieldElement.class).id("lastName");
    }

    public TextFieldElement getEmail() {
        return $(TextFieldElement.class).id("email");
    }

    public TextFieldElement getPhone() {
        return $(TextFieldElement.class).id("phone");
    }

    public DatePickerElement getDateOfBirth() {
        return $(DatePickerElement.class).id("dateOfBirth");
    }

    public TextFieldElement getOccupation() {
        return $(TextFieldElement.class).id("occupation");
    }

    public TextFieldElement getRole() {
        return $(TextFieldElement.class).id("role");
    }

    public CheckboxElement getImportant() {
        return $(CheckboxElement.class).id("important");
    }
}
