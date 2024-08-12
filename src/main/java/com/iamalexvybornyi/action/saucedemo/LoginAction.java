package com.iamalexvybornyi.action.saucedemo;

import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.iamalexvybornyi.page.saucedemo.login.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginAction {

    @NonNull
    private final LoginPage loginPage;

    @Step("Enter username")
    public void enterUsername(@NonNull String usernameToEnter) {
        log.info("Entering username '{}' into the username input", usernameToEnter);
        loginPage.getUsernameInput().enterText(usernameToEnter);
    }

    @Step("Enter password")
    public void enterPassword(@NonNull String passwordToEnter) {
        log.info("Entering password into the password input");
        loginPage.getPasswordInput().enterText(passwordToEnter);
    }

    @Step("Verify login error message")
    public void verifyLoginErrorMessage(@NonNull String expectedErrorMessage) {
        log.info("Getting the login error message");
        String errorMessage = loginPage.getLoginErrorMessage().getText();
        log.info("Verifying the login error message '{}' is equal to '{}'", errorMessage, expectedErrorMessage);
        Assert.assertEquals(errorMessage, expectedErrorMessage,
                "Login error message does not match expected!");
    }
}
