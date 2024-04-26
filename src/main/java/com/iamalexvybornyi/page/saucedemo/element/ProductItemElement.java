package com.iamalexvybornyi.page.saucedemo.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Image;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class ProductItemElement extends AbstractWebElement {

    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='inventory_item_name ']", searchWithinParent = true)
    private Label title;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='inventory_item_desc']", searchWithinParent = true)
    private Label description;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='inventory_item_price']", searchWithinParent = true)
    private Label price;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@class='inventory_item_img']//img", searchWithinParent = true)
    private Image image;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//button[contains(@id,'add-to-cart')]", searchWithinParent = true)
    private Button addToCartButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//button[contains(@id, 'remove-sauce-labs')]", searchWithinParent = true)
    private Button removeFromCartButton;

    public ProductItemElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

}
