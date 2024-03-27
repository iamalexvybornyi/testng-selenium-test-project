package com.iamalexvybornyi.action;

import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.page.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginAction {

    private final LoginPage loginPage;

    @Step("Enter username")
    public void enterUsername(@NonNull String usernameToEnter) {
        log.info("Entering username '{}' into the username input", usernameToEnter);
        DriverProvider.getDriver().waitForElementToBeVisible(loginPage.getUsernameInput()).sendKeys(usernameToEnter);
    }

    @Step("Enter password")
    public void enterPassword(@NonNull String passwordToEnter) {
        log.info("Entering password into the password input");
        DriverProvider.getDriver().waitForElementToBeVisible(loginPage.getPasswordInput()).sendKeys(passwordToEnter);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        log.info("Clicking login button");
        DriverProvider.getDriver().waitForElementToBeVisible(loginPage.getLoginButton()).click();
    }

    @Step("Verify login error message")
    public void verifyLoginErrorMessage(@NonNull String expectedErrorMessage) {
        log.info("Getting the login error message");
        WebElement errorMessage = DriverProvider.getDriver().waitForElementToBeVisible(loginPage.getLoginErrorMessage());
        log.info("Verifying the login error message '{}' is equal to '{}'", errorMessage, expectedErrorMessage);
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage,
                "Login error message does not match expected!");
    }
}
