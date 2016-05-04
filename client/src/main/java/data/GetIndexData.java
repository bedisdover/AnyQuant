package data;

import dataservice.GetIndexDataService;
import net.sf.json.JSONObject;
import po.IndexPO;

import java.io.IOException;

/**
 * Created by user on 2016/3/9.
 */
public class GetIndexData implements GetIndexDataService {

    /**
     * 得到最新的大盘数据
     *
     * @return IndexPO
     */
    public IndexPO getLatestIndexData() throws IOException {
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start=2012-11-01&end="+getDateOfLatestData();
        String result = rdt.getData(url);
        String s1 = rdt.parseJSON(result, "data");
        String[] trading_info = rdt.parseJSON_array(s1, "trading_info");

        int num=0;
        for(int i=0;i<trading_info.length;i++){
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            if(jsonObject.getString("volume").equals("0")){
                num++;
            }
        }//计算无用数据的个数
        long[] volume = new long[trading_info.length-num];
        double[] high = new double[trading_info.length-num];
        double[] adj_price = new double[trading_info.length-num];
        double[] low = new double[trading_info.length-num];
        double[] close = new double[trading_info.length-num];
        double[] open = new double[trading_info.length-num];
        String[] date = new String[trading_info.length-num];
        double[] increase_decreaseRate = new double[trading_info.length-num];
        double[] increase_decreaseNum = new double[trading_info.length-num];
        IndexPO indexPO = new IndexPO(trading_info.length-num);
        int k = 0;
        for (int i = 0; i < trading_info.length; i++) {

            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);

            if(jsonObject.getString("volume").equals("0")){
                continue;
            }

            volume[k] = Long.parseLong(jsonObject.getString("volume"));
            high[k] = Double.parseDouble(jsonObject.getString("high"));
            adj_price[k] = Double.parseDouble(jsonObject.getString("adj_price"));
            low[k] = Double.parseDouble(jsonObject.getString("low"));
            date[k] = jsonObject.getString("date");
            close[k] = Double.parseDouble(jsonObject.getString("close"));
            open[k] = Double.parseDouble(jsonObject.getString("open"));

            if(k>=1){
                increase_decreaseRate[k] = (close[k]-close[k-1])/close[k-1];
                increase_decreaseNum[k] = close[k]-close[k-1];
            }

            k++;
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
        JSONObject jsonObject1 = JSONObject.fromObject(s1);
        indexPO.setName(jsonObject1.getString("name"));

        return indexPO;
    }

    /**
     * @param date1
     * @param date2
     * @return IndexPO
     * @throws IOException
     * date1和date2的格式是yyyyMMdd
     */
    public IndexPO getIndexDataBetween(String date1,String date2) throws IOException {
        ReadData rdt = new ReadData();
        String url;
        if(date1.charAt(4)!='-'){
            String d1 = date1.substring(0,4)+"-"+date1.substring(4,6)+"-"+date1.substring(6);//给date1加上-
            String d2 = date2.substring(0,4)+"-"+date2.substring(4,6)+"-"+date2.substring(6);//给date2加上-
            url = "http://121.41.106.89:8010/api/benchmark/hs300?start="+d1+"&end="+d2;
        }
        else{
            url = "http://121.41.106.89:8010/api/benchmark/hs300?start="+date1+"&end="+date2;
        }
        String result = rdt.getData(url);
        String s1 = rdt.parseJSON(result, "data");
        String[] trading_info = rdt.parseJSON_array(s1, "trading_info");

        int num=0;
        for(int i=0;i<trading_info.length;i++){
            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);
            if(jsonObject.getString("volume").equals("0")){
                num++;
            }
        }//计算无用数据的个数
        long[] volume = new long[trading_info.length-num];
        double[] high = new double[trading_info.length-num];
        double[] adj_price = new double[trading_info.length-num];
        double[] low = new double[trading_info.length-num];
        double[] close = new double[trading_info.length-num];
        double[] open = new double[trading_info.length-num];
        String[] date = new String[trading_info.length-num];
        double[] increase_decreaseRate = new double[trading_info.length-num];
        double[] increase_decreaseNum = new double[trading_info.length-num];
        IndexPO indexPO = new IndexPO(trading_info.length-num);
        int k = 0;
        for (int i = 0; i < trading_info.length; i++) {

            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);

            if(jsonObject.getString("volume").equals("0")){
                continue;
            }

            volume[k] = Long.parseLong(jsonObject.getString("volume"));
            high[k] = Double.parseDouble(jsonObject.getString("high"));
            adj_price[k] = Double.parseDouble(jsonObject.getString("adj_price"));
            low[k] = Double.parseDouble(jsonObject.getString("low"));
            date[k] = jsonObject.getString("date");
            close[k] = Double.parseDouble(jsonObject.getString("close"));
            open[k] = Double.parseDouble(jsonObject.getString("open"));

            if(k>=1){
                increase_decreaseRate[k] = (close[k]-close[k-1])/close[k-1];
                increase_decreaseNum[k] = close[k]-close[k-1];
            }

            k++;
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
        JSONObject jsonObject1 = JSONObject.fromObject(s1);
        indexPO.setName(jsonObject1.getString("name"));

        return indexPO;
    }
    /**
     * 获得api中的最新大盘数据对应的日期
     *
     * @return String
     */
    private String getDateOfLatestData() throws IOException {
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/benchmark/all";
        String s1 = rdt.getData(url);
        String[] info = rdt.parseJson(s1, "data", "link");
        String s2 = info[0];
        String s3 = rdt.getData(s2);
        String s4 = rdt.parseJSON(s3, "data");
        String[] dates = rdt.parseJson(s4, "trading_info", "date");
        return dates[dates.length - 1];
    }

    public static void main(String[] args) {
//        ReadData rdt = new ReadData();
//        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start=2016-01-06&end=2015-03-07";
//        String result = rdt.getData(url);System.out.println(result);
//        String[] info = rdt.parseJson(result,"data","link");
//        String str = info[0];
        GetIndexData getIndexData = new GetIndexData();
        try {
            getIndexData.getLatestIndexData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(getIndexData.getLatestIndexData().getName() + " " + getIndexData.getLatestIndexData().getVolume()[0] + " " + getIndexData.getLatestIndexData().getAdj_price()[0] + " " + getIndexData.getLatestIndexData().getHigh()[0] + " " + getIndexData.getLatestIndexData().getLow()[0] + " " + getIndexData.getLatestIndexData().getOpen()[0] + " " + getIndexData.getLatestIndexData().getClose()[0] + " " + getIndexData.getLatestIndexData().getDate()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
