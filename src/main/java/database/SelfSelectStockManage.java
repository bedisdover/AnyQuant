package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class SelfSelectStockManage {
    /**
     * @param userid
     * @return List<String>
     * 得到这个用户所有自选股代号
     */
    public List<String> getAllInterestedStock(String userid){
        List<String> stocks = new ArrayList<>();
        Connect co=new Connect();
        String sql="SELECT * FROM selectstock where userid = \'"+userid+"\'";
        ResultSet result=co.getResultSet(sql);

        try {
            while(result.next()){
                stocks.add(result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }

}
