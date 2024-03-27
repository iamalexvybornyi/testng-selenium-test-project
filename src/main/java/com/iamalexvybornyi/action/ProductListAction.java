package com.iamalexvybornyi.action;

import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.model.ProductItem;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import com.iamalexvybornyi.page.ProductListPage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListAction {
    private final ProductListPage productListPage;

    @Step("Verify product list is displayed")
    public void verifyProductListIsDisplayed() {
        log.info("Verifying that the product list is displayed on the page");
        Assert.assertTrue(DriverProvider.getDriver().waitForElementToBeVisible(productListPage.getProductListContainer())
                .isDisplayed());
    }

    @Step("Verify expected product items are displayed")
    public void verifyExpectedProductItemsAreDisplayed(@NonNull List<ProductItem> expectedProductItems) {
        log.info("Getting product list from the page");
        final List<WebElement> productItemsFromPage =
                DriverProvider.getDriver().waitForElementsToBeVisible(productListPage.getProductItems());
        final List<ProductItem> actualProductItems = new ArrayList<>();
        productItemsFromPage.forEach(productItem -> actualProductItems.add(
                new ProductItem(
                        productItem.findElement(productListPage.getProductItemElement().getTitle()).getText(),
                        productItem.findElement(productListPage.getProductItemElement().getDescription()).getText(),
                        productItem.findElement(productListPage.getProductItemElement().getPrice()).getText(),
                        productItem.findElement(productListPage.getProductItemElement().getImage()).getAttribute("alt")
                )));
        log.info("Verifying that the product list from the page {} is equal to {}", actualProductItems,
                expectedProductItems);
        Assert.assertEquals(actualProductItems, expectedProductItems,
                "Product Items from the page do not match the expected ones!");
    }

    @Step("Add product from list to cart")
    public void addProductFromListToCart(@NonNull String productTitle) {
        WebElement foundProductItem = findProductOnPage(productTitle);
        log.info("Adding the product with title '{}' to cart", productTitle);
        foundProductItem.findElement(productListPage.getProductItemElement().getAddToCartButton()).click();
    }

    @Step("Verify product is added to cart")
    public void verifyProductIsAddedToCart(@NonNull String productTitle) {
        WebElement productItem = findProductOnPage(productTitle);
        log.info("Verifying the product with title '{}' is added to cart", productTitle);
        Assert.assertTrue(
                productItem.findElement(productListPage.getProductItemElement()
                        .getRemoveFromCartButton()).isDisplayed(),
                "Product is not in the cart!"
        );
    }

    @Step("Verify the number of products in cart")
    public void verifyTheNumberOfProductsInCart(int expectedNumberOfProducts) {
        log.info("Getting information from the shopping cart badge");
        WebElement shoppingCartBadge =
                DriverProvider.getDriver()
                        .waitForElementToBeVisible(productListPage.getCommonHeaderElement().getShoppingCartBadge());
        log.info("Verifying that the number of products in the cart is equal to '{}'", expectedNumberOfProducts);
        Assert.assertEquals(Integer.parseInt(shoppingCartBadge.getText()), expectedNumberOfProducts,
                "Number of products in the cart does not match the expected one!");
    }

    @Step("Add product from list to cart")
    public void addProductFromListToCart(int productIndex) {
        WebElement foundProductItem = findProductOnPage(productIndex);
        log.info("Adding the product with index '{}' to cart", productIndex);
        foundProductItem.findElement(productListPage.getProductItemElement().getAddToCartButton()).click();
    }

    @NonNull
    private WebElement findProductOnPage(@NonNull String productTitle) {
        log.info("Searching for the product with title '{}' on the page", productTitle);
        final List<WebElement> productItemsFromPage =
                DriverProvider.getDriver().waitForElementsToBeVisible(productListPage.getProductItems());
        return productItemsFromPage.stream().filter(productItem ->
                        productItem.findElement(productListPage.getProductItemElement().getTitle())
                                .getText().equals(productTitle))
                .toList().stream().findFirst().orElseThrow();
    }

    @NonNull
    private WebElement findProductOnPage(int productIndex) {
        log.info("Searching for the product with index '{}' on the page", productIndex);
        final List<WebElement> productItemsFromPage =
                DriverProvider.getDriver().waitForElementsToBeVisible(productListPage.getProductItems());
        return productItemsFromPage.get(productIndex);
    }
}
