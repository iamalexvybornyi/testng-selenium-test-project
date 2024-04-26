package com.iamalexvybornyi;

import com.iamalexvybornyi.config.BrowserConfigurationProperties;
import com.iamalexvybornyi.config.CommonConfiguration;
import com.iamalexvybornyi.config.UrlConfiguration;
import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.driver.EnhancedWebDriverImpl;
import com.iamalexvybornyi.util.GlobalKeys;
import io.qameta.allure.Allure;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {CommonConfiguration.class})
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected UrlConfiguration urlConfiguration;
    @Autowired
    protected BrowserConfigurationProperties browserConfigurationProperties;
    
    @BeforeMethod
    public void setUp(Method method) {
        MDC.put(GlobalKeys.TEST_ID.getKey(), String.format("%s-%s", method.getName(), Instant.now().toEpochMilli()));
        DriverProvider.setDriver(new EnhancedWebDriverImpl(browserConfigurationProperties));
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        final String methodName = result.getMethod().getMethodName();
        attachLogsToAllureReport();
        if (result.getStatus() == ITestResult.FAILURE) {
            attachScreenshotToAllureReport(methodName);
        }

        if (DriverProvider.getDriver() != null) {
            DriverProvider.getDriver().quit();
        }
    }

    private void attachLogsToAllureReport() {
        final File logFile = new File(String.format("./target/logs/%s.log", MDC.get(GlobalKeys.TEST_ID.getKey())));
        byte[] logFileAsBytes;
        try {
            logFileAsBytes = FileUtils.readFileToByteArray(logFile);
            Allure.addAttachment(MDC.get(GlobalKeys.TEST_ID.getKey()) + ".log",
                    new ByteArrayInputStream(logFileAsBytes));
        } catch (IOException e) {
            log.error("Couldn't attach log file to the allure report");
            throw new RuntimeException(e);
        }
    }

    private void attachScreenshotToAllureReport(@NonNull String methodName) {
        final String pathToFile;
        final String screenshotName = String.format("%s-%s", methodName, Instant.now().toEpochMilli());
        File screenshot = DriverProvider.getDriver().getTakesScreenshot().getScreenshotAs(OutputType.FILE);
        try {
            pathToFile = String.format("./target/screenshots/%s.png", screenshotName);
            FileUtils.copyFile(screenshot, new File(pathToFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Allure.addAttachment(screenshotName + ".png", Files.newInputStream(Paths.get(pathToFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
