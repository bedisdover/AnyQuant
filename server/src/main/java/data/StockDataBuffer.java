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
    public ShowStockData showStockData = new ShowStockData();


    public void notify(Object o) {
        showStockData.update();
    }


    public void update() {
        notify(showStockData);
    }
}
