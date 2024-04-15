package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class Button extends AbstractWebElement implements Clickable {
    public Button(@NonNull By locator) {
        super(locator);
    }

    public Button(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public Button(@NonNull By locator, @Nullable AbstractWebElement parent) {
        super(locator, parent);
    }

    public Button(@NonNull By locator, @Nullable AbstractWebElement parent,
                  @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, parent, webElementSupplier);
    }

    @Override
    public void click() {
        getRootWebElement().click();
    }
}
