package bl;

import database.SelfSelectStockManage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ManageSelfSelectStockTest {
    ManageSelfSelectStock manageSelfSelectStock;
    @Before
    public void addSelfSelectStockTest(){
        manageSelfSelectStock = new ManageSelfSelectStock();
        manageSelfSelectStock.addSelfSelectStock("123456789@126.com","sh600015");
    }
    @Test
    public void getAllInterestedStockTest(){
        List<String> list = manageSelfSelectStock.getAllInterestedStock("123456789@126.com");
        assertEquals("sh601009",list.get(0));
        assertEquals(2,list.size());
    }
    @After
    public void removeSelfSelectStockTest(){
        manageSelfSelectStock.removeSelfSelectStock("123456789@126.com","sh600015");
    }
}