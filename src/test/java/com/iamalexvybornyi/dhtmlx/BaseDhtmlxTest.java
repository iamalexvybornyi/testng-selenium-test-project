package com.iamalexvybornyi.dhtmlx;

import com.iamalexvybornyi.BaseTest;
import com.iamalexvybornyi.driver.DriverProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseDhtmlxTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverProvider.getDriver().get(urlConfiguration.getDhtmlx().getHome());
    }
}
