package com.iamalexvybornyi.driver.factory;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class ChromeDriverFactory implements WebDriverFactory {
    @Override
    @NonNull
    public WebDriver createDriver(@NonNull BrowserConfigurationProperties browserConfigurationProperties) {
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
}
