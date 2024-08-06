package com.iamalexvybornyi.page.saucedemo.checkout;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class CheckoutCompletePage implements Page, PageWithButtons {
    @PageElement(locatorType = LocatorType.ID, locator = "checkout_complete_container")
    private Div checkoutCompleteContainer;
    @PageElement(locatorType = LocatorType.ID, locator = "back-to-products")
    private Button backHomeButton;

    @Override
    public @NonNull String getPageName() {
        return "Checkout Complete";
    }

    @Override
    public @NonNull Map<String, Button> getButtons() {
        return Map.ofEntries(
                Map.entry("Back Home", getBackHomeButton())
        );
    }
}
