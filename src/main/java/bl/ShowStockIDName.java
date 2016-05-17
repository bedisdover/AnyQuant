package bl;

import data.StockId2Name;
import database.GetStockData_DB;
import vo.StockIDNameVO;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @return List<StockIDNameVO>
     * 得到所有银行股的代号和名称
     */
    public List<StockIDNameVO> getAllStockIdAndName(){
        List<StockIDNameVO> stockIDNameVOList = new ArrayList<>();
        for(int i=0;i< GetStockData_DB.stock_id.length;i++){
            StockIDNameVO stockIDNameVO = getStockIdAndName(GetStockData_DB.stock_id[i]);
            stockIDNameVOList.add(stockIDNameVO);
        }
        return stockIDNameVOList;
    }
}
