package bl;

import data.GetStockData;
import data.ReadData;
import dataservice.GetStockDataService;
import po.StockPO;
import vo.StockVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2016/3/8. Created by user on 2016/3/8.
 */
public class ShowStockData {

//    public List<StockVO> getLatestStockData(){
//        GetStockDataService getStockDataService = new GetStockData();
//        List<StockPO> a = getStockDataService.getStockData_today_sh();
//        List<StockVO> stockVOs = new ArrayList<StockVO>();
//        for(int i=0;i<a.size();i++){
//            StockVO stockVO = new StockVO(a.get(i));
//            stockVOs.add(stockVO);
//        }
//        return stockVOs;
//    }

//    public List<StockVO> getLatestStockData_sz(){
//        GetStockDataService getStockDataService = new GetStockData();
//        List<StockPO> a = getStockDataService.getStockData_today_sz();
//        List<StockVO> stockVOs = new ArrayList<StockVO>();
//        for(int i=0;i<a.size();i++){
//            StockVO stockVO = new StockVO(a.get(i));
//            stockVOs.add(stockVO);
//        }
//        return stockVOs;
//    }

    public StockVO getStockData(String id) throws IOException {
        GetStockDataService getStockDataService = new GetStockData();
        StockPO a = getStockDataService.getStockData_name(id,"2013-01-01",getDateOfLatestData());
        StockVO stockVO = new StockVO(a);
        return stockVO;
    }

    private String getDateOfLatestData() throws IOException {
        ReadData rdt = new ReadData();
        String url = "http://121.41.106.89:8010/api/stock/sh600000";
        String s1 = rdt.getData(url);
        String s2 = rdt.parseJSON(s1, "data");
        String[] dates = rdt.parseJson(s2, "trading_info", "date");
        return dates[dates.length - 1];
    }

    public static void main(String[] args) {
//        ShowStockData s = new ShowStockData();
//        System.out.println();
//        System.out.println(s.getLatestStockData().get(1).getVolume()[0]);
    }
}
