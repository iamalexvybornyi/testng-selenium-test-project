package com.iamalexvybornyi.core.element.collection;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.util.ElementInitializationHelper;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WebElementList<T extends AbstractWebElement> implements WebElementCollection<T> {
    @NonNull
    private final By locator;
    @NonNull
    private final Class<T> classType;

    public WebElementList(@NonNull By locator, @NonNull Class<T> classType) {
        this.locator = locator;
        this.classType = classType;
    }

    @Override
    public @NonNull List<T> getElements() {
        List<WebElement> webElements = DriverProvider.getDriver().waitForElementsToBeVisible(locator);
        List<T> abstractWebElements = new ArrayList<>();
        webElements.forEach(webElement -> abstractWebElements.add(createElementInstance(locator, () -> webElement)));
        ElementInitializationHelper elementInitializationHelper = new ElementInitializationHelper();
        abstractWebElements.forEach(elementInitializationHelper::initPageElements);
        return abstractWebElements;
    }

    @NonNull
    private T createElementInstance(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        final Constructor<T> constructor;
        try {
            constructor = classType.getConstructor(By.class, Supplier.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            return constructor.newInstance(locator, webElementSupplier);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NonNull
    public T getElement(int index) {
        return getElements().get(index);
    }
}
