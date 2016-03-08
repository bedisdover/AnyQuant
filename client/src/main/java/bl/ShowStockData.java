package bl;

import data.GetStockData;
import dataservice.GetStockDataService;
import po.StockPO;
import vo.StockVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/8.
 */
public class ShowStockData {

    public List<StockVO> getLatestStockData(){
        GetStockDataService getStockDataService = new GetStockData();
        List<StockPO> a = getStockDataService.getStockData_today();
        List<StockVO> stockVOs = new ArrayList<StockVO>();
        for(int i=0;i<a.size();i++){
            StockVO stockVO = new StockVO(a.get(i));
            stockVOs.add(stockVO);
        }
        return stockVOs;
    }

    public static void main(String[] args){
        ShowStockData s = new ShowStockData();
        System.out.println();
        System.out.println(s.getLatestStockData().get(1).getVolume()[0]);
    }
}
