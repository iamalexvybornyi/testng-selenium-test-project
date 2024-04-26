package com.iamalexvybornyi.page.dhtmlx.element.table;

import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableRowElement extends Div {

    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@data-dhx-col-id='title']", searchWithinParent = true)
    private Label title;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@data-dhx-col-id='authors']", searchWithinParent = true)
    private Label author;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@data-dhx-col-id='average_rating']", searchWithinParent = true)
    private Label rating;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//div[@data-dhx-col-id='publication_date']", searchWithinParent = true)
    private Label publicationDate;

    public TableRowElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
