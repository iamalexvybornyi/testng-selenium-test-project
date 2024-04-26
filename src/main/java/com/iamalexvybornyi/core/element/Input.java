package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class Input extends AbstractWebElement implements CanInput, HasText {

    public Input(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    @Override
    public void enterText(@NonNull String textToEnter) {
        getRootWebElement().sendKeys(textToEnter);
    }

    @Override
    @NonNull
    public String getText() {
        return getRootWebElement().getText();
    }
}
