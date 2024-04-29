package com.iamalexvybornyi.driver;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import com.iamalexvybornyi.core.exception.UnsupportedBrowserTypeException;
import com.iamalexvybornyi.driver.factory.ChromeDriverFactory;
import com.iamalexvybornyi.driver.factory.FirefoxDriverFactory;
import com.iamalexvybornyi.driver.factory.WebDriverFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class EnhancedWebDriverImpl implements EnhancedWebDriver {

    private final WebDriver driver;
    private final BrowserConfigurationProperties browserConfigurationProperties;
    private final Map<String, WebDriverFactory> webDriverFactories = Map.ofEntries(
            Map.entry("chrome", new ChromeDriverFactory()),
            Map.entry("firefox", new FirefoxDriverFactory())
    );

    public EnhancedWebDriverImpl(BrowserConfigurationProperties browserConfigurationProperties) {
        this.browserConfigurationProperties = browserConfigurationProperties;
        driver = createDriver();
        driver.manage().window().setSize(new Dimension(browserConfigurationProperties.getResolution().getWidth(),
                browserConfigurationProperties.getResolution().getHeight()));
    }

    private WebDriver createDriver() {
        final String browserName = browserConfigurationProperties.getName();
        log.info("Creating '{}' driver", browserName);
        WebDriverFactory webDriverFactory = Optional.ofNullable(webDriverFactories.get(browserName))
                .orElseThrow(() -> new UnsupportedBrowserTypeException(browserName));
        return webDriverFactory.createDriver(browserConfigurationProperties);
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    @NonNull
    public WebElement waitForElementToBeVisible(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, browserConfigurationProperties.getTimeout()
                .getExplicit());
        log.debug("Waiting for element with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    @Override
    @NonNull
    public WebElement waitForElementToBePresent(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, browserConfigurationProperties.getTimeout()
                .getExplicit());
        log.debug("Waiting for element with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Override
    @NonNull
    public List<WebElement> waitForElementsToBeVisible(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, browserConfigurationProperties.getTimeout()
                .getExplicit());
        log.debug("Waiting for all elements with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(by)));
    }

    @Override
    public boolean waitForElementToBeInvisible(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, browserConfigurationProperties.getTimeout()
                .getExplicit());
        log.debug("Waiting for element with locator '{}' to be invisible", by);
        return webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    @Override
    public WebDriver waitForFrameToBeAvailableAndSwitchToIt(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, browserConfigurationProperties.getTimeout()
                        .getExplicit());
        return webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(by)));
    }

    @Override
    @NonNull
    public TakesScreenshot getTakesScreenshot() {
        return (TakesScreenshot) driver;
    }

    @Override
    @NonNull
    public WebDriver getOriginalWebDriver() {
        return driver;
    }
}
