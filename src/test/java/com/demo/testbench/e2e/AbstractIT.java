package com.demo.testbench.e2e;

import com.vaadin.testbench.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class AbstractIT extends BrowserTestBase implements DriverSupplier {

    @RegisterExtension
    public ScreenshotOnFailureExtension screenshotOnFailureExtension =
            new ScreenshotOnFailureExtension(this, true);

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        return TestBench.createDriver(new ChromeDriver(options));
    }
}
