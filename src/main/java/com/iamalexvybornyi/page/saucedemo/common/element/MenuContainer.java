package com.iamalexvybornyi.page.saucedemo.common.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Link;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class MenuContainer extends AbstractWebElement {

    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='bm-burger-button']", searchWithinParent = true)
    private Button menuButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//a[@data-test='inventory-sidebar-link']", searchWithinParent = true)
    private Link allItemslink;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//a[@data-test='logout-sidebar-link']", searchWithinParent = true)
    private Link logoutLink;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//a[@data-test='reset-sidebar-link']", searchWithinParent = true)
    private Link resetAppStatelink;

    public MenuContainer(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
