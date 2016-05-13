package database;

import vo.StockNewsVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/6.
 *
 */
public class GetStockNews {
    /**
     * @param stockID
     * @return List<StockNewsVO>
     */
    public List<StockNewsVO> getStockNews(String stockID){
        List<String> titles = getNewsTitle(stockID);
        List<String> contents = getNewsContent(stockID);
        List<String> dates = getNewsDate(stockID);
        int size = titles.size();
        List<StockNewsVO> news = new ArrayList<>();
        for(int i=0;i<size;i++){
            StockNewsVO stockNewsVO = new StockNewsVO();
            stockNewsVO.setId(stockID);
            stockNewsVO.setTitle(titles.get(i));
            stockNewsVO.setContent(contents.get(i));
            stockNewsVO.setDate(dates.get(i));
            news.add(stockNewsVO);
        }
        return news;
    }

    /**
     * @param stockID
     * @return List<String>
     * 根据股票ID从数据库中得到新闻标题
     */
    public List<String> getNewsTitle(String stockID){
        List<String> titles = getNews(stockID,2);
        return titles;
    }

    /**
     * @param stockID
     * @return List<String>
     * 根据股票ID从数据库中得到新闻正文
     */
    public List<String> getNewsContent(String stockID){
        List<String> contents = getNews(stockID,3);
        return contents;
    }

    public List<String> getNewsDate(String stockID){
        List<String> dates = getNews(stockID,4);
        return dates;
    }

    private List<String> getNews(String stockID,int n){
        List<String> results = new ArrayList<>();
        Connect co=new Connect();
        String sql="SELECT * FROM companynews where id = \'"+stockID+"\'";
        ResultSet result=co.getResultSet(sql);

        try {
            while(result.next()){
                results.add(result.getString(n));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();
        return results;
    }

}
