package database;

import data.GetStockData;
import org.junit.Test;
import po.StockPO;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/4.
 */
public class GetStockData_DBTest {
    @Test
    public void getStockData_nameTest(){
        GetStockData_DB getStockData_db = new GetStockData_DB();
        StockPO stockPO = getStockData_db.getStockData_name("sh600016","2016-04-28","2016-04-28");
        assertEquals("sh600016",stockPO.getId());
        assertEquals("2016-04-28",stockPO.getDate()[0]);
        assertEquals(44022700,stockPO.getVolume()[0]);
        assertEquals(1.13,stockPO.getPb()[0],0.001);
        assertEquals(9.5,stockPO.getHigh()[0],0.001);
        assertEquals(7.41,stockPO.getPe_ttm()[0],0.001);
        assertEquals(9.37,stockPO.getAdj_price()[0],0.001);
        assertEquals(9.33,stockPO.getLow()[0],0.001);
        assertEquals(9.37,stockPO.getClose()[0],0.001);
        assertEquals(9.43,stockPO.getOpen()[0],0.001);
        assertEquals(0.15,stockPO.getTurnover()[0],0.001);
        assertEquals(-0.0021,stockPO.getIncrease_decreaseRate()[0],0.001);
        assertEquals(-0.02,stockPO.getIncrease_decreaseNum()[0],0.001);

        stockPO = getStockData_db.getStockData_name("sh600016","20160428","20160428");
        assertEquals("2016-04-28",stockPO.getDate()[0]);

        stockPO = getStockData_db.getStockData_name("sh500016","20160428","20160428");
        assertNull(stockPO);
    }

    @Test
    public void getAllInterestedStockTest(){
        GetStockData_DB getStockData_db = new GetStockData_DB();
        GetStockData.underTest=true;
        List<StockPO> stockPOList = null;
        try {
            stockPOList = getStockData_db.getAllInterestedStock("2016-03-28","2016-04-28");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("2016-03-28",stockPOList.get(0).getDate()[0]);

        try {
            stockPOList = getStockData_db.getAllInterestedStock();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(stockPOList.get(0));

        GetStockData.underTest=false;
    }

}