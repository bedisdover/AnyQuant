package data;

import dataservice.GetIndexDataService;
import net.sf.json.JSONObject;
import po.IndexPO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2016/3/9.
 */
public class GetIndexData implements GetIndexDataService{

    /**
     * 得到最新的大盘数据
     * @return IndexPO
     */
    public IndexPO getLatestIndexData(){
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/benchmark/hs300";
        String result = rdt.getData(url);
        String s1 = rdt.parseJSON(result,"data");
        String[] trading_info = rdt.parseJSON_array(s1,"trading_info");
        JSONObject jsonObject = JSONObject.fromObject(trading_info[trading_info.length-1]);
        IndexPO indexPO = new IndexPO(1);

        long[] volume = new long[1];
        volume[0] = Long.parseLong(jsonObject.getString("volume"));
        indexPO.setVolume(volume);

        double[] high = new double[1];
        high[0] = Double.parseDouble(jsonObject.getString("high"));
        indexPO.setHigh(high);

        double[] adj_price = new double[1];
        adj_price[0] = Double.parseDouble(jsonObject.getString("adj_price"));
        indexPO.setAdj_price(adj_price);

        double[] low = new double[1];
        low[0] = Double.parseDouble(jsonObject.getString("low"));
        indexPO.setLow(low);

        String[] date = new String[1];
        date[0] = jsonObject.getString("date");
        indexPO.setDate(date);

        double[] close = new double[1];
        close[0] = Double.parseDouble(jsonObject.getString("close"));
        indexPO.setClose(close);

        double[] open = new double[1];
        open[0] = Double.parseDouble(jsonObject.getString("open"));
        indexPO.setOpen(open);

        JSONObject jsonObject1 = JSONObject.fromObject(s1);
        indexPO.setName(jsonObject1.getString("name"));

        return indexPO;
    }

    /**
     * 获得api中的最新大盘数据对应的日期
     * @return String
     */
    private String getDateOfLatestData(){
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/benchmark/all";
        String s1 = rdt.getData(url);
        String[] info = rdt.parseJson(s1,"data","link");
        String s2 = info[0];
        String s3 = rdt.getData(s2);
        String s4 = rdt.parseJSON(s3,"data");
        String[] dates =rdt.parseJson(s4,"trading_info","date");
        return dates[dates.length-1];
    }

    public static void main(String[] args){
//        ReadData rdt = new ReadData();
//        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start=2016-01-06&end=2015-03-07";
//        String result = rdt.getData(url);System.out.println(result);
//        String[] info = rdt.parseJson(result,"data","link");
//        String str = info[0];
        GetIndexData getIndexData = new GetIndexData();
        System.out.println(getIndexData.getLatestIndexData().getName());

    }
}
