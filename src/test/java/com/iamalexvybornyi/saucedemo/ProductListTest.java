package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.dataprovider.saucedemo.ProductListDataProvider;
import com.iamalexvybornyi.model.ProductItem;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class ProductListTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @Test(dataProviderClass = ProductListDataProvider.class, dataProvider = "provideValidUserWithProductsData")
    public void verifyTheExpectedItemsAreDisplayedTest(@NonNull String username, @NonNull String password,
                                                       @NonNull List<ProductItem> expectedProductItems) {
        loginToWebsite(username, password);
        productListAction.verifyExpectedProductItemsAreDisplayed(expectedProductItems);
    }

    @Test(dataProviderClass = ProductListDataProvider.class, dataProvider = "provideProductListData")
    public void verifyProductsListSortingTest(@NonNull String sortingOptionName,
                                              @NonNull List<ProductItem> expectedProductItems) {
        loginToWebsite(STANDARD_USER_NAME, STANDARD_USER_PASSWORD);
        productListAction.sortProductListByOption(sortingOptionName);
        productListAction.verifyExpectedProductItemsAreDisplayed(expectedProductItems);
    }

    private void loginToWebsite(@NonNull String username, @NonNull String password) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        commonAction.clickButtonOnPage("Login", "Login");
        productListAction.verifyProductListIsDisplayed();
    }

}
