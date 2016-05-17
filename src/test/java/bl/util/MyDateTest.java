package bl.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/17.
 *
 */
public class MyDateTest {
    @Test
    public void  getDate_TodayTest(){
        assertNotNull(MyDate.getDate_Today());
        assertNotNull(MyDate.getDate_OneMonthAgo());
    }
    @Test
    public void getDate_NDaysAgo(){
        assertNotNull(MyDate.getDate_NDaysAgo(10));
    }
    @Test
    public void compareDateTest(){
        assertEquals(1,MyDate.compareDate("2016-05-21","2016-02-22"));
    }
}