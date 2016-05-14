package data;

import net.sf.json.JSONObject;
import org.junit.Test;
import po.StockPO;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class ReadDataTest {
    @Test
    public void readDataTest() throws IOException {
        ReadData readData = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/sh600015/?start=2016-05-12&end=2016-05-13";
        String s1 = readData.getData(url);
        String s2 = readData.parseJSON(s1, "data");
        String[] trading_info = readData.parseJSON_array(s2, "trading_info");
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
        JSONObject jsonObject = JSONObject.fromObject(trading_info[0]);

        volume[0] = Long.parseLong(jsonObject.getString("volume"));
        pb[0] = Double.parseDouble(jsonObject.getString("pb"));
        high[0] = Double.parseDouble(jsonObject.getString("high"));
        pe_ttm[0] = Double.parseDouble(jsonObject.getString("pe_ttm"));
        adj_price[0] = Double.parseDouble(jsonObject.getString("adj_price"));
        low[0] = Double.parseDouble(jsonObject.getString("low"));
        date[0] = jsonObject.getString("date");
        close[0] = Double.parseDouble(jsonObject.getString("close"));
        open[0] = Double.parseDouble(jsonObject.getString("open"));
        turnover[0] = Double.parseDouble(jsonObject.getString("turnover"));

        stockPO.setId("sh600015");
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
        assertEquals("sh600015",stockPO.getId());
        assertEquals("2016-05-12",stockPO.getDate()[0]);
        assertEquals(15260500,stockPO.getVolume()[0]);
        assertEquals(10.08,stockPO.getHigh()[0],0.01);
        assertEquals(10.02,stockPO.getOpen()[0],0.01);
        assertEquals(10.02,stockPO.getClose()[0],0.01);
        assertEquals(9.93,stockPO.getLow()[0],0.01);

    }
}