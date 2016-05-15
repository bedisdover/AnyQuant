package bl;

import org.junit.Test;
import vo.StockIDNameVO;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class ShowStockIDNameTest {
    @Test
    public void getStockIdAndNameTest(){
        ShowStockIDName showStockIDName = new ShowStockIDName();
        StockIDNameVO stockIDNameVO = showStockIDName.getStockIdAndName("sh600015");
        assertEquals("华夏银行",stockIDNameVO.getName());
    }
}