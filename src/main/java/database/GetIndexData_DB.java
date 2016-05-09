package database;


import data.GetIndexData;
import po.IndexPO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by zcy on 2016/5/4.
 */
public class GetIndexData_DB {
    /**
     * @param date1
     * @param date2
     * @return IndexPO
     * 根据起止日期从数据库中读取大盘数据
     */
    public IndexPO getIndexDataBetween(String date1, String date2){
        GetStockData_DB g = new GetStockData_DB();
        if(date1.charAt(4)!='-'){
            date1 = date1.substring(0,4)+"-"+date1.substring(4,6)+"-"+date1.substring(6);//给date1加上-
            date2 = date2.substring(0,4)+"-"+date2.substring(4,6)+"-"+date2.substring(6);//给date2加上-
        }//如果日期格式是yyyyMMdd，加上-

        Connect co=new Connect();
        String sql="SELECT * FROM indexinfo ";
        ResultSet result=co.getResultSet(sql);

        int num=0; //记录符合要求的行数
        IndexPO indexPO = null;
        try {
            while(result.next()){
                if((g.compareDate(date1,result.getString(6))==0)&&(g.compareDate(result.getString(6),date2)==0)
                        &&(result.getLong(2)!=0)&&(result.getDouble(3)!=0)){
                    num++;
                }
            }
            result.beforeFirst();//游标回到起始处
            indexPO = new IndexPO(num);
            long[] volume = new long[num];
            double[] high = new double[num];
            double[] adj_price = new double[num];
            double[] low = new double[num];
            double[] close = new double[num];
            double[] open = new double[num];
            String[] date = new String[num];
            double[] increase_decreaseRate = new double[num];
            double[] increase_decreaseNum = new double[num];

            int k = 0;
            while(result.next()){
                if((g.compareDate(date1,result.getString(6))==0)&&(g.compareDate(result.getString(6),date2)==0)
                        &&(result.getLong(2)!=0)&&(result.getDouble(3)!=0)){
                    volume[k] = result.getLong(2);
                    high[k] = result.getDouble(3);
                    adj_price[k] = result.getDouble(4);
                    low[k] = result.getDouble(5);
                    date[k] = result.getString(6);
                    close[k] = result.getDouble(7);
                    open[k] = result.getDouble(8);
                    increase_decreaseRate[k] = result.getDouble(9);
                    increase_decreaseNum[k] = result.getDouble(10);
                    k++;
                }
                if(k==num){
                    break;
                }
            }
            indexPO.setVolume(volume);
            indexPO.setHigh(high);
            indexPO.setAdj_price(adj_price);
            indexPO.setLow(low);
            indexPO.setDate(date);
            indexPO.setClose(close);
            indexPO.setOpen(open);
            indexPO.setIncrease_decreaseRate(increase_decreaseRate);
            indexPO.setIncrease_decreaseNum(increase_decreaseNum);
            indexPO.setName("hs300");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();

        return indexPO;
    }

    /**
     * @return IndexPO
     * 得到最新的大盘数据（默认为2012-10-10至今）
     */
    public IndexPO getLatestIndexData(){
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(d);//今天的日期

        return getIndexDataBetween("2012-10-10",today);
    }

    public static void main(String[] args){
        GetIndexData_DB getIndexData_db = new GetIndexData_DB();
        IndexPO indexPO = getIndexData_db.getLatestIndexData();
        System.out.println(indexPO.getName());
        System.out.println(indexPO.getDate()[0]);
        System.out.println(indexPO.getHigh()[0]);
        System.out.println(indexPO.getOpen()[0]);
        System.out.println(indexPO.getLow()[0]);
        System.out.println(indexPO.getClose()[0]);
        System.out.println(indexPO.getAdj_price()[0]);
        System.out.println(indexPO.getVolume()[0]);
        System.out.println(indexPO.getIncrease_decreaseRate()[0]);
        System.out.println(indexPO.getIncrease_decreaseNum()[0]);
    }
}
