package data;

import dataservice.GetStockDataService;
import net.sf.json.JSONObject;
import po.StockPO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2016/3/7.
 */
public class GetStockData implements GetStockDataService{
    /**
     * 得到当天的所有股票数据
     * @return List<StockPO>
     */
    public List<StockPO> getStockData_today(){

        GetStockData getStockData = new GetStockData();
        String d = getStockData.getDateOfLatestData();//获得最新的股票数据对应的日期
        String[] time = d.split("-");
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
        c.add(c.DATE,1);
        Date date1 = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = simpleDateFormat.format(date1);//得到最新股票数据对应的日期的后一天

        ArrayList<StockPO> spo = new ArrayList<StockPO>();
        ReadDataTest rdt = new ReadDataTest();
        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sh";
        String result = rdt.getData(url);
        String[] info = rdt.parseJson(result, "data","link");//info数组存放了所有股票信息的网址
        for(int i=0;i<info.length;i++){
            if(i==7){
                continue;
            }
            String str = info[i]+"/?start="+d+"&end="+d1;
            String stockInfo = rdt.getData(str);
            String s1 = rdt.parseJSON(stockInfo,"data");
            String[] trading_info = rdt.parseJSON_array(s1,"trading_info");
            StockPO stockPO = new StockPO(1);
            JSONObject jsonObject = JSONObject.fromObject(trading_info[0]);

            long[] volume = new long[1];
            volume[0] = Long.parseLong(jsonObject.getString("volume"));
            stockPO.setVolume(volume);

            double[] pb = new double[1];
            pb[0] = Double.parseDouble(jsonObject.getString("pb"));
            stockPO.setPb(pb);

            double[] high = new double[1];
            high[0] = Double.parseDouble(jsonObject.getString("high"));
            stockPO.setHigh(high);

            double[] pe_ttm = new double[1];
            pe_ttm[0] = Double.parseDouble(jsonObject.getString("pe_ttm"));
            stockPO.setPe_ttm(pe_ttm);

            double[] adj_price = new double[1];
            adj_price[0] = Double.parseDouble(jsonObject.getString("adj_price"));
            stockPO.setAdj_price(adj_price);

            double[] low = new double[1];
            low[0] = Double.parseDouble(jsonObject.getString("low"));
            stockPO.setLow(low);

            String[] date = new String[1];
            date[0] = jsonObject.getString("date");
            stockPO.setDate(date);

            double[] close = new double[1];
            close[0] = Double.parseDouble(jsonObject.getString("close"));
            stockPO.setClose(close);

            double[] open = new double[1];
            open[0] = Double.parseDouble(jsonObject.getString("open"));
            stockPO.setOpen(open);

            double[] turnover = new double[1];
            turnover[0] = Double.parseDouble(jsonObject.getString("turnover"));
            stockPO.setTurnover(turnover);

            stockPO.setName(rdt.parseJSON(s1,"name"));

            spo.add(stockPO);
        }
        System.out.println(spo.get(0).getVolume()[0]+spo.get(0).getName()+spo.get(0).getAdj_price()[0]);
        return spo;
    }

    /**
     * 获得api中的最新股票数据对应的日期
     * @return String
     */
    private String getDateOfLatestData(){
        ReadDataTest rdt = new ReadDataTest();
        String url = "http://121.41.106.89:8010/api/stock/sh600000";
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1,"data");
        String[] dates =rdt.parseJson(s2,"trading_info","date");
        System.out.println(dates[dates.length-1]);
        return dates[dates.length-1];
    }

    public static void main(String[] args){
//        ReadDataTest rdt = new ReadDataTest();
//        String url = "http://121.41.106.89:8010/api/stock/sh600000/?start=2016-02-25&end=2016-02-26";
//        rdt.getData(url);
//        System.out.println();

        GetStockData g = new GetStockData();
//        g.getDateOfLatestData();
        g.getStockData_today();

//        Calendar c = Calendar.getInstance();
//        c.add(c.DATE,-1);
//        Date d = c.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String s = simpleDateFormat.format(d);
//        System.out.println(s);
    }
}
