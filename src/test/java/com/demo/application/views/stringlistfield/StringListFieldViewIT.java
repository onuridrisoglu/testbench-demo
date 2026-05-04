package com.demo.application.views.stringlistfield;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;

import java.lang.management.ManagementFactory;

@UsePlaywright
public class StringListFieldViewIT {

    Page page;

    @BeforeEach
    public void setup() {
        String args = ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
        Boolean headed = args.contains("jdwp") || Boolean.getBoolean("headed");
        LaunchOptions ops = new BrowserType.LaunchOptions().setHeadless(!headed);
        page = Playwright.create().chromium().launch(ops).newContext().newPage();
        page.setDefaultTimeout(30000);
        page.navigate("http://localhost:8080/string-list-field");
    }

    @AfterEach
    public void tearDown() {
        page.context().browser().close();
    }

    @Test
    @Disabled
    public void testInitialStateOfMainLayout() throws Exception {
        // Given the user is on the page MainLayout
        // Then the user should see an app layout with tag name 'vaadin-app-layout'
        Locator element = page.locator("vaadin-app-layout");
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the user should see a header with tag name 'header' and text 'Testbench Demo'
        element = page.locator("header");
        element = element.filter(new Locator.FilterOptions().setHasText("Testbench Demo"));
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the user should see a drawer toggle button with role 'button'
        Locator button = page.getByRole(AriaRole.BUTTON);
        PlaywrightAssertions.assertThat(button).isVisible();

        // And the user should see a side navigation with role 'navigation'
        Locator sideNav = page.getByRole(AriaRole.NAVIGATION);
        PlaywrightAssertions.assertThat(sideNav).isVisible();

        // And the user should see a side navigation item with role 'listitem' and text 'String List Field'
        sideNav = page.getByRole(AriaRole.LISTITEM);
        sideNav = sideNav.filter(new Locator.FilterOptions().setHasText("String List Field"));
        PlaywrightAssertions.assertThat(sideNav).isVisible();
    }

    @Test
    public void testInitialStateOfStringListFieldView() throws Exception {
        // Given the user is on the page StringListFieldView
        // Then the user should see a heading with tag name 'h3' and text 'StringListField Demo'
        Locator element = page.locator("h3");
        element = element.filter(new Locator.FilterOptions().setHasText("StringListField Demo"));
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the user should see a custom field with role 'group' and label 'Tags'
        element = page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Tags"));
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the user should see a text field with role 'textbox' and placeholder 'Add item…'
        Locator textField = page.getByRole(AriaRole.TEXTBOX);
        PlaywrightAssertions.assertThat(textField).isVisible();

        // And the user should see a button with role 'button' containing an icon with icon 'vaadin:plus'
        Locator button = page.getByRole(AriaRole.BUTTON);
        PlaywrightAssertions.assertThat(button).isVisible();

        // And the user should see a grid with tag name 'vaadin-grid'
        Locator grid = page.locator("vaadin-grid");
        PlaywrightAssertions.assertThat(grid).isVisible();

        // And the user should see a button with role 'button' and text 'Set example values'
        button = page.getByRole(AriaRole.BUTTON);
        button = button.filter(new Locator.FilterOptions().setHasText("Set example values"));
        PlaywrightAssertions.assertThat(button).isVisible();

        // And the user should see a button with role 'button' and text 'Clear'
        button = page.getByRole(AriaRole.BUTTON);
        button = button.filter(new Locator.FilterOptions().setHasText("Clear"));
        PlaywrightAssertions.assertThat(button).isVisible();

        // And the user should see a paragraph with tag name 'p' and text 'Current value: (empty)'
        element = page.locator("p");
        element = element.filter(new Locator.FilterOptions().setHasText("Current value: (empty)"));
        PlaywrightAssertions.assertThat(element).isVisible();
    }

    @Test
    public void testUserEnteringTagInTextField() throws Exception {
        // Given the user is on the page StringListFieldView
        // When the user enters 'Alpha' in the text field with role 'textbox' and placeholder 'Add item…'
        Locator textField = page.getByRole(AriaRole.TEXTBOX);
        textField.fill("Alpha");

        // Then the grid with tag name 'vaadin-grid' should contain text 'Alpha'
        Locator grid = page.locator("vaadin-grid-cell-content");
        grid = grid.filter(new Locator.FilterOptions().setHasText("Alpha"));
        PlaywrightAssertions.assertThat(grid).isVisible();
    }

    @Test
    public void testUserClickingSetExampleValuesButton() throws Exception {
        // Given the user is on the page StringListFieldView
        // When the user clicks on the button with role 'button' and text 'Set example values'
        Locator button = page.getByRole(AriaRole.BUTTON);
        button = button.filter(new Locator.FilterOptions().setHasText("Set example values"));
        button.click();

        // Then the paragraph with tag name 'p' should have text 'Current value: Alpha, Beta, Gamma'
        Locator element = page.locator("p");
        element = element.filter(new Locator.FilterOptions().setHasText("Current value: Alpha, Beta, Gamma"));
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the grid with tag name 'vaadin-grid' should contain text 'Alpha'
        Locator grid = page.locator("vaadin-grid-cell-content");
        grid = grid.filter(new Locator.FilterOptions().setHasText("Alpha"));
        PlaywrightAssertions.assertThat(grid).isVisible();

        // And the grid with tag name 'vaadin-grid' should contain text 'Beta'
        grid = page.locator("vaadin-grid-cell-content");
        grid = grid.filter(new Locator.FilterOptions().setHasText("Beta"));
        PlaywrightAssertions.assertThat(grid).isVisible();

        // And the grid with tag name 'vaadin-grid' should contain text 'Gamma'
        grid = page.locator("vaadin-grid-cell-content");
        grid = grid.filter(new Locator.FilterOptions().setHasText("Gamma"));
        PlaywrightAssertions.assertThat(grid).isVisible();
    }

    @Test
    public void testUserClickingClearButton() throws Exception {
        // Given the user is on the page StringListFieldView
        // When the user clicks on the button with role 'button' and text 'Clear'
        Locator button = page.getByRole(AriaRole.BUTTON);
        button = button.filter(new Locator.FilterOptions().setHasText("Clear"));
        button.click();

        // Then the paragraph with tag name 'p' should have text 'Current value: (empty)'
        Locator element = page.locator("p");
        element = element.filter(new Locator.FilterOptions().setHasText("Current value: (empty)"));
        PlaywrightAssertions.assertThat(element).isVisible();

        // And the grid with tag name 'vaadin-grid' should contain no rows
        Locator grid = page.locator("vaadin-grid-cell-content");
        PlaywrightAssertions.assertThat(grid).isEmpty();
    }
}