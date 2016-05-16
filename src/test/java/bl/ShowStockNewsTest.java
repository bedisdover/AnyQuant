package bl;

import org.junit.Test;
import vo.StockNewsVO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowStockNewsTest {
    @Test
    public void showStockNewsTest(){
        ShowStockNews showStockNews = new ShowStockNews();
        List<StockNewsVO> results = showStockNews.showStockNews("sh600015");
        assertEquals("华夏银行:资本压力限制规模扩张",results.get(0).getTitle());
        assertEquals("2015年华夏银行归",results.get(0).getContent().substring(0,10));
        assertEquals("2016-05-12",results.get(0).getDate());
    }

}