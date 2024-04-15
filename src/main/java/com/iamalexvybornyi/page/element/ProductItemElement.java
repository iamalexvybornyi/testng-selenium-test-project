package com.iamalexvybornyi.page.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Image;
import com.iamalexvybornyi.core.element.Label;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class ProductItemElement extends AbstractWebElement {

    private final Label title = new Label(By.xpath(".//div[@class='inventory_item_name ']"), this);
    private final Label description = new Label(By.xpath(".//div[@class='inventory_item_desc']"), this);
    private final Label price = new Label(By.xpath(".//div[@class='inventory_item_price']"), this);
    private final Image image = new Image(By.xpath(".//div[@class='inventory_item_img']//img"), this);
    private final Button addToCartButton = new Button(By.xpath(".//button[contains(@id,'add-to-cart')]"), this);
    private final Button removeFromCartButton = new Button(By.xpath(".//button[contains(@id, 'remove-sauce-labs')]"), this);

    public ProductItemElement(By locator) {
        super(locator);
    }

    public ProductItemElement(By locator, Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
