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
    public void getStockVOsTest() throws IOException {
        assertEquals(15,sortStock.getStockVOs().size());
    }

    @Test
    public void increase_sortTest() throws IOException {
        assertNotNull(sortStock.increase_sort());
    }

    @Test
    public void decrease_sortTest() throws IOException {
        assertNotNull(sortStock.decrease_sort());
    }

    @Test
    public void volume_sortTest() throws IOException {
        assertNotNull(sortStock.volume_sort());
    }
}