package database;

import bl.util.MyDate;
import data.GetStockData;
import po.StockPO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zcy on 2016/5/4.
 *
 */
public class GetStockData_DB {
    public final static String[] stock_id = {"sh601818","sh600015","sh600016",
            "sh600036","sh601009","sh601166",
            "sh601169", "sh601288","sh601328",
            "sh601398","sh601939","sh601988",
            "sh601998","sz000001","sz002142"}; //所有银行股的代号

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
                if((MyDate.compareDate(date1,result.getString(8))==0)&&(MyDate.compareDate(result.getString(8),date2)==0)){
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
                if((MyDate.compareDate(date1,result.getString(8))==0)&&(MyDate.compareDate(result.getString(8),date2)==0)){
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
            StockPO stockPO = getStockData_name(stock_id[i], MyDate.getDate_OneMonthAgo(),MyDate.getDate_Today());
            stockPOList.add(stockPO);
        }
        return stockPOList;
    }

    /**
     * @return List<StockPO>
     * 得到数据库中某支股票最新一天的股票数据
     */
    public StockPO getLatestStock(String id) throws SQLException {
        Connect co=new Connect();
        long[] volume = new long[1];
        double[] pb = new double[1];
        double[] high = new double[1];
        double[] pe_ttm = new double[1];
        double[] adj_price = new double[1];
        double[] low = new double[1];
        String[] date = new String[1];
        double[] close = new double[1];
        double[] open = new double[1];
        double[] turnover = new double[1];
        double[] increase_decreaseRate = new double[1];
        double[] increase_decreaseNum = new double[1];

        String sql="SELECT * FROM stockinfo where id = \'"+id+"\'";
        ResultSet result=co.getResultSet(sql);
        result.last();
        StockPO stockPO = new StockPO(1);

        volume[0] = result.getLong(2);
        pb[0] = result.getDouble(3);
        high[0] = result.getDouble(4);
        pe_ttm[0] = result.getDouble(5);
        adj_price[0] = result.getDouble(6);
        low[0] = result.getDouble(7);
        date[0] = result.getString(8);
        close[0] = result.getDouble(9);
        open[0] = result.getDouble(10);
        turnover[0] = result.getDouble(11);
        increase_decreaseRate[0] = result.getDouble(12);
        increase_decreaseNum[0] = result.getDouble(13);

        stockPO.setId(id);
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

        co.closeConnection();

        return stockPO;
    }

    public List<StockPO> getLatestStock() throws SQLException {
        List<StockPO> stockPOList = new ArrayList<>();
        for(int i=0;i<stock_id.length;i++){
            StockPO stockPO = getLatestStock(stock_id[i]);
            stockPOList.add(stockPO);
        }
        return stockPOList;
    }

    /**
     * @param id 股票代号
     * @return StockPO
     * 股票数据日期不连续
     */
    public StockPO getStockData_withInterval(String id,int interval){
        Connect co=new Connect();
        String sql="SELECT * FROM stockinfo where id = \'"+id+"\'";
        ResultSet result=co.getResultSet(sql);

        int num = 100; //数据个数
        StockPO stockPO = new StockPO(num);
        try {
            int size = result.getFetchSize();
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
            int n = 0;
            for(;k<size-interval*num;k++){
                result.next();
            }
            k = 0;
            while(result.next()){
                if(k%interval==0){
                    volume[n] = result.getLong(2);
                    pb[n] = result.getDouble(3);
                    high[n] = result.getDouble(4);
                    pe_ttm[n] = result.getDouble(5);
                    adj_price[n] = result.getDouble(6);
                    low[n] = result.getDouble(7);
                    date[n] = result.getString(8);
                    close[n] = result.getDouble(9);
                    open[n] = result.getDouble(10);
                    turnover[n] = result.getDouble(11);
                    increase_decreaseRate[n] = result.getDouble(12);
                    increase_decreaseNum[n] = result.getDouble(13);
                    n++;
                }
                if(n==num){
                    break;
                }
                k++;
            }
            stockPO.setId(id);
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

}
