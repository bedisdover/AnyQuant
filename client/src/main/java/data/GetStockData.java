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
    /**
     * 得到当天的所有上交所股票数据
     * @return List<StockPO>
     */
//    public List<StockPO> getStockData_today_sh(){
//        GetStockData getStockData = new GetStockData();
//        String d = getStockData.getDateOfLatestData();//获得最新的股票数据对应的日期
//        String[] time = d.split("-");
//        Calendar c = new GregorianCalendar();
//        c.set(Integer.parseInt(time[0]),Integer.parseInt(time[1])-1,Integer.parseInt(time[2]));
//        c.add(c.DATE,1);
//        Date date1 = c.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String d1 = simpleDateFormat.format(date1);//得到最新股票数据对应的日期的后一天
//
//        ArrayList<StockPO> spo = new ArrayList<StockPO>();
//        ReadData rdt = new ReadData();
//        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sh";
//        String result = rdt.getData(url);
//        String[] info = rdt.parseJson(result, "data","link");//info数组存放了所有上交所股票信息的网址
//        for(int i=0;i<25;i++){
//            if(i==7){
//                continue;
//            }
//            String str = info[i]+"/?start="+d+"&end="+d1;
//            String stockInfo = rdt.getData(str);
//            String s1 = rdt.parseJSON(stockInfo,"data");
//            String[] trading_info = rdt.parseJSON_array(s1,"trading_info");
//            StockPO stockPO = new StockPO(1);
//            JSONObject jsonObject = JSONObject.fromObject(trading_info[0]);
//
//            long[] volume = new long[1];
//            volume[0] = Long.parseLong(jsonObject.getString("volume"));
//            stockPO.setVolume(volume);
//
//            double[] pb = new double[1];
//            pb[0] = Double.parseDouble(jsonObject.getString("pb"));
//            stockPO.setPb(pb);
//
//            double[] high = new double[1];
//            high[0] = Double.parseDouble(jsonObject.getString("high"));
//            stockPO.setHigh(high);
//
//            double[] pe_ttm = new double[1];
//            pe_ttm[0] = Double.parseDouble(jsonObject.getString("pe_ttm"));
//            stockPO.setPe_ttm(pe_ttm);
//
//            double[] adj_price = new double[1];
//            adj_price[0] = Double.parseDouble(jsonObject.getString("adj_price"));
//            stockPO.setAdj_price(adj_price);
//
//            double[] low = new double[1];
//            low[0] = Double.parseDouble(jsonObject.getString("low"));
//            stockPO.setLow(low);
//
//            String[] date = new String[1];
//            date[0] = jsonObject.getString("date");
//            stockPO.setDate(date);
//
//            double[] close = new double[1];
//            close[0] = Double.parseDouble(jsonObject.getString("close"));
//            stockPO.setClose(close);
//
//            double[] open = new double[1];
//            open[0] = Double.parseDouble(jsonObject.getString("open"));
//            stockPO.setOpen(open);
//
//            double[] turnover = new double[1];
//            turnover[0] = Double.parseDouble(jsonObject.getString("turnover"));
//            stockPO.setTurnover(turnover);
//
//            stockPO.setId(rdt.parseJSON(s1,"name"));

    //TODO 待检验
//            System.out.println(stockPO.getId());
//            if (Transfer.getName(stockPO.getId())!=null) {
//                spo.add(stockPO);
//            }
//            spo.add(stockPO);
//        }System.out.println(spo.size());
//        return spo;
//    }

    /**
     * 得到当天的深交所所有股票信息
     * @return List<StockPO>
     */
