package data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class StockId2NameTest {
    @Test
    public void getStockNameTest(){
        StockId2Name stockId2Name = new StockId2Name();
        assertEquals("华夏银行",stockId2Name.getStockName("sh600015"));
    }
}