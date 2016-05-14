package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class SelfSelectStockManageTest {
    SelfSelectStockManage selfSelectStockManage;
    @Before
    public void addSelfSelectStockTest(){
        selfSelectStockManage = new SelfSelectStockManage();
        selfSelectStockManage.addSelfSelectStock("123456789@126.com","sh600015");
    }
    @Test
    public void getAllInterestedStockTest(){
        List<String> list = selfSelectStockManage.getAllInterestedStock("123456789@126.com");
        assertEquals("sh601009",list.get(0));
        assertEquals(2,list.size());
    }
    @After
    public void removeSelfSelectStockTest(){
        selfSelectStockManage.removeSelfSelectStock("123456789@126.com","sh600015");
    }

}