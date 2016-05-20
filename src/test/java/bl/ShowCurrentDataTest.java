package bl;

import org.junit.Test;
import vo.CurrentStockVO;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/20.
 *
 */
public class ShowCurrentDataTest {
    @Test
    public void showCurrentDataTest() throws IOException {
        ShowCurrentData showCurrentData = new ShowCurrentData();
        CurrentStockVO currentStockVO = showCurrentData.showCurrentData("sh600015");
        assertEquals("sh600015",currentStockVO.getId());
    }
}