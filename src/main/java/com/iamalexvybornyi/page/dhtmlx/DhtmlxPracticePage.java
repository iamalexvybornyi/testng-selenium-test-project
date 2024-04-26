package com.iamalexvybornyi.page.dhtmlx;

import com.iamalexvybornyi.core.element.Label;
import com.iamalexvybornyi.core.page.Page;
import com.iamalexvybornyi.page.dhtmlx.element.TableDemoContainerElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DhtmlxPracticePage implements Page {
    private final TableDemoContainerElement tableDemoContainerElement = new TableDemoContainerElement(By.xpath("//div[@class='dhx-livedemo-iframe']/iframe"));
    private final Label jsDatatableHeader = new Label(By.xpath("//h2[contains(text(), 'Reinforce Your JS DataTable with DHTMLX Suite')]"));
    private final Label jsDatatableDescription = new Label(By.xpath("(//div[@class='dhx-container']//div[@class='export-content__body'])[1]"));
}
