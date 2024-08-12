package com.iamalexvybornyi.page.saucedemo.login;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Input;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.util.buttons.ButtonName;
import com.iamalexvybornyi.util.buttons.LoginPageButtonName;
import com.iamalexvybornyi.util.buttons.PageName;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
public class LoginPage implements Page, PageWithButtons {
    @PageElement(locatorType = LocatorType.ID, locator = "user-name")
    private Input usernameInput;
    @PageElement(locatorType = LocatorType.ID, locator = "password")
    private Input passwordInput;
    @PageElement(locatorType = LocatorType.ID, locator = "login-button")
    private Button loginButton;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//h3[@data-test='error']")
    private Label loginErrorMessage;

    @Override
    @NonNull
    public String getPageName() {
        return PageName.LOGIN.getPageName();
    }

    @Override
    @NonNull
    public Map<ButtonName, Button> getButtons() {
        return Map.ofEntries(
                Map.entry(LoginPageButtonName.LOGIN, getLoginButton())
        );
    }
}
