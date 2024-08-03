package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.CartAction;
import com.iamalexvybornyi.action.saucedemo.CommonAction;
import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.model.CartProductItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CartTest extends BaseSauceDemoTest {

    private static final String SAUCE_LABS_BACKPACK_PRODUCT = "Sauce Labs Backpack";
    private static final String SAUCE_LABS_BIKE_LIGHT_PRODUCT = "Sauce Labs Bike Light";

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;
    @Autowired
    private CartAction cartAction;
    @Autowired
    private CommonAction commonAction;

    @BeforeMethod
    private void loginToWebsite() {
        loginAction.enterUsername("standard_user");
        loginAction.enterPassword("secret_sauce");
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test
    public void verifyAddedItemsAreDisplayedProperlyOnCartPageTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
    }

    @Test
    public void verifyAddedItemsCanBeRemovedFromCartPageTest() {
        addProductsToCartAndGoToCartPage();
        final List<CartProductItem> expectedProductItemsFromCart = getExpectedProductItems();
        cartAction.verifyExpectedProductsAreDisplayed(expectedProductItemsFromCart);
        removeProductFromCartAndVerifyTheRemainingItems(SAUCE_LABS_BACKPACK_PRODUCT, expectedProductItemsFromCart);
        removeProductFromCartAndVerifyTheRemainingItems(SAUCE_LABS_BIKE_LIGHT_PRODUCT, expectedProductItemsFromCart);
    }

    @Test
    public void verifyUserCanContinueShoppingFromCartPageTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
        cartAction.clickContinueShoppingButton();
        commonAction.verifyTheActualUrlMatchesTheExpected(urlConfiguration.getSaucedemo().getInventory());
        productListAction.verifyTheNumberOfProductsInCart(2);
    }

    @Test
    public void verifyProductsAreStillInCartAfterLogoutAndLoginTest() {
        addProductsToCartAndGoToCartPage();
        cartAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
        cartAction.clickContinueShoppingButton();
        productListAction.clickSidebarMenuButton();
        productListAction.clickLogoutLink();
        loginToWebsite();
        productListAction.verifyTheNumberOfProductsInCart(2);
        productListAction.clickOnCartBadge();
        cartAction.verifyExpectedProductsAreDisplayed(getExpectedProductItems());
    }

    private void addProductsToCartAndGoToCartPage() {
        productListAction.addProductFromListToCart(SAUCE_LABS_BACKPACK_PRODUCT);
        productListAction.addProductFromListToCart(SAUCE_LABS_BIKE_LIGHT_PRODUCT);
        productListAction.clickOnCartBadge();
    }

    private void removeProductFromCartAndVerifyTheRemainingItems(@NonNull String productTitleToRemove,
                                                                 @NonNull List<CartProductItem> expectedProductItemsFromCart) {
        cartAction.removeProductFromCartListByTitle(productTitleToRemove);
        expectedProductItemsFromCart.removeIf(cartProductItem ->
                cartProductItem.title().equals(productTitleToRemove));
        cartAction.verifyExpectedProductsAreDisplayed(expectedProductItemsFromCart);
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

}
