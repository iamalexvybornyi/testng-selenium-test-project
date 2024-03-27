package com.iamalexvybornyi.page.element;

import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonHeaderElement {
    private final By menuButton = By.xpath("//div[@id='menu_button_container']//div[@class='bm-burger-button']");
    private final By shoppingCartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By shoppingCartBadge = By.xpath("//span[@class='shopping_cart_badge']");
}
