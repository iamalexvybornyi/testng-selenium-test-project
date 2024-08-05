package com.iamalexvybornyi.saucedemo;

import com.iamalexvybornyi.BaseTest;
import com.iamalexvybornyi.driver.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseSauceDemoTest extends BaseTest {

    protected static final String STANDARD_USER_NAME = "standard_user";
    protected static final String STANDARD_USER_PASSWORD = "secret_sauce";

    @BeforeMethod
    public void setUp() {
        DriverProvider.getDriver().get(urlConfiguration.getSaucedemo().getHome());
    }
}
