package com.iamalexvybornyi.util;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.collection.WebElementList;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.driver.DriverProvider;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class ElementInitializationHelper {

    public Object initPageElements(Object uiComponent) {
            final List<Field> singleElementsToInit = Arrays.stream(uiComponent.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(PageElement.class) &&
                            AbstractWebElement.class.isAssignableFrom(field.getType()))
                    .toList();
            final List<Field> elementCollectionsToInit = Arrays.stream(uiComponent.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(PageElementCollection.class) &&
                            WebElementCollection.class.isAssignableFrom(field.getType()))
                    .toList();
            initSingleElements(uiComponent, singleElementsToInit);
            initElementCollections(uiComponent, elementCollectionsToInit);
        return uiComponent;
    }

    private void initSingleElements(@NonNull Object uiComponent, @NonNull List<Field> elementsToInit) {
        elementsToInit.forEach(element -> {
            element.setAccessible(true);
            final PageElement pageElementAnnotation = element.getAnnotation(PageElement.class);
            final Class<? extends AbstractWebElement> elementClass = element.getType().asSubclass(AbstractWebElement.class);
            
            try {
                AbstractWebElement parent;
                if (pageElementAnnotation.searchWithinParent()) {
                    parent = ((AbstractWebElement) uiComponent);
                } else {
                    parent = null;
                }
                element.set(uiComponent, createElementInstance(
                        LocatorType.getElement(pageElementAnnotation.locatorType(), pageElementAnnotation.locator()),
                        parent,
                        elementClass
                ));
                initPageElements(element.get(uiComponent));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <T extends AbstractWebElement> void initElementCollections(@NonNull Object uiComponent,
                                                                       @NonNull List<Field> elementsToInit) {
        elementsToInit.forEach(element -> {
            element.setAccessible(true);
            final PageElementCollection pageElementCollectionAnnotation =
                    element.getAnnotation(PageElementCollection.class);
            try {
                final ParameterizedType elementCollectionGenericType = (ParameterizedType) element.getGenericType();
                final Class<T> elementClass = (Class<T>) elementCollectionGenericType.getActualTypeArguments()[0];
                element.set(uiComponent, new WebElementList<>(
                        LocatorType.getElement(pageElementCollectionAnnotation.locatorType(),
                                pageElementCollectionAnnotation.locator()), elementClass));
                initPageElements(element.get(uiComponent));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @NonNull
    private <T> T createElementInstance(@NonNull By locator, @Nullable AbstractWebElement parent,
                                        @NonNull Class<T> classType) {
        final Constructor<T> constructor;
        try {
            constructor = classType.getConstructor(By.class, Supplier.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            final Supplier<WebElement> webElementSupplier = parent != null ?
                    () -> parent.getRootWebElement().findElement(locator) :
                    () -> DriverProvider.getDriver().waitForElementToBeVisible(locator);
            return constructor.newInstance(locator, webElementSupplier);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
