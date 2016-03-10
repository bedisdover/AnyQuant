package init;

import data.GetStockData;
import data.StockDataBuffer;
import dataservice.GetStockDataService;

/**
 * Created by user on 2016/3/9.
 */
public class LaunchServer {
    public static void main(String[] args){
        System.out.println("Ready...");
        GetStockDataService getStockDataService = new GetStockData();
        getStockDataService.getStockData_today_sh();
//        getStockDataService.getStockData_today_sz();
    }

    public static void init(){
        GetStockDataService getStockDataService = new GetStockData();
        getStockDataService.getStockData_today_sh();
//        getStockDataService.getStockData_today_sz();
    }
}
