package com.iamalexvybornyi.core.element;

import com.iamalexvybornyi.driver.DriverProvider;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public class IFrame extends AbstractWebElement {
    public IFrame(@NonNull By locator) {
        super(locator);
    }

    public IFrame(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public IFrame(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public IFrame(@NonNull By locator, AbstractWebElement parent, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, parent, webElementSupplier);
    }

    public void switchToFrame() {
        WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver().getOriginalWebDriver(),
                Duration.of(10, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void quitFrame() {
        DriverProvider.getDriver().switchTo().defaultContent();
    }
}
