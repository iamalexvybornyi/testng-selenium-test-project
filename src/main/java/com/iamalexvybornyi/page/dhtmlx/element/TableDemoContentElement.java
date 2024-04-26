package com.iamalexvybornyi.page.dhtmlx.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.IFrame;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.collection.WebElementList;
import com.iamalexvybornyi.page.dhtmlx.element.table.TableRowElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableDemoContentElement extends IFrame {

    private final WebElementCollection<TableRowElement> tableRows =
            new WebElementList<>(By.xpath("//div[@class='dhx_grid-row ']"), TableRowElement.class);

    public TableDemoContentElement(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public TableDemoContentElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public TableDemoContentElement(@NonNull By locator) {
        super(locator);
    }
}
