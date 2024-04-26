package com.iamalexvybornyi.dhtmlx;

import com.iamalexvybornyi.action.dhtmlx.DhtmlxDemoTableAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class DhtmlxTableSearchTest extends BaseDhtmlxTest {

    @Autowired
    private DhtmlxDemoTableAction dhtmlxDemoTableAction;

    @Test
    public void findTheGreenMileBookTestWithPages() {
        Duration pauseInBetweenScrolls = Duration.of(2, ChronoUnit.SECONDS);
        dhtmlxDemoTableAction.scrollToDhtmlxDemoTable(pauseInBetweenScrolls);
        dhtmlxDemoTableAction.verifyTheAuthorOfTheBook("The Green Mile", "Stephen King");
    }
}
