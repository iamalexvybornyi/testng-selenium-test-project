package com.iamalexvybornyi.dataprovider.saucedemo;

import com.iamalexvybornyi.model.ProductItem;
import org.testng.annotations.DataProvider;

public class ProductListDataProvider {

    @DataProvider
    private Object[][] provideProductListSortingData() {
        return new Object[][] {
                {"Name (A to Z)", new ProductItem.ProductItemTitleComparatorAsc()},
                {"Name (Z to A)", new ProductItem.ProductItemTitleComparatorDesc()},
                {"Price (low to high)", new ProductItem.ProductItemPriceComparatorAsc()},
                {"Price (high to low)", new ProductItem.ProductItemPriceComparatorDesc()}
        };
    }
}
