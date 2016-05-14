package database;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class SelfSelectStockManageTest {
    @Test
    public void getAllInterestedStockTest(){
        SelfSelectStockManage selfSelectStockManage = new SelfSelectStockManage();
        List<String> list = selfSelectStockManage.getAllInterestedStock("123456789@126.com");
        assertEquals("sh601009",list.get(0));
        assertEquals(1,list.size());
    }

}