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
 */
public class GetStockData_DB {
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

        return stockPO;
    }

    /**
     * 得到所有我们感兴趣的股票数据（默认为近一个月的）
     *
     * @return List<StockPO>
     */
    public List<StockPO> getAllInterestedStock() throws IOException {
        GetStockData getStockData = new GetStockData();
        String stocks = getStockData.getID_BankStocks();
        String[] names = stocks.split(" ");
        List<StockPO> stockPOs = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(d);//今天的日期
        c.add(c.DATE,-30);
        d = c.getTime();
        String aMonthAgo = simpleDateFormat.format(d);//一个月前的日期

        for (int i = 0; i < names.length; i++) {
            stockPOs.add(getStockData_name(names[i],aMonthAgo,today));
        }

        return stockPOs;
    }

    /**
     * 得到指定时间段内的我们感兴趣的所有股票数据
     *
     * @param date1
     * @param date2
     * @return List<StockPO>
     */
    public List<StockPO> getAllInterestedStock(String date1, String date2) throws IOException {
        GetStockData getStockData = new GetStockData();
        String stocks = getStockData.getID_BankStocks();
        String[] names = stocks.split(" ");
        List<StockPO> stockPOs = new ArrayList<StockPO>();
        for (int i = 0; i < names.length; i++) {
            stockPOs.add(getStockData_name(names[i], date1, date2));
        }
        return stockPOs;
    }

    /**
     * @param date1
     * @param date2
     * @return int
     * 如果date1小于等于date2，返回0
     * 如果date1大于等于date2，返回1
     */
    private int compareDate(String date1,String date2){
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

    public static void main(String[] args) throws IOException {
        GetStockData_DB getStockData_db = new GetStockData_DB();
        List<StockPO> stockPO = getStockData_db.getAllInterestedStock();
        for(int i=0;i<stockPO.size();i++){
            System.out.println(stockPO.get(i).getDate()[0]);
        }
    }
}
