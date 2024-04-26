package com.iamalexvybornyi.core.element;

import com.iamalexvybornyi.driver.DriverProvider;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.function.Supplier;

public class IFrame extends AbstractWebElement {

    public IFrame(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public IFrame(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public void switchToFrame() {
        DriverProvider.getDriver().waitForFrameToBeAvailableAndSwitchToIt(locator);
    }

    public void quitFrame() {
        DriverProvider.getDriver().switchTo().defaultContent();
    }
}
