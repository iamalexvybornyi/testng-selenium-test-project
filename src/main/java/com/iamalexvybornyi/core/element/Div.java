package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class Div extends AbstractWebElement implements HasText {
    public Div(@NonNull By locator) {
        super(locator);
    }

    public Div(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public Div(@NonNull By locator, @Nullable AbstractWebElement parent) {
        super(locator, parent);
    }

    public Div(@NonNull By locator, @Nullable AbstractWebElement parent,
               @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, parent, webElementSupplier);
    }

    @Override
    @NonNull
    public String getText() {
        return getRootWebElement().getText();
    }
}
