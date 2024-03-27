package com.iamalexvybornyi.page.element;

import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ProductItemElement {
    private final By title = By.xpath(".//div[@class='inventory_item_name ']");
    private final By description = By.xpath(".//div[@class='inventory_item_desc']");
    private final By price = By.xpath(".//div[@class='inventory_item_price']");
    private final By image = By.xpath(".//div[@class='inventory_item_img']//img");
    private final By addToCartButton = By.xpath(".//button[contains(@id,'add-to-cart')]");
    private final By removeFromCartButton = By.xpath(".//button[contains(@id, 'remove-sauce-labs')]");
}
