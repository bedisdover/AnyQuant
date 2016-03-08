package blservice;

import po.StockPO;

import java.util.Iterator;

/**
 * Created by user on 2016/3/4.
 */
public interface SelfSelectStockService {
    public void addStock(String id);

    public void removeStock(String id);

    /**
     * 获得关注股票列表的迭代器
     * @return
     */
    Iterator<StockPO> getFollowed();
}
