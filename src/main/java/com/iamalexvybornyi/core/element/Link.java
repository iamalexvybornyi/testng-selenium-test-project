package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class Link extends AbstractWebElement implements Clickable, HasText, HasLinkAttribute {

    public Link(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public Link(@NonNull By locator, @Nullable AbstractWebElement parent) {
        super(locator, parent);
    }

    @Override
    public void click() {
        getRootWebElement().click();
    }

    @Override
    public @NonNull String getText() {
        return getRootWebElement().getText();
    }

    @Override
    public @NonNull String getLinkAttribute() {
        return getRootWebElement().getAttribute("href");
    }
}
