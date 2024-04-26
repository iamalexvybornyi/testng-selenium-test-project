package com.iamalexvybornyi.page.dhtmlx.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.IFrame;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableDemoBodyElement extends IFrame {

    private final TableDemoContentElement tableDemoContentElement = new TableDemoContentElement(By.xpath("//iframe[@id='content']"));

    public TableDemoBodyElement(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public TableDemoBodyElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    public TableDemoBodyElement(@NonNull By locator) {
        super(locator);
    }
}
