package data;

import dataservice.GetStockDataService;
import net.sf.json.JSONObject;
import po.StockPO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2016/3/7.
 */
public class GetStockData implements GetStockDataService {
    public static boolean underTest = false; //在JUnit测试中将它设置为true

    /**
     * 根据股票的名称得到这支股票的数据（默认为近一个月的）
     *
     * @param name
     * @return StockPO
     */
    public StockPO getStockData_name(String name) throws IOException {
//        GetStockData getStockData = new GetStockData();
//        String d = getStockData.getDateOfLatestData();//获得最新的股票数据对应的日期
//        String[] time = d.split("-");
//        Calendar c = new GregorianCalendar();
//        c.set(Integer.parseInt(time[0]),Integer.parseInt(time[1])-1,Integer.parseInt(time[2]));
//        c.add(c.DATE,-30);
//        Date date1 = c.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String d1 = simpleDateFormat.format(date1);//得到最新股票数据对应的日期的前30天

        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/" + name;
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] trading_info = rdt.parseJSON_array(s2, "trading_info");
        StockPO stockPO = new StockPO(trading_info.length);
        long[] volume = new long[trading_info.length];
        double[] pb = new double[trading_info.length];
        double[] high = new double[trading_info.length];
        double[] pe_ttm = new double[trading_info.length];
        double[] adj_price = new double[trading_info.length];
        double[] low = new double[trading_info.length];
        String[] date = new String[trading_info.length];
        double[] close = new double[trading_info.length];
        double[] open = new double[trading_info.length];
        double[] turnover = new double[trading_info.length];
        double[] increase_decreaseRate = new double[trading_info.length];
        double[] increase_decreaseNum = new double[trading_info.length];
        for (int i = 0; i < trading_info.length; i++) {
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            volume[i] = Long.parseLong(jsonObject.getString("volume"));
            pb[i] = Double.parseDouble(jsonObject.getString("pb"));
            high[i] = Double.parseDouble(jsonObject.getString("high"));
            pe_ttm[i] = Double.parseDouble(jsonObject.getString("pe_ttm"));
            adj_price[i] = Double.parseDouble(jsonObject.getString("adj_price"));
            low[i] = Double.parseDouble(jsonObject.getString("low"));
            date[i] = jsonObject.getString("date");
            close[i] = Double.parseDouble(jsonObject.getString("close"));
            open[i] = Double.parseDouble(jsonObject.getString("open"));
            turnover[i] = Double.parseDouble(jsonObject.getString("turnover"));
            if (i >= 1) {

                increase_decreaseRate[i] = ((double) Math.round((close[i] - close[i - 1]) / close[i - 1] * 10000)) / 10000;
                increase_decreaseNum[i] = ((double)Math.round((close[i] - close[i - 1])*100))/100;

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
        return stockPO;
    }

    /**
     * 得到指定日期的指定名称的股票数据
     *
     * @param name
     * @param dates
     * @return StockPO
     */
    public StockPO getStockData_name(String name, String dates) throws IOException {

        String[] time = dates.split("-");
        Calendar c = new GregorianCalendar();
        c.set(Integer.parseInt(time[0]), Integer.parseInt(time[1]) - 1, Integer.parseInt(time[2]));
        c.add(c.DATE, 1);
        Date date1 = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d = simpleDateFormat.format(date1);//得到指定日期的后1天

        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/" + name + "/?start=" + dates + "&end=" + d;
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] trading_info = rdt.parseJSON_array(s2, "trading_info");
        StockPO stockPO = new StockPO(1);
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
        for (int i = 0; i < trading_info.length; i++) {
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            volume[i] = Long.parseLong(jsonObject.getString("volume"));
            pb[i] = Double.parseDouble(jsonObject.getString("pb"));
            high[i] = Double.parseDouble(jsonObject.getString("high"));
            pe_ttm[i] = Double.parseDouble(jsonObject.getString("pe_ttm"));
            adj_price[i] = Double.parseDouble(jsonObject.getString("adj_price"));
            low[i] = Double.parseDouble(jsonObject.getString("low"));
            date[i] = jsonObject.getString("date");
            close[i] = Double.parseDouble(jsonObject.getString("close"));
            open[i] = Double.parseDouble(jsonObject.getString("open"));
            turnover[i] = Double.parseDouble(jsonObject.getString("turnover"));
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
        return stockPO;
    }

    /**
     * 得到指定时间段内的指定名称的股票
     *
     * @param name
     * @param date1
     * @param date2
     * @return StockPO
     */
    public StockPO getStockData_name(String name, String date1, String date2) throws IOException {
//        int interval = intervalBetweenTwoDates(date1, date2);
        String url;
        if(date1.length()==8){
            String d1 = date1.substring(0,4)+"-"+date1.substring(4,6)+"-"+date1.substring(6);//给date1加上-
            String d2 = date2.substring(0,4)+"-"+date2.substring(4,6)+"-"+date2.substring(6);//给date2加上-
            url = "http://121.41.106.89:8010/api/stock/" + name + "/?start=" + d1 + "&end=" + d2;
        }
        else{
            url = "http://121.41.106.89:8010/api/stock/" + name + "/?start=" + date1 + "&end=" + date2;
        }
        ReadData rdt = new ReadData();
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] trading_info = rdt.parseJSON_array(s2, "trading_info");

        int num = 0;
        for (int i = 0; i < trading_info.length; i++) {
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            if (jsonObject.getString("volume").equals("0") || jsonObject.getString("high").equals("0") || jsonObject.getString("low").equals("0") || jsonObject.getString("close").equals("0")) {
                num++;
            }
        }//计算无用的股票数据个数

        StockPO stockPO = new StockPO(trading_info.length - num);
        long[] volume = new long[trading_info.length - num];
        double[] pb = new double[trading_info.length - num];
        double[] high = new double[trading_info.length - num];
        double[] pe_ttm = new double[trading_info.length - num];
        double[] adj_price = new double[trading_info.length - num];
        double[] low = new double[trading_info.length - num];
        String[] date = new String[trading_info.length - num];
        double[] close = new double[trading_info.length - num];
        double[] open = new double[trading_info.length - num];
        double[] turnover = new double[trading_info.length - num];
        double[] increase_decreaseRate = new double[trading_info.length - num];
        double[] increase_decreaseNum = new double[trading_info.length - num];
        int k = 0;
        for (int i = 0; i < trading_info.length; i++) {
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            if (jsonObject.getString("volume").equals("0") || jsonObject.getString("high").equals("0") || jsonObject.getString("low").equals("0") || jsonObject.getString("close").equals("0")) {
                continue;
            }
            volume[k] = Long.parseLong(jsonObject.getString("volume"));
            pb[k] = Double.parseDouble(jsonObject.getString("pb"));
            high[k] = Double.parseDouble(jsonObject.getString("high"));
            pe_ttm[k] = Double.parseDouble(jsonObject.getString("pe_ttm"));
            adj_price[k] = Double.parseDouble(jsonObject.getString("adj_price"));
            low[k] = Double.parseDouble(jsonObject.getString("low"));
            date[k] = jsonObject.getString("date");
            close[k] = Double.parseDouble(jsonObject.getString("close"));
            open[k] = Double.parseDouble(jsonObject.getString("open"));
            turnover[k] = Double.parseDouble(jsonObject.getString("turnover"));
            if (k >= 1) {
                increase_decreaseRate[k] = ((double) Math.round((close[k] - close[k - 1]) / close[k - 1] * 10000)) / 10000;
                increase_decreaseNum[k] = ((double)Math.round((close[k] - close[k - 1])*100))/100;
            }
            k++;
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
        return stockPO;
    }

    /**
     * 得到所有我们感兴趣的股票数据（默认为近一个月的）
     *
     * @return List<StockPO>
     */
    public List<StockPO> getAllInterestedStock() throws IOException {
        String stocks = getID_BankStocks();
        String[] names = stocks.split(" ");
        List<StockPO> stockPOs = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            stockPOs.add(getStockData_name(names[i]));
        }

        return stockPOs;
    }

