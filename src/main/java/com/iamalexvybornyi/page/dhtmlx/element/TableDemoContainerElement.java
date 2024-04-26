package com.iamalexvybornyi.page.dhtmlx.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.IFrame;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableDemoContainerElement extends IFrame {

    private final TableDemoBodyElement tableDemoBodyElement = new TableDemoBodyElement(By.xpath("//iframe[@class='st-preview-body']"));

    public TableDemoContainerElement(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public TableDemoContainerElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public TableDemoContainerElement(@NonNull By locator) {
        super(locator);
    }
}
