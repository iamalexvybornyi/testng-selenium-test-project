package com.iamalexvybornyi.dataprovider.saucedemo;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider
    public Object[][] provideValidUserData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider
    public Object[][] provideInvalidUserData() {
        return new Object[][] {
                {"standard_user", "invalid_secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

}
