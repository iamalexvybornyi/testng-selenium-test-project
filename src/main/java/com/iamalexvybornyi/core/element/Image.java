package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;

import java.util.function.Supplier;

public class Image extends AbstractWebElement implements HasImageAttributes {

    public Image(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    @Override
    @Nullable
    public String getImageAlt() {
        return getRootWebElement().getAttribute("alt");
    }

    @Override
    @Nullable
    public String getImageSource() {
        return getRootWebElement().getAttribute("src");
    }
}
