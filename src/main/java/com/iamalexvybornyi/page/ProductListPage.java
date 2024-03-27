package com.iamalexvybornyi.page;

import com.iamalexvybornyi.page.element.CommonHeaderElement;
import com.iamalexvybornyi.page.element.ProductItemElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class ProductListPage {
    private final By productListContainer = By.xpath("//div[@class='inventory_list']");
    private final By productItems = By.xpath("//div[@class='inventory_item']");
    private final ProductItemElement productItemElement;
    private final CommonHeaderElement commonHeaderElement;
}
