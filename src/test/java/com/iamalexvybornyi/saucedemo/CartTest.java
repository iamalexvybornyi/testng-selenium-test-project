package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.CartAction;
import com.iamalexvybornyi.action.saucedemo.CommonAction;
import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.dataprovider.saucedemo.CartItemsDataProvider;
import com.iamalexvybornyi.model.CartProductItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class CartTest extends BaseSauceDemoTest {

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
        loginAction.enterUsername(STANDARD_USER_NAME);
        loginAction.enterPassword(STANDARD_USER_PASSWORD);
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test(dataProviderClass = CartItemsDataProvider.class, dataProvider = "provideCartItemsData")
    public void verifyAddedItemsAreDisplayedProperlyOnCartPageTest(@NonNull List<CartProductItem> cartProductItems) {
        addProductsToCartAndGoToCartPage(cartProductItems);
        cartAction.verifyExpectedProductsAreDisplayed(cartProductItems);
    }

    @Test(dataProviderClass = CartItemsDataProvider.class, dataProvider = "provideCartItemsData")
    public void verifyAddedItemsCanBeRemovedFromCartPageTest(@NonNull List<CartProductItem> cartProductItems) {
        addProductsToCartAndGoToCartPage(cartProductItems);
        cartAction.verifyExpectedProductsAreDisplayed(cartProductItems);

        final Iterator<CartProductItem> cartProductItemIterator = cartProductItems.iterator();
        while (cartProductItemIterator.hasNext()) {
            final CartProductItem currentCartProductItem = cartProductItemIterator.next();
            cartProductItemIterator.remove();
            removeProductFromCartAndVerifyTheRemainingItems(currentCartProductItem.title(),
                    cartProductItems);
        }
    }

    @Test(dataProviderClass = CartItemsDataProvider.class, dataProvider = "provideCartItemsData")
    public void verifyUserCanContinueShoppingFromCartPageTest(@NonNull List<CartProductItem> cartProductItems) {
        addProductsToCartAndGoToCartPage(cartProductItems);
        cartAction.verifyExpectedProductsAreDisplayed(cartProductItems);
        cartAction.clickContinueShoppingButton();
        commonAction.verifyTheActualUrlMatchesTheExpected(urlConfiguration.getSaucedemo().getInventory());
        productListAction.verifyTheNumberOfProductsInCart(2);
    }

    @Test(dataProviderClass = CartItemsDataProvider.class, dataProvider = "provideCartItemsData")
    public void verifyProductsAreStillInCartAfterLogoutAndLoginTest(@NonNull List<CartProductItem> cartProductItems) {
        addProductsToCartAndGoToCartPage(cartProductItems);
        cartAction.verifyExpectedProductsAreDisplayed(cartProductItems);
        cartAction.clickContinueShoppingButton();
        productListAction.clickSidebarMenuButton();
        productListAction.clickLogoutLink();
        loginToWebsite();
        productListAction.verifyTheNumberOfProductsInCart(2);
        productListAction.clickOnCartBadge();
        cartAction.verifyExpectedProductsAreDisplayed(cartProductItems);
    }

    private void addProductsToCartAndGoToCartPage(@NonNull List<CartProductItem> cartProductItems) {
        cartProductItems.forEach(cartProductItem ->
                productListAction.addProductFromListToCart(cartProductItem.title()));
        productListAction.clickOnCartBadge();
    }

    private void removeProductFromCartAndVerifyTheRemainingItems(@NonNull String productTitleToRemove,
                                                                 @NonNull List<CartProductItem> expectedProductItemsFromCart) {
        cartAction.removeProductFromCartListByTitle(productTitleToRemove);
        expectedProductItemsFromCart.removeIf(cartProductItem ->
                cartProductItem.title().equals(productTitleToRemove));
        cartAction.verifyExpectedProductsAreDisplayed(expectedProductItemsFromCart);
    }

}
