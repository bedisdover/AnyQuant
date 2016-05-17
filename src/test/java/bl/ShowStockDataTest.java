package bl;

import org.junit.Test;
import vo.StockVO;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowStockDataTest {
    @Test
    public void getStockDataTest() throws IOException {
        ShowStockData showStockData = new ShowStockData();
        StockVO stockVO = showStockData.getStockData("sh600015","2005-01-04","2005-01-06");
        assertEquals("2005-01-04",stockVO.getDate()[0]);
        assertEquals(4.16001,stockVO.getHigh()[0],0.01);
        assertEquals(4.14001,stockVO.getOpen()[0],0.01);
        assertEquals(4.06999,stockVO.getLow()[0],0.01);
        assertEquals(4.10001,stockVO.getClose()[0],0.01);
    }
}