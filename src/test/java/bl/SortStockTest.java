package bl;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class SortStockTest {
    SortStock sortStock;
    @Before
    public void init() throws IOException {
        sortStock = new SortStock();
    }

    @Test
    public void getStockVOsTest(){
        assertEquals(15,sortStock.getStockVOs().size());
    }

    @Test
    public void increase_sortTest(){

    }
}