package com.iamalexvybornyi.driver;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface EnhancedWebDriver extends WebDriver {

    @NonNull
    WebElement waitForElementToBeVisible(@NonNull By by);
    @NonNull
    List<WebElement> waitForElementsToBeVisible(@NonNull By by);
    boolean waitForElementToBeInvisible(@NonNull By by);
    @NonNull
    TakesScreenshot getTakesScreenshot();
    @NonNull
    WebDriver getOriginalWebDriver();
}
