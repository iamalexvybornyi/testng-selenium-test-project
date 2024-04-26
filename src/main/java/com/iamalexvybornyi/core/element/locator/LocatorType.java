package com.iamalexvybornyi.core.element.locator;

import com.iamalexvybornyi.core.exception.UnsupportedLocatorTypeException;
import lombok.NonNull;
import org.openqa.selenium.By;

public enum LocatorType {

    ID,
    LINK_TEXT,
    PARTIAL_LINK_TEXT,
    NAME,
    TAG_NAME,
    XPATH,
    CLASS_NAME,
    CSS;

    public static By getElement(@NonNull LocatorType locatorType, @NonNull String locator) {
        switch (locatorType) {
            case ID -> {
                return By.id(locator);
            }
            case LINK_TEXT -> {
                return By.linkText(locator);
            }
            case PARTIAL_LINK_TEXT -> {
                return By.partialLinkText(locator);
            }
            case NAME -> {
                return By.name(locator);
            }
            case TAG_NAME -> {
                return By.tagName(locator);
            }
            case XPATH -> {
                return By.xpath(locator);
            }
            case CLASS_NAME -> {
                return By.className(locator);
            }
            case CSS -> {
                return By.cssSelector(locator);
            }
        }
        throw new UnsupportedLocatorTypeException();
    }
}
