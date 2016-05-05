package dataservice;

import po.StockPO;

import java.util.List;

/**
 * Created by syz on 2016/4/9.
 */
public interface TimeSeriesGetStockDataService {
    public List<StockPO> getStockData();
}
