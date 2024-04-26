package com.iamalexvybornyi.action.dhtmlx;

import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.driver.DriverProvider;
import com.iamalexvybornyi.page.dhtmlx.DhtmlxPracticePage;
import com.iamalexvybornyi.page.dhtmlx.element.table.TableRowElement;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class DhtmlxDemoTableAction {

    @NonNull
    private final DhtmlxPracticePage dhtmlxPracticePage;

    @Step("Scroll to Dhtmlx Demo Table")
    public void scrollToDhtmlxDemoTable(@NonNull Duration pauseInBetweenScrolls) {
        final WebDriver driver = DriverProvider.getDriver().getOriginalWebDriver();

        log.info("Scrolling to Dhtmlx Demo Table");
        Actions actions = new Actions(driver);
        actions.scrollToElement(dhtmlxPracticePage.getJsDatatableHeader().getRootWebElement());
        actions.perform();
        sleep(pauseInBetweenScrolls);

        actions = new Actions(driver);
        actions.scrollToElement(dhtmlxPracticePage.getJsDatatableDescription().getRootWebElement());
        actions.perform();
        sleep(pauseInBetweenScrolls);
        log.info("Done scrolling to Dhtmlx Demo Table");
    }

    @NonNull
    @Step("Find the author of the provided book")
    public String findTheAuthorOfTheBook(@NonNull String bookName) {
        log.info("Switching to the Dhtmlx Demo Table frames");
        dhtmlxPracticePage.getTableDemoContainerElement().switchToFrame();
        dhtmlxPracticePage.getTableDemoContainerElement().getTableDemoBodyElement().switchToFrame();
        dhtmlxPracticePage.getTableDemoContainerElement().getTableDemoBodyElement().getTableDemoContentElement()
                .switchToFrame();
        log.info("Getting rows from the Dhtmlx Demo Table");
        WebElementCollection<TableRowElement> tableRows = dhtmlxPracticePage.getTableDemoContainerElement()
                .getTableDemoBodyElement().getTableDemoContentElement().getTableRows();
        TableRowElement foundBookRow = tableRows.getElements().stream()
                .filter(tableRowElement -> tableRowElement.getTitle().getText().equals(bookName))
                .findFirst().orElseThrow();
        String bookAuthor = foundBookRow.getAuthor().getText();
        log.info("The found author of '{}' is '{}'", bookName, bookAuthor);
        dhtmlxPracticePage.getTableDemoContainerElement().getTableDemoBodyElement().getTableDemoContentElement()
                .quitFrame();
        return bookAuthor;
    }

    @Step("Verify the author of the provided book")
    public void verifyTheAuthorOfTheBook(@NonNull String bookName, @NonNull String expectedBookAuthor) {
        String actualBookAuthor = findTheAuthorOfTheBook(bookName);
        log.info("Comparing the expected author '{}' with the actual '{}'", expectedBookAuthor, actualBookAuthor);
        Assertions.assertEquals(expectedBookAuthor, actualBookAuthor);
    }

    private void sleep(@NonNull Duration pause) {
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
