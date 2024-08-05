package com.iamalexvybornyi.dataprovider.saucedemo;

import com.iamalexvybornyi.model.ProductItem;
import lombok.NonNull;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductListDataProvider {

    @DataProvider
    public Object[][] provideValidUserWithProductsData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", getExpectedProductItems(new ProductItem.ProductItemTitleComparatorAsc())},
                {"problem_user", "secret_sauce", getExpectedProductItems(new ProductItem.ProductItemTitleComparatorAsc())},
                {"performance_glitch_user", "secret_sauce", getExpectedProductItems(new ProductItem.ProductItemTitleComparatorAsc())}
        };
    }

    @DataProvider
    public Object[][] provideProductListData() {
        return new Object[][]{
                {"Name (A to Z)", getExpectedProductItems(new ProductItem.ProductItemTitleComparatorAsc())},
                {"Name (Z to A)", getExpectedProductItems(new ProductItem.ProductItemTitleComparatorDesc())},
                {"Price (low to high)", getExpectedProductItems(new ProductItem.ProductItemPriceComparatorAsc())},
                {"Price (high to low)", getExpectedProductItems(new ProductItem.ProductItemPriceComparatorDesc())}
        };
    }

    private List<ProductItem> getExpectedProductItems(@NonNull Comparator<ProductItem> productItemComparator) {
        final List<ProductItem> productItems = new ArrayList<>() {{
            add(new ProductItem("Sauce Labs Backpack",
                    "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                    "$29.99",
                    "Sauce Labs Backpack",
                    "/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg"));
            add(new ProductItem("Sauce Labs Bike Light",
                    "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                    "$9.99",
                    "Sauce Labs Bike Light",
                    "/static/media/bike-light-1200x1500.37c843b0.jpg"));
            add(new ProductItem("Sauce Labs Bolt T-Shirt",
                    "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                    "$15.99",
                    "Sauce Labs Bolt T-Shirt",
                    "/static/media/bolt-shirt-1200x1500.c2599ac5.jpg"));
            add(new ProductItem("Sauce Labs Fleece Jacket",
                    "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                    "$49.99",
                    "Sauce Labs Fleece Jacket",
                    "/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg"));
            add(new ProductItem("Sauce Labs Onesie",
                    "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                    "$7.99",
                    "Sauce Labs Onesie",
                    "/static/media/red-onesie-1200x1500.2ec615b2.jpg"));
            add(new ProductItem("Test.allTheThings() T-Shirt (Red)",
                    "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
                    "$15.99",
                    "Test.allTheThings() T-Shirt (Red)",
                    "/static/media/red-tatt-1200x1500.30dadef4.jpg"));
        }};

        productItems.sort(productItemComparator);
        return productItems;
    }
}
