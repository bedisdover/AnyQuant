package bl;

import data.ReadData;
import vo.StockVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/17.
 */
public class SortStock {
    /**
     * 将股票按涨幅从大到小排列
     * @param stockVOs
     * @return List<StockVO>
     */
    public List<StockVO> increase_sort(List<StockVO> stockVOs){
        List<StockVO> stockVOs1 = new ArrayList<StockVO>();
        double[] increaseRate = new double[stockVOs.size()];
        for(int i=0;i<increaseRate.length;i++){
            increaseRate[i] = calculateIncreaseRate(stockVOs.get(i).getId());
        }
        for(int i=0;i<stockVOs.size();i++){
            int k = 0;
            for(int j=0;j<stockVOs.size();j++){
                if(increaseRate[j]>increaseRate[k]){
                    k = j;
                }
            }
            stockVOs1.add(stockVOs.get(k));
            increaseRate[k] = -999;
        }
        return stockVOs1;
    }

    /**
     * 将股票按跌幅从大到小排列
     * @param stockVOs
     * @return List<StockVO>
     */
    public List<StockVO> decrease_sort(List<StockVO> stockVOs){
        List<StockVO> stockVOs1 = new ArrayList<StockVO>();
        double[] increaseRate = new double[stockVOs.size()];
        for(int i=0;i<increaseRate.length;i++){
            increaseRate[i] = calculateIncreaseRate(stockVOs.get(i).getId());
        }
        for(int i=0;i<stockVOs.size();i++){
            int k = 0;
            for(int j=0;j<stockVOs.size();j++){
                if(increaseRate[j]<increaseRate[k]){
                    k = j;
                }
            }
            stockVOs1.add(stockVOs.get(k));
            increaseRate[k] = 999;
        }
        return stockVOs1;
    }

    private double calculateIncreaseRate(String stockID){
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list"+stockID);
        String[] strings = s.split(",");
        double close_yesterday = Double.parseDouble(strings[2]);
        double currentPrice = Double.parseDouble(strings[3]);
        return (currentPrice-close_yesterday)/close_yesterday;
    }
}
