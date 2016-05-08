package dataservice.current;

import po.current.CurrentIndexPO;

/**
 * Created by 宋益明 on 16-4-5.
 * <p>
 * 大盘指数当前数据接口
 * 提供大盘指数当前数据
 */
public interface CurrentIndexService {

    /**
     * 获取深证指数当前数据
     *
     * @return 当前数据持久化对象
     * @throws Exception
     */
    CurrentIndexPO getCurrentIndexPO_SZ() throws Exception;

    /**
     * 获取上证指数当前数据
     *
     * @return 当前数据持久化对象
     * @throws Exception
     */
    CurrentIndexPO getCurrentIndexPO_SH() throws Exception;
}
