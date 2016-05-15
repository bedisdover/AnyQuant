package bl;

import data.StockId2Name;
import vo.StockIDNameVO;

/**
 * Created by zcy on 2016/5/14.
 *
 */
public class ShowStockIDName {
    public StockIDNameVO getStockIdAndName(String id){
        StockIDNameVO stockIDNameVO = new StockIDNameVO();
        StockId2Name stockId2Name = new StockId2Name();
        stockIDNameVO.setId(id);
        stockIDNameVO.setName(stockId2Name.getStockName(id));
        return stockIDNameVO;
    }
}
