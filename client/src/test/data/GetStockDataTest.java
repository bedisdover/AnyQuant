package data;

import org.junit.Test;
import po.StockPO;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 2016/3/12.
 */
public class GetStockDataTest {
    @Test
    public void getStockData_nameTest() throws IOException {
        GetStockData getStockData = new GetStockData();
        String name = "sh600000";
        StockPO stockPO = getStockData.getStockData_name(name);
        assertEquals("784134",Long.toString(stockPO.getVolume()[0]));
        assertEquals("1.27",Double.toString(stockPO.getPb()[0]));
        assertEquals("18.38",Double.toString(stockPO.getAdj_price()[0]));
        assertEquals("18.39",Double.toString(stockPO.getHigh()[0]));
        assertEquals("17.15",Double.toString(stockPO.getLow()[0]));
        assertEquals("17.24",Double.toString(stockPO.getOpen()[0]));
        assertEquals("18.38",Double.toString(stockPO.getClose()[0]));
        assertEquals("0.42",Double.toString(stockPO.getTurnover()[0]));
        assertEquals("2016-02-09",stockPO.getDate()[0]);
    }
}