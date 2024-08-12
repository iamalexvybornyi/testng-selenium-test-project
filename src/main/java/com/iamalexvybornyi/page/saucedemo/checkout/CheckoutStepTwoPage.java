package com.iamalexvybornyi.page.saucedemo.checkout;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.page.saucedemo.cart.element.CartItemElement;
import com.iamalexvybornyi.page.saucedemo.checkout.element.OrderSummaryInfoContainer;
import com.iamalexvybornyi.util.buttons.ButtonName;
import com.iamalexvybornyi.util.buttons.CheckoutStepTwoPageButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class CheckoutStepTwoPage implements Page, PageWithButtons {
    @PageElementCollection(locatorType = LocatorType.XPATH, locator = "//div[@data-test='inventory-item']")
    private WebElementCollection<CartItemElement> cartItemCollection;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@class='summary_info']")
    private OrderSummaryInfoContainer orderSummaryInfoContainer;
    @PageElement(locatorType = LocatorType.ID, locator = "cancel")
    private Button cancelButton;
    @PageElement(locatorType = LocatorType.ID, locator = "finish")
    private Button finishButton;

    @Override
    @NonNull
    public String getPageName() {
        return PageName.CHECKOUT_STEP_TWO.getPageName();
    }

    @Override
    public @NonNull Map<ButtonName, Button> getButtons() {
        return Map.ofEntries(
                Map.entry(CheckoutStepTwoPageButtonName.CANCEL, getCancelButton()),
                Map.entry(CheckoutStepTwoPageButtonName.FINISH, getFinishButton())
        );
    }
}
