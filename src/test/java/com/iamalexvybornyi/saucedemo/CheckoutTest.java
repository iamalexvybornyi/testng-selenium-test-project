package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class CheckoutTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @BeforeMethod
    private void loginToWebsite() {
        loginAction.enterUsername("standard_user");
        loginAction.enterPassword("secret_sauce");
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyAllFieldsAreRequiredOnCheckoutStepOneTest() {

    }

    @Test
    public void verifyCancelButtonsReturnsUserToCartFromCheckoutStepOneTest() {

    }

    @Test
    public void verifyCancelButtonsReturnsUserToProductListFromCheckoutStepTwoTest() {

    }

    @Test
    public void verifyAllItemsAndValuesAreDisplayedProperlyOnCheckoutStepTwoTest() {
        /*
            1. List of products
            2. Payment Information
         */
    }

    @Test
    public void verifyUserCanSuccessfullyPlaceOrderTest() {

    }
}
