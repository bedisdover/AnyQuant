package init;

import data.GetStockData;
import dataservice.GetStockDataService;

/**
 * Created by user on 2016/3/9.
 */
public class LaunchServer {
    public static void main(String[] args){
        GetStockDataService getStockDataService = new GetStockData();
        getStockDataService.getStockData_today_sh();
    }
}
