package database;

import org.junit.Test;
import po.IndexPO;

import static org.junit.Assert.*;

/**
 * Created by user on 2016/5/4.
 */
public class GetIndexData_DBTest {
    @Test
    public void getIndexDataBetweenTest(){
        GetIndexData_DB data_db = new GetIndexData_DB();
        IndexPO indexPO = data_db.getIndexDataBetween("2016-4-28","2016-4-28");
        assertEquals("hs300",indexPO.getName());
        assertEquals("2016-04-28",indexPO.getDate()[0]);
        assertEquals(3174.77,indexPO.getHigh()[0],0.01);
        assertEquals(3169.33,indexPO.getOpen()[0],0.01);
        assertEquals(3135.64,indexPO.getLow()[0],0.01);
        assertEquals(3160.58,indexPO.getClose()[0],0.01);
        assertEquals(3160.58,indexPO.getAdj_price()[0],0.01);
        assertEquals(6987066000L,indexPO.getVolume()[0]);
        assertEquals(-0.0016867134987618593,indexPO.getIncrease_decreaseRate()[0],0.01);
        assertEquals(-5.3400000000001455,indexPO.getIncrease_decreaseNum()[0],0.01);
    }

    @Test
    public void getLatestIndexDataTest(){
        GetIndexData_DB data_db = new GetIndexData_DB();
        IndexPO indexPO = data_db.getLatestIndexData();
        assertEquals("hs300",indexPO.getName());
        assertEquals("2012-10-10",indexPO.getDate()[0]);
        assertEquals(2325.749,indexPO.getHigh()[0],0.01);
        assertEquals(2314.593,indexPO.getOpen()[0],0.01);
        assertEquals(2304.608,indexPO.getLow()[0],0.01);
        assertEquals(2324.117,indexPO.getClose()[0],0.01);
        assertEquals(2324.117,indexPO.getAdj_price()[0],0.01);
        assertEquals(4268624000L,indexPO.getVolume()[0]);
        assertEquals(0.0017067810497307019,indexPO.getIncrease_decreaseRate()[0],0.01);
        assertEquals(3.9600000000000364,indexPO.getIncrease_decreaseNum()[0],0.01);
    }
}