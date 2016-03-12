package bl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zmj on 2016/3/12.
 */
public class SelfSelectStockTest {
static SelfSelectStock selfSelectStock=new SelfSelectStock();

    @Test
    public void testAddStock() throws Exception {
        assertEquals(true,selfSelectStock.addStock("sh600000"));
        assertEquals(false,selfSelectStock.addStock("sh600004"));
        assertEquals(false,selfSelectStock.addStock("sh111"));
    }

    @Test
    public void testRemoveStock() throws Exception {
        assertEquals(true,selfSelectStock.removeStock("sh600004"));
        assertEquals(false,selfSelectStock.removeStock("sh600000"));
        assertEquals(false,selfSelectStock.removeStock("sh111"));
    }

    @Test
    public void testGetFollowed() throws Exception {
    while(selfSelectStock.getFollowed().hasNext()){
        assertEquals(true, selfSelectStock.exist(selfSelectStock.getFollowed().next()));
    }

    }

    @Test
    public void testExist() throws Exception {
        assertEquals(true,selfSelectStock.exist("sh600004"));
        assertEquals(false,selfSelectStock.exist("sh600000"));
    }
}