package com.iamalexvybornyi.action.saucedemo;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.page.PageWithButtons;
import com.iamalexvybornyi.driver.DriverProvider;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommonAction {

    @NonNull
    private final List<PageWithButtons> pageWithButtonsList;

    @Step("Verify the actual url matches the expected")
    public void verifyTheActualUrlMatchesTheExpected(@NonNull String expectedUrl) {
        final String actualCurrentUrl = DriverProvider.getDriver().getCurrentUrl();
        log.info("Verifying that the current URL '{}' matches the expected '{}'", actualCurrentUrl, expectedUrl);
        Assert.assertEquals(actualCurrentUrl, expectedUrl, "Current URL does not match the expected!");
    }

    @Step("Click '{buttonName}' button on '{pageName}' page")
    public void clickButtonOnPage(@NonNull String buttonName, @NonNull String pageName) {
        final PageWithButtons foundPageWithButtons = pageWithButtonsList.stream()
                .filter(pageWithButtons -> pageWithButtons.getPageName().equals(pageName))
                .findFirst().orElseThrow();
        final Map.Entry<String, Button> foundButtonEntry = foundPageWithButtons.getButtons().entrySet().stream()
                .filter(buttonEntry -> buttonEntry.getKey().equals(buttonName))
                .findFirst().orElseThrow();
        log.info("Clicking '{}' button on '{}' page", buttonName, pageName);
        foundButtonEntry.getValue().click();
    }
}