//    public List<StockPO> getStockData_today_sz(){
//        GetStockData getStockData = new GetStockData();
//        String d = getStockData.getDateOfLatestData();//获得最新的股票数据对应的日期
//        String[] time = d.split("-");
//        Calendar c = new GregorianCalendar();
//        c.set(Integer.parseInt(time[0]),Integer.parseInt(time[1])-1,Integer.parseInt(time[2]));
//        c.add(c.DATE,1);
//        Date date1 = c.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String d1 = simpleDateFormat.format(date1);//得到最新股票数据对应的日期的后一天
//
//        ArrayList<StockPO> spo = new ArrayList<StockPO>();
//        ReadData rdt = new ReadData();
//        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sz";
//        String result = rdt.getData(url);
//        String[] info = rdt.parseJson(result, "data","link");//info数组存放了所有深交所股票信息的网址
//        for(int i=0;i<info.length;i++){
//
//            String str = info[i]+"/?start="+d+"&end="+d1;
//            String stockInfo = rdt.getData(str);
//            String s1 = rdt.parseJSON(stockInfo,"data");
//            String[] trading_info = rdt.parseJSON_array(s1,"trading_info");
//            StockPO stockPO = new StockPO(1);
//            JSONObject jsonObject = JSONObject.fromObject(trading_info[0]);
//
//            long[] volume = new long[1];
//            volume[0] = Long.parseLong(jsonObject.getString("volume"));
//            stockPO.setVolume(volume);
//
//            double[] pb = new double[1];
//            pb[0] = Double.parseDouble(jsonObject.getString("pb"));
//            stockPO.setPb(pb);
//
//            double[] high = new double[1];
//            high[0] = Double.parseDouble(jsonObject.getString("high"));
//            stockPO.setHigh(high);
//
//            double[] pe_ttm = new double[1];
//            pe_ttm[0] = Double.parseDouble(jsonObject.getString("pe_ttm"));
//            stockPO.setPe_ttm(pe_ttm);
//
//            double[] adj_price = new double[1];
//            adj_price[0] = Double.parseDouble(jsonObject.getString("adj_price"));
//            stockPO.setAdj_price(adj_price);
//
//            double[] low = new double[1];
//            low[0] = Double.parseDouble(jsonObject.getString("low"));
//            stockPO.setLow(low);
//
//            String[] date = new String[1];
//            date[0] = jsonObject.getString("date");
//            stockPO.setDate(date);
//
//            double[] close = new double[1];
//            close[0] = Double.parseDouble(jsonObject.getString("close"));
//            stockPO.setClose(close);
//
//            double[] open = new double[1];
//            open[0] = Double.parseDouble(jsonObject.getString("open"));
//            stockPO.setOpen(open);
//
//            double[] turnover = new double[1];
//            turnover[0] = Double.parseDouble(jsonObject.getString("turnover"));
//            stockPO.setTurnover(turnover);
//
//            stockPO.setId(rdt.parseJSON(s1,"name"));
//
//            spo.add(stockPO);
//        }System.out.println(spo.size());
//        return spo;
//    }

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
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/" + name + "/?start=" + date1 + "&end=" + date2;
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

    public double getIncrease_decreaseRate(String stockID) throws IOException {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list=" + stockID);
        String[] strings = s.split(",");
        double close_yesterday = Double.parseDouble(strings[2]);
        double currentPrice = Double.parseDouble(strings[3]);
        return ((double) Math.round((currentPrice - close_yesterday) / close_yesterday * 10000)) / 10000;
    }

    public double getIncrease_decreaseNum(String stockID) throws IOException {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list=" + stockID);
        String[] strings = s.split(",");
        double close_yesterday = Double.parseDouble(strings[2]);
        double currentPrice = Double.parseDouble(strings[3]);
        return Math.round((currentPrice - close_yesterday) * 100) / 100;
    }

    /**
     * 获得api中的最新股票数据对应的日期
     *
     * @return String
     */
    private String getDateOfLatestData() throws IOException {
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/sh600000";
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] dates = rdt.parseJson(s2, "trading_info", "date");
        return dates[dates.length - 1];
    }

    private int intervalBetweenTwoDates(String d1, String d2) {
        String[] date1 = d1.split("-");
        String[] date2 = d2.split("-");
        Calendar c1 = new GregorianCalendar();
        c1.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]) - 1, Integer.parseInt(date1[2]));
        Calendar c2 = new GregorianCalendar();
        c2.set(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]) - 1, Integer.parseInt(date2[2]));
        long t1 = c1.getTimeInMillis();
        long t2 = c2.getTimeInMillis();
        return (int) ((t2 - t1) / (1000 * 3600 * 24));
    }

    /**
     * 从文件中读取所有银行股的代号
     *
     * @return String
     */
    public String getID_BankStocks() {
        String stocks = "";
        try {
            File file = new File("client/src/main/resources/Stocks_Bank.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            stocks = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public static void main(String[] args) throws IOException {
//        ReadData rdt = new ReadData();
//        String url = "http://121.41.106.89:8010/api/stock/sh600000/?start=2016-02-25&end=2016-02-26";
//        rdt.getData(url);
//        System.out.println();

        GetStockData g = new GetStockData();
//        g.getStockData_name("sh600000");
//        g.getDateOfLatestData();
//        StockPO stockPO = g.getStockData_name("sh600000");
//        System.out.println(stockPO.getVolume()[0]+" "+stockPO.getPb()[0]+" "+stockPO.getAdj_price()[0]+" "+stockPO.getHigh()[0]+" "+stockPO.getLow()[0]+" "+stockPO.getOpen()[0]+" "+stockPO.getClose()[0]+" "+stockPO.getTurnover()[0]+" "+stockPO.getDate()[0]);

//        Calendar c = Calendar.getInstance();
//        c.add(c.DATE,1);
//        Date d = c.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String s = simpleDateFormat.format(d);
//        System.out.println(s);
        System.out.println(g.getAllInterestedStock().get(0).getDate()[g.getAllInterestedStock().get(0).getDate().length - 1]);
    }
}
