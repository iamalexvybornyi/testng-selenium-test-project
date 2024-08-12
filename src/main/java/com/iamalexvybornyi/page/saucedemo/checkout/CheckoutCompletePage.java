package com.iamalexvybornyi.page.saucedemo.checkout;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.util.buttons.ButtonName;
import com.iamalexvybornyi.util.buttons.CheckoutCompletePageButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
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
    @NonNull
    public String getPageName() {
        return PageName.CHECKOUT_COMPLETE.getPageName();
    }

    @Override
    public @NonNull Map<ButtonName, Button> getButtons() {
        return Map.ofEntries(
                Map.entry(CheckoutCompletePageButtonName.BACK_HOME, getBackHomeButton())
        );
    }
}
