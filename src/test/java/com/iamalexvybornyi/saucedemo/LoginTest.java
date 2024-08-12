package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import com.iamalexvybornyi.dataprovider.saucedemo.UserDataProvider;
import com.iamalexvybornyi.util.buttons.LoginPageButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

@Slf4j
public class LoginTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "provideValidUserData")
    public void loginWithValidUserTest(String username, String password) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        commonAction.clickButtonOnPage(LoginPageButtonName.LOGIN, PageName.LOGIN);
        productListAction.verifyProductListIsDisplayed();
    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "provideInvalidUserData")
    public void loginWithInvalidUserTest(String username, String password, String expectedErrorMessage) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        commonAction.clickButtonOnPage(LoginPageButtonName.LOGIN, PageName.LOGIN);
        loginAction.verifyLoginErrorMessage(expectedErrorMessage);
    }
}
