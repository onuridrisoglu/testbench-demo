package com.demo.testbench.unit;

import com.demo.application.components.StringListField;
import com.demo.testbench.unit.tester.StringListFieldTester;
import com.vaadin.browserless.SpringBrowserlessTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringListFieldUnitTest extends SpringBrowserlessTest {

    @Test
    public void testAddingItem() {

        StringListField stringListField = new StringListField();
        test(StringListFieldTester.class, stringListField).addValue("Test Item");

        Assertions.assertTrue(stringListField.getValue().contains("Test Item"), "The value should contain the added item");
    }

    @Test
    public void testTrimmingSpaces() {
        StringListField stringListField = new StringListField();

        test(StringListFieldTester.class, stringListField).addValue("  Test Item  ");
        Assertions.assertFalse(stringListField.getValue().contains("  Test Item  "), "The value should not contain the item with leading/trailing spaces");
        Assertions.assertTrue(stringListField.getValue().contains("Test Item"), "The value should contain the trimmed item");

    }
}
