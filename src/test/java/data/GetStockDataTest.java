package data;

import org.junit.Test;
import po.StockPO;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zcy on 2016/3/12.
 *
 */

public class GetStockDataTest {
    @Test
    public void getStockData_nameTest() throws IOException {
        GetStockData getStockData = new GetStockData();
        String name = "sh600015";
        StockPO stockPO = getStockData.getStockData_name(name);
        assertNotNull(stockPO.getDate()[0]);

        stockPO = getStockData.getStockData_name(name,"2016-04-28");
        assertEquals("2016-04-28",stockPO.getDate()[0]);

        stockPO = getStockData.getStockData_name(name,"2016-04-28","2016-04-29");
        assertEquals("2016-04-28",stockPO.getDate()[0]);
    }

    @Test
    public void getAllInterestedStockTest() throws IOException {
        GetStockData getStockData = new GetStockData();
        List<StockPO> list = getStockData.getAllInterestedStock();
        assertNotNull(list);

        list = getStockData.getAllInterestedStock("2016-04-28");
        assertNotNull(list);

        list = getStockData.getAllInterestedStock("2016-04-28","2016-04-29");
        assertNotNull(list);
    }
}