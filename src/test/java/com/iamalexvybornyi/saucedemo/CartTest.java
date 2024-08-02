package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.CartAction;
import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.model.CartProductItem;
import com.iamalexvybornyi.model.ProductItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class CartTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;
    @Autowired
    private CartAction cartAction;

    @BeforeMethod
    private void loginToWebsite() {
        loginAction.enterUsername("standard_user");
        loginAction.enterPassword("secret_sauce");
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyAddedItemsAreDisplayedProperlyOnCartPageTest() {
        productListAction.addProductFromListToCart("Sauce Labs Backpack");
        productListAction.addProductFromListToCart("Sauce Labs Bike Light");
        productListAction.clickOnCartBadge();
        cartAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
    }

    @Test
    public void verifyAddedItemsCanBeRemovedFromCartPageTest() {
        /*
            1. Add products to cart
            2. Go to cart page
            3. Check that the list of products contains the added products in the same order they were added
            4. Remove one product, verify the other ones are still in cart
            5. Remove all remaining products, verify that cart is empty
         */
    }

    @Test
    public void verifyUserCanContinueShoppingFromCartPageTest() {
        /*
            1. Add products to cart
            2. Go to cart page
            3. Check that the list of products contains the added products in the same order they were added
            4. Click Continue Shopping button, verify that user is returned to the product list page and the number
            of products in the cart is still the same
         */
    }

    @Test
    public void verifyProductsAreStillInCartAfterLogoutAndLoginTest() {

    }

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

}
