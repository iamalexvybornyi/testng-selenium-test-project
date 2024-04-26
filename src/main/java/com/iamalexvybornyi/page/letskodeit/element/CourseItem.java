package com.iamalexvybornyi.page.letskodeit.element;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.Link;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import lombok.Getter;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

@Getter
public class CourseItem extends AbstractWebElement {

    @PageElement(locatorType = LocatorType.XPATH, locator = ".//a", searchWithinParent = true)
    private Link link;
    @PageElement(locatorType = LocatorType.XPATH, locator = ".//span[@class='zen-course-price dynamic-text']", searchWithinParent = true)
    private Label price;

    public CourseItem(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

}
