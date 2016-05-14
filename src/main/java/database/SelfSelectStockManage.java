package database;

import java.sql.PreparedStatement;
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

    /**
     * @param userid
     * @param stockid
     * 向数据库中添加该用户的自选股
     */
    public void addSelfSelectStock(String userid,String stockid){
        Connect co=new Connect();
        String sql="insert into selectstock values (?,?)";
        PreparedStatement pstmt=co.getPreparedStatement(sql);
        try {
            pstmt.setString(1,userid);
            pstmt.setString(2,stockid);
            pstmt.executeUpdate();
            co.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userid
     * @param stockid
     * 从数据库中删除该用户自选股
     */
    public void removeSelfSelectStock(String userid,String stockid){
        Connect co=new Connect();
        String sql="DELETE FROM selectstock WHERE userid = ? and stockid = ?";
        PreparedStatement pstmt=co.getPreparedStatement(sql);
        try {
            pstmt.setString(1, userid);
            pstmt.setString(2, stockid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();
    }
}
