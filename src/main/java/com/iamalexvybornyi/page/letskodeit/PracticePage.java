package com.iamalexvybornyi.page.letskodeit;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.core.element.IFrame;
import com.iamalexvybornyi.core.element.collection.WebElementCollection;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.element.locator.PageElementCollection;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.page.letskodeit.element.CourseItem;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PracticePage implements Page {

    @PageElement(locatorType = LocatorType.ID, locator = "courses-iframe")
    private IFrame coursesFrame;
    @PageElement(locatorType = LocatorType.CSS, locator = "[class='load-more allcourse load-more-btn']")
    private Button loadMoreButton;
    @PageElementCollection(locatorType = LocatorType.XPATH, locator = "//div[@class='zen-course-list']")
    private WebElementCollection<CourseItem> coursesList;
}
