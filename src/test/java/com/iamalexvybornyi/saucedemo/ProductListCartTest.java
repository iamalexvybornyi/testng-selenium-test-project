package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class ProductListCartTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @BeforeMethod
    private void loginToWebsite() {
        loginAction.enterUsername(STANDARD_USER_NAME);
        loginAction.enterPassword(STANDARD_USER_PASSWORD);
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyProductItemsCanBeAddedToCartFromProductListTest() {
        final String productName = "Sauce Labs Backpack";
        productListAction.addProductFromListToCart(productName);
        productListAction.verifyProductIsAddedToCart(productName);
    }

    @Test
    public void verifyProductsAreAddedIntoCartTest() {
        productListAction.addProductFromListToCart("Sauce Labs Backpack");
        productListAction.addProductFromListToCart("Sauce Labs Bike Light");
        productListAction.verifyTheNumberOfProductsInCart(2);
    }

}
