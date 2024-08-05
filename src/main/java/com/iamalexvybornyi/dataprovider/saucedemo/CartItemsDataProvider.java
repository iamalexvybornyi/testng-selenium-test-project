package com.iamalexvybornyi.dataprovider.saucedemo;

import com.iamalexvybornyi.model.CartProductItem;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

public class CartItemsDataProvider {

    @DataProvider
    public Object[][] provideCartItemsData() {
        return new Object[][]{
                {new ArrayList<>() {{
                    add(new CartProductItem("Sauce Labs Backpack",
                            "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                            "$29.99",
                            1));
                    add(new CartProductItem("Sauce Labs Bike Light",
                            "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                            "$9.99",
                            1));
                }}}
        };
    }
}
