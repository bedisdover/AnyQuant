package database;

import po.BusinessNewsPO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/14.
 * 从数据库中得到银行行业新闻
 */
public class GetBusinessNews {
    /**
     * @return List<BusinessNewsPO>
     * 从数据库中得到行业新闻
     */
    public List<BusinessNewsPO> getBusinessNews(){
        List<BusinessNewsPO> list = new ArrayList<>();
        Connect co=new Connect();
        String sql="SELECT * FROM businessnews ";
        ResultSet result=co.getResultSet(sql);

        try {
            while(result.next()){
                BusinessNewsPO businessNewsPO = new BusinessNewsPO();
                businessNewsPO.setTitle(result.getString(1));
                businessNewsPO.setContent(result.getString(2));
                businessNewsPO.setDate(result.getString(3));
                list.add(businessNewsPO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();
        return list;
    }
}
