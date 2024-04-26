package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Button extends AbstractWebElement implements Clickable {

    public Button(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    @Override
    public void click() {
        getRootWebElement().click();
    }
}
