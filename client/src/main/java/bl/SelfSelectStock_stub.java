package bl;

import blservice.SelfSelectStockService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-3-8.
 *
 * 自选股桩程序
 */
public class SelfSelectStock_stub implements SelfSelectStockService {

    /**
     * 关注股票
     *
     * @param id 股票代码
     */
    public void addStock(String id) {

    }

    /**
     * 取消关注
     *
     * @param id 股票代码
     */
    public void removeStock(String id) {

    }

    /**
     * 获得关注股票列表
     *
     * @return 股票代码列表的迭代器
     */
    public Iterator<String> getFollowed() {
        List<String> list = new ArrayList<String>();

        list.add("sh600008");
        list.add("sh600005");

        return list.iterator();
    }

    /**
     * 判断是否已关注股票
     *
     * @param id 股票列表
     * @return 若已关注, 返回true, 否则返回false
     */
    public boolean exist(String id) {
        return false;
    }
}
