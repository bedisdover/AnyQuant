package database;

import org.junit.Test;

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
        assertEquals("华夏银行:资本压力限制规模扩张",results.get(0));
    }
}