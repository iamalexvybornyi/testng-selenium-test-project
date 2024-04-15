package com.iamalexvybornyi.page;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.Input;
import com.iamalexvybornyi.core.element.Label;
import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginPage {
    private final Input usernameInput = new Input(By.id("user-name"));
    private final Input passwordInput = new Input(By.id("password"));
    private final Button loginButton = new Button(By.id("login-button"));
    private final Label loginErrorMessage = new Label(By.xpath("//h3[@data-test='error']"));
}
