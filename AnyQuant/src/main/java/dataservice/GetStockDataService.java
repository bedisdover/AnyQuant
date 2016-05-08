package dataservice;

import po.StockPO;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2016/3/8.
 */
public interface GetStockDataService {
//    public List<StockPO> getStockData_today_sh();
//    public List<StockPO> getStockData_today_sz();
    public StockPO getStockData_name(String name, String date1, String date2) throws IOException;
}