    /**
     * 得到指定日期的我们感兴趣的所有股票数据
     *
     * @param dates
     * @return List<StockPO>
     */
    public List<StockPO> getAllInterestedStock(String dates) throws IOException {
        String stocks = getID_BankStocks();
        String[] names = stocks.split(" ");
        List<StockPO> stockPOs = new ArrayList<StockPO>();
        for (int i = 0; i < names.length; i++) {
            stockPOs.add(getStockData_name(names[i], dates));
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

        String stocks = getID_BankStocks();
        String[] names = stocks.split(" ");
        List<StockPO> stockPOs = new ArrayList<StockPO>();
        for (int i = 0; i < names.length; i++) {
            stockPOs.add(getStockData_name(names[i], date1, date2));
        }
        return stockPOs;
    }


    /**
     * 从文件中读取所有银行股的代号
     *
     * @return String
     */
    public String getID_BankStocks() {
        String stocks = "";
        String temp = "";
        try {
            File file;
            if(underTest){
                file = new File("src/test/resources/bank_stock.txt");
            }
            else{
                file = new File("src/main/resources/bank_stock.txt");
            }
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while((temp = bufferedReader.readLine())!=null){
                String[] co = temp.split(" ");
                if(co[2].equals("true")){
                    stocks+=co[0]+" ";
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks.trim();
    }

    public static void main(String[] args){
        GetStockData getStockData = new GetStockData();
        getStockData.getID_BankStocks();
    }
}
