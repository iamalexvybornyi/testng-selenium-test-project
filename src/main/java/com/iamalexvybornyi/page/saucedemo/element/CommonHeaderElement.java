package com.iamalexvybornyi.page.saucedemo.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class CommonHeaderElement extends AbstractWebElement {
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@id='menu_button_container']//div[@class='bm-burger-button']")
    private Button menuButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//a[@class='shopping_cart_link']")
    private Button shoppingCartLink;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//span[@class='shopping_cart_badge']")
    private Label shoppingCartBadge;

    public CommonHeaderElement(By locator) {
        super(locator);
    }

    public CommonHeaderElement(By locator, Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public CommonHeaderElement(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public CommonHeaderElement(@NonNull By locator, AbstractWebElement parent, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, parent, webElementSupplier);
    }
}