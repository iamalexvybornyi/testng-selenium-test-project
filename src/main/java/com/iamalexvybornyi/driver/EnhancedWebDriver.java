package com.iamalexvybornyi.driver;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface EnhancedWebDriver extends WebDriver {

    WebElement waitForElementToBeVisible(@NonNull By by);
    List<WebElement> waitForElementsToBeVisible(@NonNull By by);
    TakesScreenshot getTakesScreenshot();
}
