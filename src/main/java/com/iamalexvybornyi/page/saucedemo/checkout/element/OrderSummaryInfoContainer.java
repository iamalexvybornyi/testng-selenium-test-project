package com.iamalexvybornyi.page.saucedemo.checkout.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class OrderSummaryInfoContainer extends AbstractWebElement {

    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='payment-info-value']", searchWithinParent = true)
    private Label paymentInformationLabel;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='shipping-info-value']", searchWithinParent = true)
    private Label shippingInformationLabel;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='subtotal-label']", searchWithinParent = true)
    private Label priceSubtotalLabel;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='tax-label']", searchWithinParent = true)
    private Label taxLabel;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@data-test='total-label']", searchWithinParent = true)
    private Label totalPriceLabel;

    public OrderSummaryInfoContainer(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
