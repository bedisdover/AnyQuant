package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/6.
 */
public class GetStockNews {
    /**
     * @param stockID
     * @return String
     * 根据股票ID从数据库中得到新闻标题
     */
    public List<String> getNewsTitle(String stockID){
        List<String> titles = new ArrayList<>();
        Connect co=new Connect();
        String sql="SELECT * FROM stockinfo where id = \'"+stockID+"\'";
        ResultSet result=co.getResultSet(sql);

        try {
            while(result.next()){
                titles.add(result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
    }

    public static void main(String[] args){
        GetStockNews getStockNews = new GetStockNews();
        for(int i=0;i<5;i++){
            System.out.println(getStockNews.getNewsTitle("sh600015").get(i));
        }
    }
}
