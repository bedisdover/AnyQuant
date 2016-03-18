package po;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by 宋益明 on 16-3-18.
 *
 * 测试股票代码-名称转换类
 */
public class TransferTest {

    @Test
    public void testGetName() throws Exception {
        assertEquals(Transfer.getName("sh600000"), "浦发银行");
        assertEquals(Transfer.getName("sh601818"), "光大银行");
        assertEquals(Transfer.getName("sh600015"), "华夏银行");
        assertEquals(Transfer.getName("sh600016"), "民生银行");
        assertEquals(Transfer.getName("sh600036"), "招商银行");
        assertEquals(Transfer.getName("sh601009"), "南京银行");
        assertEquals(Transfer.getName("sh601166"), "兴业银行");
        assertEquals(Transfer.getName("sz000001"), "平安银行");
        assertEquals(Transfer.getName("sz002142"), "宁波银行");
    }
}