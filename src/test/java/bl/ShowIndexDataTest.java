package bl;

import org.junit.Test;
import vo.IndexVO;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowIndexDataTest {
    @Test
    public void getLatestIndexDataTest() throws IOException {
        ShowIndexData showIndexData = new ShowIndexData();
        IndexVO indexVO = showIndexData.getLatestIndexData();
        assertEquals("hs300",indexVO.getName());
        assertEquals("2016-04-28",indexVO.getDate()[870]);
        assertEquals(3174.77,indexVO.getHigh()[870],0.0001);
        assertEquals(3169.33,indexVO.getOpen()[870],0.0001);
        assertEquals(3135.64,indexVO.getLow()[870],0.0001);
        assertEquals(3160.58,indexVO.getClose()[870],0.0001);
        assertEquals(3160.58,indexVO.getAdj_price()[870],0.0001);
        assertEquals(6987066000L,indexVO.getVolume()[870]);
        assertEquals(-0.0016867134987618593,indexVO.getIncrease_decreaseRate()[870],0.01);
        assertEquals(-5.3400000000001455,indexVO.getIncrease_decreaseNum()[870],0.01);
    }

    @Test
    public void getIndexDataBetweenTest(){
        ShowIndexData showIndexData = new ShowIndexData();
        IndexVO indexVO = showIndexData.getIndexDataBetween("2016-04-28","2016-04-28");
        assertEquals("hs300",indexVO.getName());
        assertEquals("2016-04-28",indexVO.getDate()[0]);
        assertEquals(3174.77,indexVO.getHigh()[0],0.01);
        assertEquals(3169.33,indexVO.getOpen()[0],0.01);
        assertEquals(3135.64,indexVO.getLow()[0],0.01);
        assertEquals(3160.58,indexVO.getClose()[0],0.01);
        assertEquals(3160.58,indexVO.getAdj_price()[0],0.01);
        assertEquals(6987066000L,indexVO.getVolume()[0]);
        assertEquals(-0.0016867134987618593,indexVO.getIncrease_decreaseRate()[0],0.01);
        assertEquals(-5.3400000000001455,indexVO.getIncrease_decreaseNum()[0],0.01);
    }

}