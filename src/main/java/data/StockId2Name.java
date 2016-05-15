package data;

import database.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zcy on 2016/5/14.
 * 根据股票ID得到中文名称
 */
public class StockId2Name {
    /**
     * @param id
     * @return String
     * 根据股票ID从数据库中得到股票名称
     */
    public String getStockName(String id){
        Connect co=new Connect();
        String sql="SELECT * FROM stockname where id = '"+id+"\'";
        ResultSet result=co.getResultSet(sql);
        String name = null;
        try {
            if(result.next()){
                name = result.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();
        return name;
    }
}
