package com.iamalexvybornyi.driver.factory;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface WebDriverFactory {

    @NonNull
    WebDriver createDriver(@NonNull BrowserConfigurationProperties browserConfigurationProperties);

    @NonNull
    default URL getSeleniumGridAddress(@NonNull BrowserConfigurationProperties browserConfigurationProperties) {
        URL seleniumGridAddress;
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
}
