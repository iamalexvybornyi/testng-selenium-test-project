package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class Label extends AbstractWebElement implements HasText {

    public Label(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public Label(@NonNull By locator, @Nullable AbstractWebElement parent) {
        super(locator, parent);
    }

    @Override
    @NonNull
    public String getText() {
        return getRootWebElement().getText();
    }
}
