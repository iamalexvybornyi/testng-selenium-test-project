package com.iamalexvybornyi.page;

import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.collection.WebElementList;
import com.iamalexvybornyi.page.element.CommonHeaderElement;
import com.iamalexvybornyi.page.element.ProductItemElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ProductListPage {
    private final Div productListContainer = new Div(By.xpath("//div[@class='inventory_list']"));
    private final WebElementList<ProductItemElement> productItemList = new WebElementList<>(By.xpath("//div[@class='inventory_item']"), ProductItemElement.class);
    private final CommonHeaderElement commonHeaderElement = new CommonHeaderElement(By.xpath("//div[@data-test='primary-header']"));
}
