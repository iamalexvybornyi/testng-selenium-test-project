package com.iamalexvybornyi.page.dhtmlx.element.table;

import com.iamalexvybornyi.core.element.Div;
import com.iamalexvybornyi.core.element.Label;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableRowElement extends Div {

    private final Label title = new Label(By.xpath(".//div[@data-dhx-col-id='title']"), this);
    private final Label author = new Label(By.xpath(".//div[@data-dhx-col-id='authors']"), this);
    private final Label rating = new Label(By.xpath(".//div[@data-dhx-col-id='average_rating']"), this);
    private final Label publicationDate = new Label(By.xpath(".//div[@data-dhx-col-id='publication_date']"), this);

    public TableRowElement(@NonNull By locator) {
        super(locator);
    }

    public TableRowElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
