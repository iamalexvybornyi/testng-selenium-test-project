package com.iamalexvybornyi.page.saucedemo.productlist;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.Select;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.page.saucedemo.common.element.CommonHeaderElement;
import com.iamalexvybornyi.page.saucedemo.productlist.element.ProductItemElement;
import com.iamalexvybornyi.util.buttons.ButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
import com.iamalexvybornyi.util.buttons.ProductListButtonName;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class ProductListPage implements Page, PageWithButtons {
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@class='inventory_list']")
    private Div productListContainer;
    @PageElementCollection(locatorType = LocatorType.XPATH, locator = "//div[@class='inventory_item']")
    private WebElementCollection<ProductItemElement> productItemCollection;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='primary-header']")
    private CommonHeaderElement commonHeaderElement;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//select[@data-test='product-sort-container']")
    private Select productListSortingDropdown;

    @Override
    @NonNull
    public String getPageName() {
        return PageName.PRODUCT_LIST.getPageName();
    }

    @Override
    @NonNull
    public Map<ButtonName, Button> getButtons() {
        return Map.ofEntries(
                Map.entry(ProductListButtonName.SIDEBAR_MENU, getCommonHeaderElement().getMenuContainer().getMenuButton())
        );
    }
}
