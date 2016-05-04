package po;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 宋益明 on 16-4-13.
 *
 * 测试成功
 */
public class StockTypeTest {
    @Test
    public void isUS() throws Exception {
        assertEquals(StockType.isUS("aapl"), true);
        assertEquals(StockType.isUS("sh60001"), false);
    }
}