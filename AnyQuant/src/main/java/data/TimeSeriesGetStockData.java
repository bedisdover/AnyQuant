package data;

import dataservice.GetStockDataService;
import dataservice.TimeSeriesGetStockDataService;
import po.StockPO;

import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo2014 on 2016/4/9.
 */
public class TimeSeriesGetStockData implements TimeSeriesGetStockDataService {

    @Override
    public List<StockPO> getStockData() {
        return null;
    }

    private String getDateOfLatestData() throws IOException {
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/sh600000";
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] dates = rdt.parseJson(s2, "trading_info", "date");
        return dates[dates.length - 1];
    }
}
