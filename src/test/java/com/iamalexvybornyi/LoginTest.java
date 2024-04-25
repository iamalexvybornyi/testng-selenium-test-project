package com.iamalexvybornyi;

import com.iamalexvybornyi.action.LoginAction;
import com.iamalexvybornyi.action.ProductListAction;
import com.iamalexvybornyi.driver.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chromium.HasCdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoginTest extends BaseTest {

    @Autowired
    private LoginAction loginAction;
    @Autowired
    private ProductListAction productListAction;

    @Test
    public void loginWithValidUserTest() {
        Map<String, Object> cookie = new HashMap<>();
        cookie.put("name", "cheese");
        cookie.put("value", "gouda");
        cookie.put("domain", "www.saucedemo.com");
        cookie.put("secure", true);

        ((HasCdp) DriverProvider.getDriver().getOriginalWebDriver()).executeCdpCommand("Network.setCookie", cookie);
        loginAction.enterUsername("standard_user");
        loginAction.enterPassword("secret_sauce");
        loginAction.clickLoginButton();
        productListAction.verifyProductListIsDisplayed();
    }

    @Test(dataProvider = "provideUserData")
    public void loginWithInvalidUserTest(String username, String password, String expectedErrorMessage) {
        loginAction.enterUsername(username);
        loginAction.enterPassword(password);
        loginAction.clickLoginButton();
        loginAction.verifyLoginErrorMessage(expectedErrorMessage);
    }

    @DataProvider
    public Object[][] provideUserData() {
        return new Object[][] {
                {"standard_user", "invalid_secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
