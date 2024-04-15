package com.iamalexvybornyi.action;

import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.model.ProductItem;
import com.iamalexvybornyi.page.element.ProductItemElement;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import com.iamalexvybornyi.page.ProductListPage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListAction {

    @NonNull
    private final ProductListPage productListPage;

    @Step("Verify product list is displayed")
    public void verifyProductListIsDisplayed() {
        log.info("Verifying that the product list is displayed on the page");
        Assert.assertTrue(productListPage.getProductListContainer().getRootWebElement().isDisplayed());
    }

    @Step("Verify expected product items are displayed")
    public void verifyExpectedProductItemsAreDisplayed(@NonNull List<ProductItem> expectedProductItems) {
        log.info("Getting product list from the page");
        final List<ProductItemElement> productItemsFromPage = productListPage.getProductItemList().getElements();
        final List<ProductItem> actualProductItems = new ArrayList<>();
        productItemsFromPage.forEach(productItem -> actualProductItems.add(
                new ProductItem(
                        productItem.getTitle().getText(),
                        productItem.getDescription().getText(),
                        productItem.getPrice().getText(),
                        productItem.getImage().getImageAlt()
                )));
        log.info("Verifying that the product list from the page {} is equal to {}", actualProductItems,
                expectedProductItems);
        Assert.assertEquals(actualProductItems, expectedProductItems,
                "Product Items from the page do not match the expected ones!");
    }

    @Step("Add product from list to cart")
    public void addProductFromListToCart(@NonNull String productTitle) {
        final ProductItemElement foundProductItem = findProductOnPage(productTitle);
        log.info("Adding the product with title '{}' to cart", productTitle);
        foundProductItem.getAddToCartButton().click();
    }

    @Step("Verify product is added to cart")
    public void verifyProductIsAddedToCart(@NonNull String productTitle) {
        final ProductItemElement productItem = findProductOnPage(productTitle);
        log.info("Verifying the product with title '{}' is added to cart", productTitle);
        Assert.assertTrue(
                productItem.getRemoveFromCartButton().getRootWebElement().isDisplayed(),
                "Product is not in the cart!"
        );
    }

    @Step("Verify the number of products in cart")
    public void verifyTheNumberOfProductsInCart(int expectedNumberOfProducts) {
        log.info("Getting information from the shopping cart badge");
        final Label shoppingCartBadge = productListPage.getCommonHeaderElement().getShoppingCartBadge();
        log.info("Verifying that the number of products in the cart is equal to '{}'", expectedNumberOfProducts);
        Assert.assertEquals(Integer.parseInt(shoppingCartBadge.getText()), expectedNumberOfProducts,
                "Number of products in the cart does not match the expected one!");
    }

    @Step("Add product from list to cart")
    public void addProductFromListToCart(int productIndex) {
        final ProductItemElement foundProductItem = findProductOnPage(productIndex);
        log.info("Adding the product with index '{}' to cart", productIndex);
        foundProductItem.getAddToCartButton().click();
    }

    @NonNull
    private ProductItemElement findProductOnPage(@NonNull String productTitle) {
        log.info("Searching for the product with title '{}' on the page", productTitle);
        final List<ProductItemElement> productItemsFromPage = productListPage.getProductItemList().getElements();
        return productItemsFromPage.stream().filter(productItem ->
                        productItem.getTitle().getText().equals(productTitle))
                .toList().stream().findFirst().orElseThrow();
    }

    @NonNull
    private ProductItemElement findProductOnPage(int productIndex) {
        log.info("Searching for the product with index '{}' on the page", productIndex);
        final List<ProductItemElement> productItemsFromPage = productListPage.getProductItemList().getElements();
        return productItemsFromPage.get(productIndex);
    }
}
