package bl;

import data.GetStockData;
import po.CurrentStockPO;
import vo.CurrentStockVO;

import java.io.IOException;

/**
 * Created by zcy on 2016/5/20.
 * 显示股票的实时数据信息
 */
public class ShowCurrentData {
    /**
     * @param id 股票代号
     * @return CurrentStockVO
     * @throws IOException
     * 得到指定股票的实时数据
     */
    public CurrentStockVO showCurrentData(String id) throws IOException {
        GetStockData getStockData = new GetStockData();
        CurrentStockPO currentStockPO = getStockData.getCurrentData(id);
        CurrentStockVO currentStockVO = new CurrentStockVO(currentStockPO);
        return currentStockVO;
    }
}
