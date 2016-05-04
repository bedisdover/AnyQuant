package data;

import org.junit.Test;
import po.IndexPO;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 2016/3/12.
 */
public class GetIndexDataTest {
    @Test
    public void getLatestIndexDataTest() throws IOException {
        GetIndexData getIndexData = new GetIndexData();
        IndexPO indexPO = getIndexData.getLatestIndexData();
        assertEquals("hs300",indexPO.getName());
        assertEquals("24686476800",Long.toString(indexPO.getVolume()[0]));
        assertEquals("3617.657",Double.toString(indexPO.getAdj_price()[0]));
        assertEquals("3641.392",Double.toString(indexPO.getHigh()[0]));
        assertEquals("3594.742",Double.toString(indexPO.getLow()[0]));
        assertEquals("3604.671",Double.toString(indexPO.getOpen()[0]));
        assertEquals("3617.657",Double.toString(indexPO.getClose()[0]));
        assertEquals("2015-03-13",indexPO.getDate()[0]);
    }
}