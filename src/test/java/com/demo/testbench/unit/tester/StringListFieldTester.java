package com.demo.testbench.unit.tester;

import com.demo.application.components.StringListField;
import com.vaadin.browserless.ComponentTester;
import com.vaadin.browserless.Tests;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

@Tests(StringListField.class)
public class StringListFieldTester extends ComponentTester<StringListField> {

    public StringListFieldTester(StringListField component) {
        super(component);
    }

    public void addValue(String value) {
        TextField input = find(TextField.class).single();
        Button addButton = find(Button.class).withTheme("icon").single();

        input.setValue(value);
        addButton.click();
    }
}
