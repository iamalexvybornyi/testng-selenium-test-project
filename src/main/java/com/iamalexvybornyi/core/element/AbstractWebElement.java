package com.iamalexvybornyi.core.element;

import com.iamalexvybornyi.driver.DriverProvider;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public abstract class AbstractWebElement {
    @NonNull
    protected final By locator;
    @NonNull
    protected final Supplier<WebElement> webElementSupplier;

    public AbstractWebElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        this.locator = locator;
        this.webElementSupplier = webElementSupplier;
    }

    @NonNull
    public WebElement getRootWebElement() {
        return this.webElementSupplier.get();
    }

    public boolean waitForElementToDisappear() {
        return DriverProvider.getDriver().waitForElementToBeInvisible(this.locator);
    }
}
