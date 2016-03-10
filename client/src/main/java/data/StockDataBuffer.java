package data;


import bl.ShowStockData;
import observer.Observable;
import observer.Observer;
import po.StockPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/9.
 */
public class StockDataBuffer implements Observer, Observable{
    public static List<StockPO> stockPOs_sh = new ArrayList<StockPO>();
    public static List<StockPO> stockPOs_sz = new ArrayList<StockPO>();
    ShowStockData showStockData = new ShowStockData();

    public List<StockPO> getStockData_today_sh_buffer(){
        return stockPOs_sh;
    }


    @Override
    public void notify(Object o) {
        showStockData.update();
    }

    @Override
    public void update() {
        notify(showStockData);
    }
}
