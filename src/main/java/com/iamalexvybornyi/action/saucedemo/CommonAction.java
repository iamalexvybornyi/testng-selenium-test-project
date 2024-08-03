package com.iamalexvybornyi.action.saucedemo;

import com.iamalexvybornyi.driver.DriverProvider;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommonAction {

    @Step("Verify the actual url matches the expected")
    public void verifyTheActualUrlMatchesTheExpected(@NonNull String expectedUrl) {
        final String actualCurrentUrl = DriverProvider.getDriver().getCurrentUrl();
        log.info("Verifying that the current URL '{}' matches the expected '{}'", actualCurrentUrl, expectedUrl);
        Assert.assertEquals(actualCurrentUrl, expectedUrl, "Current URL does not match the expected!");
    }
}
