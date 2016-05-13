package database;

import org.junit.Test;
import vo.StockNewsVO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/6.
 */
public class GetStockNewsTest {
    @Test
    public void getNewsTitleTest(){
        GetStockNews getStockNews = new GetStockNews();
        List<String> results = getStockNews.getNewsTitle("sh600015");
        assertEquals("华夏银行:资本压力限制规模扩张",results.get(0));
    }

    @Test
    public void getNewsContentTest(){
        GetStockNews getStockNews = new GetStockNews();
        List<String> results = getStockNews.getNewsContent("sh600015");
        assertEquals("2015年华夏银行归",results.get(0).substring(0,10));
    }
    @Test
    public void getNewsDateTest(){
        GetStockNews getStockNews = new GetStockNews();
        List<String> results = getStockNews.getNewsDate("sh600015");
        assertEquals("2016-05-12",results.get(0));
    }
    @Test
    public void getStockNewsTest(){
        GetStockNews getStockNews = new GetStockNews();
        List<StockNewsVO> results = getStockNews.getStockNews("sh600015");
        assertEquals("华夏银行:资本压力限制规模扩张",results.get(0).getTitle());
    }
}