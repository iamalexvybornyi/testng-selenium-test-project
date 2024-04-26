package com.iamalexvybornyi.page.dhtmlx.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.IFrame;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class TableDemoBodyElement extends IFrame {

    @PageElement(locatorType = LocatorType.XPATH, locator = "//iframe[@id='content']")
    private TableDemoContentElement tableDemoContentElement;

    public TableDemoBodyElement(@NonNull By locator, AbstractWebElement parent) {
        super(locator, parent);
    }

    public TableDemoBodyElement(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }
}
