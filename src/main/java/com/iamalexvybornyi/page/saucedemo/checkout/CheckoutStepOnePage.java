package com.iamalexvybornyi.page.saucedemo.checkout;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Input;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CheckoutStepOnePage implements Page {
    @PageElement(locatorType = LocatorType.ID, locator = "first-name")
    private Input firstNameInput;
    @PageElement(locatorType = LocatorType.ID, locator = "last-name")
    private Input lastNameInput;
    @PageElement(locatorType = LocatorType.ID, locator = "postal-code")
    private Input postalCodeInput;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//h3[@data-test='error']")
    private Label errorMessage;
    @PageElement(locatorType = LocatorType.ID, locator = "cancel")
    private Button cancelButton;
    @PageElement(locatorType = LocatorType.ID, locator = "continue")
    private Button continueButton;
}
