package com.iamalexvybornyi.page.saucedemo.cart.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class CartItemElement extends AbstractWebElement {

    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@data-test='item-quantity']", searchWithinParent = true)
    private Label quantityLabel;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='cart_item_label']", searchWithinParent = true)
    private ProductItemElement productItemElement;

    public CartItemElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
