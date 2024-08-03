package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Label extends AbstractWebElement implements HasText, Clickable {

    public Label(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    @Override
    @NonNull
    public String getText() {
        return getRootWebElement().getText();
    }

    @Override
    public void click() {
        getRootWebElement().click();
    }
}
