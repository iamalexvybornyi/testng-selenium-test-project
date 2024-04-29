package com.iamalexvybornyi.driver.factory;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class FirefoxDriverFactory implements WebDriverFactory {
    @Override
    @NonNull
    public WebDriver createDriver(@NonNull BrowserConfigurationProperties browserConfigurationProperties) {
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
}
