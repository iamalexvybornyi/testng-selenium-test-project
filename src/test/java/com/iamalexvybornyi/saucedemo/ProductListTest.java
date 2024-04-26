package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.model.ProductItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductListTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @BeforeMethod
    public void loginToWebsite() {
        loginAction.enterUsername("standard_user");
        loginAction.enterPassword("secret_sauce");
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyTheExpectedItemsAreDisplayedTest() {
        final List<ProductItem> expectedProductItems = getExpectedProductItems();
        productListAction.verifyExpectedProductItemsAreDisplayed(expectedProductItems);
    }

    @Test
    public void verifyProductItemsCanBeAddedToCartFromProductList() {
        final String productName = "Sauce Labs Backpack";
        productListAction.addProductFromListToCart(productName);
        productListAction.verifyProductIsAddedToCart(productName);
    }

    @Test
    public void verifyProductsAreAddedIntoCart() {
        productListAction.addProductFromListToCart("Sauce Labs Backpack");
        productListAction.addProductFromListToCart("Sauce Labs Bike Light");
        productListAction.verifyTheNumberOfProductsInCart(2);
    }

    private List<ProductItem> getExpectedProductItems() {
        return new ArrayList<>() {{
            add(new ProductItem("Sauce Labs Backpack",
                    "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                    "$29.99",
                    "Sauce Labs Backpack"));
            add(new ProductItem("Sauce Labs Bike Light",
                    "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    "$9.99",
                    "Sauce Labs Bike Light"));
            add(new ProductItem("Sauce Labs Bolt T-Shirt",
                    "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                    "$15.99",
                    "Sauce Labs Bolt T-Shirt"));
            add(new ProductItem("Sauce Labs Fleece Jacket",
                    "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                    "$49.99",
                    "Sauce Labs Fleece Jacket"));
            add(new ProductItem("Sauce Labs Onesie",
                    "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                    "$7.99",
                    "Sauce Labs Onesie"));
            add(new ProductItem("Test.allTheThings() T-Shirt (Red)",
                    "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                    "$15.99",
                    "Test.allTheThings() T-Shirt (Red)"));
        }};
    }
}
