package com.iamalexvybornyi.page.saucedemo.cart;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.page.saucedemo.cart.element.CartItemElement;
import com.iamalexvybornyi.page.saucedemo.common.element.CommonHeaderElement;
import com.iamalexvybornyi.util.buttons.ButtonName;
import com.iamalexvybornyi.util.buttons.CartPageButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class CartPage implements Page, PageWithButtons {

    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='primary-header']")
    private CommonHeaderElement commonHeaderElement;
    @PageElementCollection(locatorType = LocatorType.XPATH, locator = "//div[@data-test='inventory-item']")
    private WebElementCollection<CartItemElement> cartItemCollection;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//button[@data-test='checkout']")
    private Button checkoutButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//button[@data-test='continue-shopping']")
    private Button continueShoppingButton;

    @Override
    @NonNull
    public String getPageName() {
        return PageName.CART.getPageName();
    }

    @Override
    public @NonNull Map<ButtonName, Button> getButtons() {
        return Map.ofEntries(
                Map.entry(CartPageButtonName.CHECKOUT, getCheckoutButton()),
                Map.entry(CartPageButtonName.CONTINUE_SHOPPING, getContinueShoppingButton())
        );
    }
}
