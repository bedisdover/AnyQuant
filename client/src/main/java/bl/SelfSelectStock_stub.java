package bl;

import data.GetStockData;
import po.StockPO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-8.
 *
 * 自选股桩程序
 */
public class SelfSelectStock_stub {

    public void addStock(String id) {

    }

    public void removeStock(String id) {

    }

    /**
     * 获得关注股票列表的迭代器
     *
     * @return
     */
    public Iterator<StockPO> getFollowed() {
        GetStockData getStockData = new GetStockData();
        List<StockPO> stocks = new ArrayList<StockPO>();
        stocks.add(getStockData.getStockData_name(""));

        return stocks.iterator();
    }
}
