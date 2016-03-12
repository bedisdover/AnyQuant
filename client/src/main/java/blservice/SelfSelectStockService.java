package blservice;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by user on 2016/3/4.
 * <p>
 * 自选股票逻辑接口
 * 负责管理自选股票的添加与删除
 */
public interface SelfSelectStockService {

    /**
     * 关注股票
     *
     * @param id 股票代码
     */
    boolean addStock(String id) throws IOException;

    /**
     * 取消关注
     *
     * @param id 股票代码
     */
    boolean removeStock(String id) throws IOException;

    /**
     * 获得关注股票列表
     *
     * @return 股票代码列表的迭代器
     */
    Iterator<String> getFollowed();

    /**
     * 判断是否已关注股票
     *
     * @param id 股票列表
     * @return 若已关注,返回true,否则返回false
     */
    boolean exist(String id);
}
