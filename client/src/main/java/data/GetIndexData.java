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
        String url = "http://121.41.106.89:8010/api/benchmark/hs300";
        String result = rdt.getData(url);
        String s1 = rdt.parseJSON(result, "data");
        String[] trading_info = rdt.parseJSON_array(s1, "trading_info");
        long[] volume = new long[trading_info.length];
        double[] high = new double[trading_info.length];
        double[] adj_price = new double[trading_info.length];
        double[] low = new double[trading_info.length];
        double[] close = new double[trading_info.length];
        double[] open = new double[trading_info.length];
        String[] date = new String[trading_info.length];
        IndexPO indexPO = new IndexPO(trading_info.length);
        for (int i = 0; i < trading_info.length; i++) {

            JSONObject jsonObject = JSONObject.fromObject(trading_info[i]);

            volume[i] = Long.parseLong(jsonObject.getString("volume"));
            indexPO.setVolume(volume);

            high[i] = Double.parseDouble(jsonObject.getString("high"));
            indexPO.setHigh(high);

            adj_price[i] = Double.parseDouble(jsonObject.getString("adj_price"));
            indexPO.setAdj_price(adj_price);

            low[i] = Double.parseDouble(jsonObject.getString("low"));
            indexPO.setLow(low);

            date[i] = jsonObject.getString("date");
            indexPO.setDate(date);

            close[i] = Double.parseDouble(jsonObject.getString("close"));
            indexPO.setClose(close);

            open[i] = Double.parseDouble(jsonObject.getString("open"));
            indexPO.setOpen(open);

        }
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
//        GetIndexData getIndexData = new GetIndexData();
//        System.out.println(getIndexData.getLatestIndexData().getName() + " " + getIndexData.getLatestIndexData().getVolume()[0] + " " + getIndexData.getLatestIndexData().getAdj_price()[0] + " " + getIndexData.getLatestIndexData().getHigh()[0] + " " + getIndexData.getLatestIndexData().getLow()[0] + " " + getIndexData.getLatestIndexData().getOpen()[0] + " " + getIndexData.getLatestIndexData().getClose()[0] + " " + getIndexData.getLatestIndexData().getDate()[0]);

    }
}
