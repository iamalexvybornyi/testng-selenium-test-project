package com.iamalexvybornyi.core.element;

import com.iamalexvybornyi.driver.DriverProvider;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

@Getter
public abstract class AbstractWebElement {
    @NonNull
    protected final By locator;
    @Nullable
    protected final AbstractWebElement parent;
    @NonNull
    protected final Supplier<WebElement> webElementSupplier;

    public AbstractWebElement(@NonNull By locator) {
        this.locator = locator;
        this.webElementSupplier = () -> DriverProvider.getDriver().waitForElementToBeVisible(this.locator);
        this.parent = null;
    }

    public AbstractWebElement(@NonNull By locator, @Nullable AbstractWebElement parent) {
        this.locator = locator;
        this.webElementSupplier = parent != null ? () -> parent.getRootWebElement().findElement(locator) :
                () -> DriverProvider.getDriver().waitForElementToBeVisible(this.locator);
        this.parent = parent;
    }

    public AbstractWebElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        this.locator = locator;
        this.webElementSupplier = webElementSupplier;
        this.parent = null;
    }

    public AbstractWebElement(@NonNull By locator, @Nullable AbstractWebElement parent,
                              @NonNull Supplier<WebElement> webElementSupplier) {
        this.locator = locator;
        this.webElementSupplier = webElementSupplier;
        this.parent = parent;
    }

    @NonNull
    public WebElement getRootWebElement() {
        return this.webElementSupplier.get();
    }

    public boolean waitForElementToDisappear() {
        return DriverProvider.getDriver().waitForElementToBeInvisible(this.locator);
    }
}
