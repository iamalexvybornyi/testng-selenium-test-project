package com.iamalexvybornyi.letskodeit;

import com.iamalexvybornyi.action.letskodeit.PracticePageCourseSearchAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

@Slf4j
public class PracticeTest extends BaseLetskodeitTest {

    @Autowired
    private PracticePageCourseSearchAction practicePageCourseSearchAction;

    @Test
    public void findTheCoursePriceTest() {
        practicePageCourseSearchAction.scrollToTheBottomOfTheCourseList();
        practicePageCourseSearchAction.verifyTheExpectedCoursePrice("/courses/learn-python3-from-scratch", "$99");
    }
}
