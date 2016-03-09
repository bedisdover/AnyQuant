package bl;

import data.GetStockData;
import data.StockDataBuffer;
import dataservice.GetStockDataService;
import po.StockPO;
import presentation.panel.operation.PicturePanel;
import vo.StockVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/8. Created by user on 2016/3/8.
 */
public class ShowStockData {
    PicturePanel picturePanel = new PicturePanel();

    public List<StockVO> getLatestStockData_sh(){
        GetStockDataService getStockDataService = new GetStockData();
        List<StockPO> a = getStockDataService.getStockData_today_sh();
        List<StockVO> stockVOs = new ArrayList<StockVO>();
        for(int i=0;i<a.size();i++){
            StockVO stockVO = new StockVO(a.get(i));
            stockVOs.add(stockVO);
        }
        return stockVOs;
    }

    public List<StockVO> getLatestStockData_sz(){
        GetStockDataService getStockDataService = new GetStockData();
        List<StockPO> a = getStockDataService.getStockData_today_sz();
        List<StockVO> stockVOs = new ArrayList<StockVO>();
        for(int i=0;i<a.size();i++){
            StockVO stockVO = new StockVO(a.get(i));
            stockVOs.add(stockVO);
        }
        return stockVOs;
    }

    public List<StockVO> getLatestStockData_buffer(){
        List<StockPO> a = StockDataBuffer.stockPOs_sh;
        List<StockVO> stockVOs = new ArrayList<StockVO>();
        for(int i=0;i<a.size();i++){
            StockVO stockVO = new StockVO(a.get(i));
            stockVOs.add(stockVO);
        }
        return stockVOs;
    }

    public void notify(Object o) {
        picturePanel.update();
    }

    public void update() {
        notify(picturePanel);
    }

    public static void main(String[] args){
        ShowStockData s = new ShowStockData();
        System.out.println();
        System.out.println(s.getLatestStockData_sh().get(1).getVolume()[0]);
    }
}
