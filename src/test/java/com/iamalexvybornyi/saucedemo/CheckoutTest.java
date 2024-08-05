package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.*;
import com.iamalexvybornyi.model.CartProductItem;
import com.iamalexvybornyi.model.CheckoutInformation;
import com.iamalexvybornyi.model.OrderSummary;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CheckoutTest extends BaseSauceDemoTest {

    private static final String SAUCE_LABS_BACKPACK_PRODUCT = "Sauce Labs Backpack";
    private static final String SAUCE_LABS_BIKE_LIGHT_PRODUCT = "Sauce Labs Bike Light";
    private static final String PAYMENT_INFORMATION = "SauceCard #31337";
    private static final String SHIPPING_INFORMATION = "Free Pony Express Delivery!";

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;
    @Autowired
    private CartAction cartAction;
    @Autowired
    private CommonAction commonAction;
    @Autowired
    private CheckoutAction checkoutAction;

    @BeforeMethod
    private void loginToWebsite() {
        loginAction.enterUsername(STANDARD_USER_NAME);
        loginAction.enterPassword(STANDARD_USER_PASSWORD);
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyAllFieldsAreRequiredOnCheckoutStepOneTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.clickCheckoutButton();
        checkoutAction.fillInCheckoutInformationForm(new CheckoutInformation("", "", ""));
        checkoutAction.clickContinueButtonOnCheckoutStepOne();
        checkoutAction.verifyCheckoutInformationFormError("Error: First Name is required");
        checkoutAction.fillInCheckoutInformationForm(new CheckoutInformation("FirstName", "", ""));
        checkoutAction.clickContinueButtonOnCheckoutStepOne();
        checkoutAction.verifyCheckoutInformationFormError("Error: Last Name is required");
        checkoutAction.fillInCheckoutInformationForm(new CheckoutInformation("FirstName", "LastName", ""));
        checkoutAction.clickContinueButtonOnCheckoutStepOne();
        checkoutAction.verifyCheckoutInformationFormError("Error: Postal Code is required");
    }

    @Test
    public void verifyCancelButtonsReturnsUserToCartFromCheckoutStepOneTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.clickCheckoutButton();
        checkoutAction.clickCancelButtonOnCheckoutStepOne();
        commonAction.verifyTheActualUrlMatchesTheExpected(urlConfiguration.getSaucedemo().getCart());
    }

    @Test
    public void verifyCancelButtonsReturnsUserToProductListFromCheckoutStepTwoTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.clickCheckoutButton();
        checkoutAction.fillInCheckoutInformationForm(new CheckoutInformation("FirstName", "LastName", "0001"));
        checkoutAction.clickContinueButtonOnCheckoutStepOne();
        checkoutAction.clickCancelButtonOnCheckoutStepTwo();
        commonAction.verifyTheActualUrlMatchesTheExpected(urlConfiguration.getSaucedemo().getInventory());
    }

    @Test
    public void verifyUserCanSuccessfullyPlaceOrderTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.clickCheckoutButton();
        checkoutAction.fillInCheckoutInformationForm(new CheckoutInformation("FirstName", "LastName", "0001"));
        checkoutAction.clickContinueButtonOnCheckoutStepOne();
        checkoutAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
        checkoutAction.verifyExpectedOrderSummaryIsDisplayed(getExpectedOrderSummary());
        checkoutAction.clickFinishButtonOnCheckoutStepTwo();
        checkoutAction.verifyCheckoutProcessIsCompleted();
    }

    private void addProductsToCartAndGoToCartPage() {
        productListAction.addProductFromListToCart(SAUCE_LABS_BACKPACK_PRODUCT);
        productListAction.addProductFromListToCart(SAUCE_LABS_BIKE_LIGHT_PRODUCT);
        productListAction.clickOnCartBadge();
    }

    @NonNull
    private List<CartProductItem> getExpectedProductItems() {
        return new ArrayList<>() {{
            add(new CartProductItem("Sauce Labs Backpack",
                    "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                    "$29.99",
                    1));
            add(new CartProductItem("Sauce Labs Bike Light",
                    "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    "$9.99",
                    1));
        }};
    }

    @NonNull
    private OrderSummary getExpectedOrderSummary() {
        final List<CartProductItem> expectedProductItems = getExpectedProductItems();
        double price = expectedProductItems.stream()
                .mapToDouble(cartProductItem -> Double.parseDouble(cartProductItem.price().replaceAll("[^\\d\\.]", "")))
                .sum();
        double tax  = price * 0.08;
        double totalPrice = price + tax;
        final String priceSubtotalValue = String.format("Item total: $%.2f", price);
        final String taxValue = String.format("Tax: $%.2f", tax);
        final String totalPriceValue = String.format("Total: $%.2f", totalPrice);
        return new OrderSummary(PAYMENT_INFORMATION, SHIPPING_INFORMATION, priceSubtotalValue, taxValue,
                totalPriceValue);
    }
}
