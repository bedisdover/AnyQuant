package data;

import org.junit.Test;
import po.IndexPO;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zcy on 2016/3/12.
 */
public class GetIndexDataTest {
    @Test
    public void getLatestIndexDataTest() throws IOException {
        GetIndexData getIndexData = new GetIndexData();
        IndexPO indexPO = getIndexData.getLatestIndexData();
        assertEquals("hs300",indexPO.getName());
        assertNotNull(indexPO.getDate()[0]);
    }

    @Test
    public void getIndexDataBetweenTest() throws IOException {
        GetIndexData getIndexData = new GetIndexData();
        IndexPO indexPO = getIndexData.getLatestIndexData();
        assertEquals("hs300",indexPO.getName());
        assertNotNull(indexPO.getDate()[0]);
    }
}