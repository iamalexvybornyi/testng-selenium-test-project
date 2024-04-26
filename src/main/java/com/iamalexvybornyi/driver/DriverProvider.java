package com.iamalexvybornyi.driver;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverProvider {

    private DriverProvider() {}

    private static final ThreadLocal<EnhancedWebDriver> driver = new ThreadLocal<>();

    public static EnhancedWebDriver getDriver() {
        if (driver.get() == null) {
            throw new RuntimeException("Driver cannot be obtained before the initialization!");
        }
        return driver.get();
    }

    public static void setDriver(@NonNull EnhancedWebDriver webDriver) {
        log.info("Setting driver for the current thread");
        driver.set(webDriver);
    }
}
