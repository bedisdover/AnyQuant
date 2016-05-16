package database;

import data.GetStockData;
import po.StockPO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zcy on 2016/5/4.
 *
 */
public class GetStockData_DB {
    final String[] stock_id = {"sh601818","sh600015","sh600016",
            "sh600036","sh601009","sh601166",
            "sh601169", "sh601288","sh601328",
            "sh601398","sh601939","sh601988",
            "sh601998","sz000001","sz002142"};

    /**
     * @param name
     * @param date1
     * @param date2
     * @return StockPO
     * 根据股票ID和起止日期从数据库中得到股票信息
     */
    public StockPO getStockData_name(String name,String date1,String date2){
        if(date1.length()==8){
            date1 = (date1.substring(0,4)+"-"+date1.substring(4,6)+"-"+date1.substring(6));//给date1加上-
            date2 = (date2.substring(0,4)+"-"+date2.substring(4,6)+"-"+date2.substring(6));//给date2加上-
        }

        Connect co=new Connect();
        String sql="SELECT * FROM stockinfo where id = \'"+name+"\'";
        ResultSet result=co.getResultSet(sql);

        int num = 0; //记录符合要求的行数
        StockPO stockPO = null;
        try {
            while(result.next()){
                if((compareDate(date1,result.getString(8))==0)&&(compareDate(result.getString(8),date2)==0)){
                    num++;
                }
            }
            if(num==0){
                return null;
            }
            stockPO = new StockPO(num);
            result.beforeFirst();
            long[] volume = new long[num];
            double[] pb = new double[num];
            double[] high = new double[num];
            double[] pe_ttm = new double[num];
            double[] adj_price = new double[num];
            double[] low = new double[num];
            String[] date = new String[num];
            double[] close = new double[num];
            double[] open = new double[num];
            double[] turnover = new double[num];
            double[] increase_decreaseRate = new double[num];
            double[] increase_decreaseNum = new double[num];
            int k = 0;
            while(result.next()){
                if((compareDate(date1,result.getString(8))==0)&&(compareDate(result.getString(8),date2)==0)){
                    volume[k] = result.getLong(2);
                    pb[k] = result.getDouble(3);
                    high[k] = result.getDouble(4);
                    pe_ttm[k] = result.getDouble(5);
                    adj_price[k] = result.getDouble(6);
                    low[k] = result.getDouble(7);
                    date[k] = result.getString(8);
                    close[k] = result.getDouble(9);
                    open[k] = result.getDouble(10);
                    turnover[k] = result.getDouble(11);
                    increase_decreaseRate[k] = result.getDouble(12);
                    increase_decreaseNum[k] = result.getDouble(13);
                    k++;
                }
                if(k==num){
                    break;
                }
            }
            stockPO.setId(name);
            stockPO.setVolume(volume);
            stockPO.setPb(pb);
            stockPO.setHigh(high);
            stockPO.setPe_ttm(pe_ttm);
            stockPO.setAdj_price(adj_price);
            stockPO.setLow(low);
            stockPO.setDate(date);
            stockPO.setClose(close);
            stockPO.setOpen(open);
            stockPO.setTurnover(turnover);
            stockPO.setIncrease_decreaseRate(increase_decreaseRate);
            stockPO.setIncrease_decreaseNum(increase_decreaseNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        co.closeConnection();

        return stockPO;
    }

    /**
     * @return List<StockPO>
     * 得到所有银行股数据（近一个月的）
     */
    public List<StockPO> getAllStock() {
        List<StockPO> stockPOList = new ArrayList<>();
        for(int i=0;i<stock_id.length;i++){
            StockPO stockPO = getStockData_name(stock_id[i],getDate_OneMonthAgo(),getDate_Today());
            stockPOList.add(stockPO);
        }
        return stockPOList;
    }


    /**
     * @param date1
     * @param date2
     * @return int
     * 如果date1小于等于date2，返回0
     * 如果date1大于等于date2，返回1
     */
    protected int compareDate(String date1,String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = df.parse(date1);
            d2 = df.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d1.getTime()<=d2.getTime()){
            return 0;
        }
        else if(d1.getTime()>=d2.getTime()){
            return 1;
        }
        else{
            return -1;
        }
    }

    private String getDate_Today(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    private String getDate_OneMonthAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-30);
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
