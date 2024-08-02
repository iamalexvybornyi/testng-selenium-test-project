package com.iamalexvybornyi.page.saucedemo.login;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Input;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginPage implements Page {
    @PageElement(locatorType = LocatorType.ID, locator = "user-name")
    private Input usernameInput;
    @PageElement(locatorType = LocatorType.ID, locator = "password")
    private Input passwordInput;
    @PageElement(locatorType = LocatorType.ID, locator = "login-button")
    private Button loginButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//h3[@data-test='error']")
    private Label loginErrorMessage;
}
