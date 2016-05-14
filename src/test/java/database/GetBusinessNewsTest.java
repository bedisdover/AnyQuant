package database;

import org.junit.Test;
import po.BusinessNewsPO;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class GetBusinessNewsTest {
    @Test
    public void getBusinessNewsTest(){
        GetBusinessNews getBusinessNews = new GetBusinessNews();
        List<BusinessNewsPO> list = getBusinessNews.getBusinessNews();
        assertEquals(40,list.size());
        assertEquals("2016-05-13",list.get(0).getDate());
    }

}