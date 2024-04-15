package com.iamalexvybornyi.driver;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class EnhancedWebDriverImpl implements EnhancedWebDriver {

    private final WebDriver driver;

    public EnhancedWebDriverImpl(BrowserConfigurationProperties browserConfigurationProperties) {
        driver = createDriver(browserConfigurationProperties);
        driver.manage().window().setSize(new Dimension(browserConfigurationProperties.getResolution().getWidth(),
                browserConfigurationProperties.getResolution().getHeight()));
    }

    private WebDriver createDriver(BrowserConfigurationProperties browserConfigurationProperties) {
        log.info("Creating '{}' driver", browserConfigurationProperties.getName());
        switch (browserConfigurationProperties.getName()) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                if (browserConfigurationProperties.getHeadless()) {
                    chromeOptions.addArguments("--headless");
                }
                if (browserConfigurationProperties.getRemote()) {
                    log.info("Using the remote server '{}' for creating a driver",
                            browserConfigurationProperties.getRemoteServerAddress());
                    return new RemoteWebDriver(getSeleniumGridAddress(browserConfigurationProperties), chromeOptions);
                } else {
                    return new ChromeDriver(chromeOptions);
                }
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (browserConfigurationProperties.getHeadless()) {
                    firefoxOptions.addArguments("--headless");
                }
                if (browserConfigurationProperties.getRemote()) {
                    log.info("Using the remote server '{}' for creating a driver",
                            browserConfigurationProperties.getRemoteServerAddress());
                    return new RemoteWebDriver(getSeleniumGridAddress(browserConfigurationProperties), firefoxOptions);
                } else {
                    return new FirefoxDriver(firefoxOptions);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + browserConfigurationProperties.getName());
        }
    }

    @NonNull
    private URL getSeleniumGridAddress(@NonNull BrowserConfigurationProperties browserConfigurationProperties) {
        URL seleniumGridAddress = null;
        try {
            if (browserConfigurationProperties.getRemoteServerAddress() != null) {
                seleniumGridAddress = new URL(browserConfigurationProperties.getRemoteServerAddress());
            } else {
                throw new RuntimeException("Remote server address can't be null!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return seleniumGridAddress;
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
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        log.debug("Waiting for element with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    @Override
    @NonNull
    public List<WebElement> waitForElementsToBeVisible(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        log.debug("Waiting for all elements with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(by)));
    }

    @Override
    public boolean waitForElementToBeInvisible(@NonNull By by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        log.debug("Waiting for element with locator '{}' to be visible", by);
        return webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    @Override
    @NonNull
    public TakesScreenshot getTakesScreenshot() {
        return (TakesScreenshot)driver;
    }

    @Override
    @NonNull
    public WebDriver getOriginalWebDriver() {
        return driver;
    }
}
