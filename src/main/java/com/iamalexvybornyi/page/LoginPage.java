package com.iamalexvybornyi.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginPage {
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginErrorMessage = By.xpath("//h3[@data-test='error']");
}
