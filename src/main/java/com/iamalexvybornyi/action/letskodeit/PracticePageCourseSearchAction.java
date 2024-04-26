package com.iamalexvybornyi.action.letskodeit;

import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.page.letskodeit.PracticePage;
import com.iamalexvybornyi.page.letskodeit.element.CourseItem;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PracticePageCourseSearchAction {

    @NonNull
    private final PracticePage practicePage;

    @Step("Scroll to course")
    public void scrollToTheBottomOfTheCourseList() {
        log.info("Switching to courses frame");
        practicePage.getCoursesFrame().switchToFrame();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(DriverProvider.getDriver().getOriginalWebDriver())
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(500, ChronoUnit.MILLIS))
                .ignoring(TimeoutException.class);
        fluentWait.until((driver) -> {
            final Actions frameActions = new Actions(driver);
            log.info("Scrolling to load more button");
            frameActions.scrollToElement(practicePage.getLoadMoreButton().getRootWebElement());
            frameActions.perform();
            return ExpectedConditions.not(
                    ExpectedConditions.presenceOfElementLocated(practicePage.getLoadMoreButton().getLocator()));
        });
        practicePage.getCoursesFrame().quitFrame();
    }

    @NonNull
    @Step("Get course price")
    public String getCoursePrice(@NonNull String linkToSearchFor) {
        log.info("Switching to courses frame");
        practicePage.getCoursesFrame().switchToFrame();
        log.info("Getting courses list");
        List<CourseItem> courseItems = practicePage.getCoursesList().getElements();
        log.info("Searching for the course with link '{}'", linkToSearchFor);
        CourseItem foundCourseItem = courseItems.stream().filter(courseItem ->
                        courseItem.getLink().getLinkAttribute().contains(linkToSearchFor))
                .findFirst().orElseThrow();
        final String priceOfTheCourse = foundCourseItem.getPrice().getText();
        log.info("The price of the course is {}", priceOfTheCourse);
        practicePage.getCoursesFrame().quitFrame();
        return priceOfTheCourse;
    }

    @Step("Verify the expected course price")
    public void verifyTheExpectedCoursePrice(@NonNull String linkToSearchFor, @NonNull String expectedPrice) {
        String actualCoursePrice = getCoursePrice(linkToSearchFor);
        log.info("Comparing the actual price of the course '{}' with expected '{}'", actualCoursePrice, expectedPrice);
        Assertions.assertEquals(expectedPrice, actualCoursePrice);
    }
}
