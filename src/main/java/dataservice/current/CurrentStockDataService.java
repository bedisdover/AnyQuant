package dataservice.current;

import po.current.CurrentStockPO;

/**
 * Created by 宋益明 on 16-4-5.
 * <p>
 * 股票当前信息数据接口
 * 提供股票当前数据
 */
public interface CurrentStockDataService {

    /**
     * 根据股票代码获取股票当前数据
     *
     * @param id 股票代码
     * @return 股票当前数据持久化对象
     * @throws Exception
     */
    CurrentStockPO getCurrentStockPO(String id) throws Exception;

    /**
     * 根据股票代码获取股票当前数据（美股）
     *
     * @param id 股票代码
     * @return 股票数据持久化对象
     * @throws Exception
     */
    CurrentStockPO getCurrentStockPO_US(String id) throws Exception;
}
