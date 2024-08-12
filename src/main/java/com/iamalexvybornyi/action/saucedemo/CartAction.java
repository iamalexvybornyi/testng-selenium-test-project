package com.iamalexvybornyi.action.saucedemo;

import com.iamalexvybornyi.model.CartProductItem;
import com.iamalexvybornyi.page.saucedemo.cart.CartPage;
import com.iamalexvybornyi.page.saucedemo.cart.element.CartItemElement;
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
public class CartAction {

    @NonNull
    private final CartPage cartPage;

    @Step("Verify the expected products are displayed in cart")
    public void verifyExpectedProductsAreDisplayed(@NonNull List<CartProductItem> expectedCartProductItems) {
        log.info("Getting product items from cart");
        final List<CartItemElement> cartItemsFromPage = cartPage.getCartItemCollection().getElements();
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

    @Step("Remove product from list by title")
    public void removeProductFromCartListByTitle(@NonNull String productTitle) {
        log.info("Getting product items from the cart");
        List<CartItemElement> cartItemsFromPage = cartPage.getCartItemCollection().getElements();

        log.info("Removing product '{}' from the cart", productTitle);
        for (CartItemElement cartItemElement : cartItemsFromPage) {
            if (cartItemElement.getProductItemElement().getTitle().getText().equals(productTitle)) {
                cartItemElement.getProductItemElement().getRemoveFromCartButton().click();
                break;
            }
        }

        log.info("Verifying product '{}' has been removed from the cart", productTitle);
        cartItemsFromPage = cartPage.getCartItemCollection().getElements();
        Assert.assertEquals(
                cartItemsFromPage.stream().filter(cartItemElement ->
                cartItemElement.getProductItemElement().getTitle().getText().equals(productTitle)).toList().size(), 0,
        "The expected product is still in the cart!");
    }

}
