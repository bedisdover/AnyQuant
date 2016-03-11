package blservice;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by syz on 2016/3/11.
 *
 * 关注历史逻辑接口
 * 用于查看和管理关注历史
 */
public interface HistoryRecordStockService {
    /**
     * 添加股票到历史关注
     *
     * @param id 股票代码
     */
    void addStock(String id) throws IOException;

    /**
     * 从历史关注中删除股票
     *
     * @param id 股票代码
     */
    void removeStock(String id) throws IOException;

    /**
     * 获得历史关注股票列表
     *
     * @return 股票代码列表的迭代器
     */
    Iterator<String> getFollowed();

    /**
     * 判断是否已在历史关注列表中
     *
     * @param id 股票列表
     * @return 若已关注,返回true,否则返回false
     */
    boolean exist(String id);

}
