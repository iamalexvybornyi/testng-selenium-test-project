package com.iamalexvybornyi.page.saucedemo.checkout;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CheckoutCompletePage implements Page {
    @PageElement(locatorType = LocatorType.ID, locator = "checkout_complete_container")
    private Div checkoutCompleteContainer;
    @PageElement(locatorType = LocatorType.ID, locator = "back-to-products")
    private Button backHomeButton;
}
