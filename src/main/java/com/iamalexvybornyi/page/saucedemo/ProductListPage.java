package com.iamalexvybornyi.page.saucedemo;

import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.page.saucedemo.element.CommonHeaderElement;
import com.iamalexvybornyi.page.saucedemo.element.ProductItemElement;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ProductListPage implements Page {
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@class='inventory_list']")
    private Div productListContainer;
    @PageElementCollection(locatorType = LocatorType.XPATH, locator = "//div[@class='inventory_item']")
    private WebElementCollection<ProductItemElement> productItemList;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='primary-header']")
    private CommonHeaderElement commonHeaderElement;
}
