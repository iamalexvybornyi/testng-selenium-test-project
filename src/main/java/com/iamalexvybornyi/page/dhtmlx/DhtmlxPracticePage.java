package com.iamalexvybornyi.page.dhtmlx;

import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.element.locator.LocatorType;
import com.iamalexvybornyi.core.element.locator.PageElement;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.page.dhtmlx.element.TableDemoContainerElement;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DhtmlxPracticePage implements Page {
    @PageElement(locatorType = LocatorType.XPATH, locator = "//div[@class='dhx-livedemo-iframe']/iframe")
    private TableDemoContainerElement tableDemoContainerElement;
    @PageElement(locatorType = LocatorType.XPATH, locator = "//h2[contains(text(), 'Reinforce Your JS DataTable with DHTMLX Suite')]")
    private Label jsDatatableHeader;
    @PageElement(locatorType = LocatorType.XPATH, locator = "(//div[@class='dhx-container']//div[@class='export-content__body'])[1]")
    private Label jsDatatableDescription;
}
