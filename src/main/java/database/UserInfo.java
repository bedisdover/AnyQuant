package database;

import po.UserPO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/9.
 *
 */
public class UserInfo {
    public UserPO getUserInfo(String id,String password){
        Connect co=new Connect();
        String sql="SELECT * FROM user where id = \'"+id+"\'"+" and password = \'"+password+"\'";
        ResultSet result = co.getResultSet(sql);

        UserPO userPO = new UserPO();
        List<String> stockList = new ArrayList<>();
        try {
            if(result.next()){
                userPO.setId(id);
                userPO.setPassword(password);

                //从selectstock表中找出该用户的所有自选股
                sql = "SELECT * FROM selectstock where userid = \'"+id+"\'";
                result = co.getResultSet(sql);
                while(result.next()){
                    stockList.add(result.getString(2));
                }
                userPO.setStockList(stockList);
            } //账号密码正确
            else{
                co.closeConnection();
                return null;
            } //账号或密码错误
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();
        return userPO;
    }
}
