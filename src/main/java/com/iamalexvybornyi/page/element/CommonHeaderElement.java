package com.iamalexvybornyi.page.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Label;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class CommonHeaderElement extends AbstractWebElement {

    private final Button menuButton = new Button(By.xpath("//div[@id='menu_button_container']//div[@class='bm-burger-button']"));
    private final Button shoppingCartLink = new Button(By.xpath("//a[@class='shopping_cart_link']"));
    private final Label shoppingCartBadge = new Label(By.xpath("//span[@class='shopping_cart_badge']"));

    public CommonHeaderElement(By locator) {
        super(locator);
    }

    public CommonHeaderElement(By locator, Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
