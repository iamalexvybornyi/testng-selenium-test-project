package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.action.saucedemo.LoginAction;
import com.iamalexvybornyi.action.saucedemo.ProductListAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class LoginTest extends BaseSauceDemoTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @Test(dataProvider = "provideValidUserData")
    public void loginWithValidUserTest(String username, String password) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test(dataProvider = "provideInvalidUserData")
    public void loginWithInvalidUserTest(String username, String password, String expectedErrorMessage) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        loginAction.clickLoginButton();
        loginAction.verifyLoginErrorMessage(expectedErrorMessage);
    }

    @DataProvider
    private Object[][] provideInvalidUserData() {
        return new Object[][] {
                {"standard_user", "invalid_secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @DataProvider
    private Object[][] provideValidUserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }
}
