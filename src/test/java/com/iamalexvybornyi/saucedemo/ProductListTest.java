package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.model.ProductItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class ProductListTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @Test(dataProvider = "provideUserData")
    public void verifyTheExpectedItemsAreDisplayedTest(@NonNull String username, @NonNull String password) {
        loginToWebsite(username, password);
        final List<ProductItem> expectedProductItems = getExpectedProductItems(new ProductItem.ProductItemTitleComparatorAsc());
        productListAction.verifyExpectedProductItemsAreDisplayed(expectedProductItems);
    }

    @Test(dataProvider = "provideSortingData")
    public void verifyProductsListSortingTest(@NonNull String sortingOptionName,
                                              @NonNull Comparator<ProductItem> productItemComparator) {
        loginToWebsite("standard_user", "secret_sauce");
        productListAction.sortProductListByOption(sortingOptionName);
        final List<ProductItem> expectedProductItems = getExpectedProductItems(productItemComparator);
        productListAction.verifyExpectedProductItemsAreDisplayed(expectedProductItems);
    }

    private void loginToWebsite(@NonNull String username, @NonNull String password) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @DataProvider
    private Object[][] provideUserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider
    private Object[][] provideSortingData() {
        return new Object[][] {
                {"Name (A to Z)", new ProductItem.ProductItemTitleComparatorAsc()},
                {"Name (Z to A)", new ProductItem.ProductItemTitleComparatorDesc()},
                {"Price (low to high)", new ProductItem.ProductItemPriceComparatorAsc()},
                {"Price (high to low)", new ProductItem.ProductItemPriceComparatorDesc()}
        };
    }

    private List<ProductItem> getExpectedProductItems(@NonNull Comparator<ProductItem> productItemComparator) {
        final String sauceDemoBaseUrl = urlConfiguration.getSaucedemo().getHome();
        final List<ProductItem> productItems =  new ArrayList<>() {{
            add(new ProductItem("Sauce Labs Backpack",
                    "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                    "$29.99",
                    "Sauce Labs Backpack",
                    sauceDemoBaseUrl + "/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg"));
            add(new ProductItem("Sauce Labs Bike Light",
                    "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    "$9.99",
                    "Sauce Labs Bike Light",
                    sauceDemoBaseUrl + "/static/media/bike-light-1200x1500.37c843b0.jpg"));
            add(new ProductItem("Sauce Labs Bolt T-Shirt",
                    "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                    "$15.99",
                    "Sauce Labs Bolt T-Shirt",
                    sauceDemoBaseUrl + "/static/media/bolt-shirt-1200x1500.c2599ac5.jpg"));
            add(new ProductItem("Sauce Labs Fleece Jacket",
                    "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                    "$49.99",
                    "Sauce Labs Fleece Jacket",
                    sauceDemoBaseUrl + "/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg"));
            add(new ProductItem("Sauce Labs Onesie",
                    "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                    "$7.99",
                    "Sauce Labs Onesie",
                    sauceDemoBaseUrl + "/static/media/red-onesie-1200x1500.2ec615b2.jpg"));
            add(new ProductItem("Test.allTheThings() T-Shirt (Red)",
                    "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                    "$15.99",
                    "Test.allTheThings() T-Shirt (Red)",
                    sauceDemoBaseUrl + "/static/media/red-tatt-1200x1500.30dadef4.jpg"));
        }};

        productItems.sort(productItemComparator);
        return productItems;
    }
}
