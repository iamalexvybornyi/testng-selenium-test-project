package com.iamalexvybornyi.action.saucedemo;

import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.model.CartProductItem;
import com.iamalexvybornyi.model.CheckoutInformation;
import com.iamalexvybornyi.model.OrderSummary;
import com.iamalexvybornyi.page.saucedemo.cart.element.CartItemElement;
import com.iamalexvybornyi.page.saucedemo.checkout.CheckoutCompletePage;
import com.iamalexvybornyi.page.saucedemo.checkout.CheckoutStepOnePage;
import com.iamalexvybornyi.page.saucedemo.checkout.CheckoutStepTwoPage;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckoutAction {

    @NonNull
    private final CheckoutStepOnePage checkoutStepOnePage;
    @NonNull
    private final CheckoutStepTwoPage checkoutStepTwoPage;
    @NonNull
    private final CheckoutCompletePage checkoutCompletePage;

    @Step("Fill in checkout information form")
    public void fillInCheckoutInformationForm(@NonNull CheckoutInformation checkoutInformation) {
        log.info("Filling in checkout information with the following data: {}", checkoutInformation);
        log.info("Filling in first name field with the following data: {}", checkoutInformation.firstName());
        checkoutStepOnePage.getFirstNameInput().enterText(checkoutInformation.firstName());
        log.info("Filling in last name field with the following data: {}", checkoutInformation.lastName());
        checkoutStepOnePage.getLastNameInput().enterText(checkoutInformation.lastName());
        log.info("Filling in postal code field with the following data: {}", checkoutInformation.postalCode());
        checkoutStepOnePage.getPostalCodeInput().enterText(checkoutInformation.postalCode());
    }

    @Step("Click Continue button on step one of the checkout process")
    public void clickContinueButtonOnCheckoutStepOne() {
        log.info("Clicking 'Continue' button on step one of the checkout process");
        checkoutStepOnePage.getContinueButton().click();
    }

    @Step("Click Cancel button")
    public void clickCancelButtonOnCheckoutStepOne() {
        log.info("Clicking 'Cancel' button on step one of the checkout process");
        checkoutStepOnePage.getCancelButton().click();
    }

    @Step("Click Continue button on step two of the checkout process")
    public void clickFinishButtonOnCheckoutStepTwo() {
        log.info("Clicking 'Finish' button on step two of the checkout process");
        checkoutStepTwoPage.getFinishButton().click();
    }

    @Step("Click Cancel button on step two of the checkout process")
    public void clickCancelButtonOnCheckoutStepTwo() {
        log.info("Clicking 'Cancel' button on step two of the checkout process");
        checkoutStepTwoPage.getCancelButton().click();
    }

    @Step("Verify checkout information form error")
    public void verifyCheckoutInformationFormError(@NonNull String expectedError) {
        log.info("Verifying the checkout information form error");
        log.info("Getting the error from the checkout information form");
        final String actualErrorMessage = checkoutStepOnePage.getErrorMessage().getText();
        log.info("Verifying that the checkout information form error '{}' matches the expected '{}'",
                actualErrorMessage, expectedError);
        Assert.assertEquals(actualErrorMessage, expectedError,
                "Checkout information form error does not match the expected value!");
    }

    @Step("Verify that checkout process has been completed")
    public void verifyCheckoutProcessIsCompleted() {
        log.info("Verifying the checkout process has been completed");
        log.info("Getting the checkout complete container");
        final Div checkoutCompleteContainer = checkoutCompletePage.getCheckoutCompleteContainer();
        log.info("Verifying that the checkout complete container is displayed");
        Assert.assertTrue(checkoutCompleteContainer.getRootWebElement().isDisplayed(),
                "checkout complete container is not displayed!");
    }

    @Step("Verify the expected products are displayed in cart")
    public void verifyExpectedProductsAreDisplayed(@NonNull List<CartProductItem> expectedCartProductItems) {
        log.info("Getting product items from cart");
        final List<CartItemElement> cartItemsFromPage = checkoutStepTwoPage.getCartItemCollection().getElements();
        final List<CartProductItem> actualCartProductItems = new ArrayList<>();
        cartItemsFromPage.forEach(cartItemElement -> {
            actualCartProductItems.add(new CartProductItem(
                    cartItemElement.getProductItemElement().getTitle().getText(),
                    cartItemElement.getProductItemElement().getDescription().getText(),
                    cartItemElement.getProductItemElement().getPrice().getText(),
                    Integer.valueOf(cartItemElement.getQuantityLabel().getText())
            ));
        });
        log.info("Verifying the cart items from the page '{}' match the expected '{}'",
                actualCartProductItems, expectedCartProductItems);
        Assert.assertEquals(actualCartProductItems, expectedCartProductItems,
                "Cart items from the page do not match the expected ones!");
    }

    @Step("Verify the expected order summary is displayed")
    public void verifyExpectedOrderSummaryIsDisplayed(@NonNull OrderSummary expectedOrderSummary) {
        log.info("Getting order summary from the page");
        final String paymentInfo = checkoutStepTwoPage.getOrderSummaryInfoContainer().getPaymentInformationLabel().getText();
        final OrderSummary orderSummaryFromPage = getOrderSummaryFromPage(paymentInfo);
        log.info("Verifying the order summary from the page '{}' matches the expected '{}'",
                orderSummaryFromPage, expectedOrderSummary);
        Assert.assertEquals(orderSummaryFromPage, expectedOrderSummary,
                "Order summary from the page does not match the expected one!");
    }

    @NonNull
    private OrderSummary getOrderSummaryFromPage(String paymentInfo) {
        final String shippingInfo = checkoutStepTwoPage.getOrderSummaryInfoContainer().getShippingInformationLabel()
                .getText();
        final String priceSubtotal = checkoutStepTwoPage.getOrderSummaryInfoContainer().getPriceSubtotalLabel()
                .getText();
        final String tax = checkoutStepTwoPage.getOrderSummaryInfoContainer().getTaxLabel().getText();
        final String totalPrice = checkoutStepTwoPage.getOrderSummaryInfoContainer().getTotalPriceLabel().getText();
        return new OrderSummary(paymentInfo, shippingInfo, priceSubtotal, tax, totalPrice);
    }
}
