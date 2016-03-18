package bl;

import data.GetStockData;
import data.ReadData;
import po.StockPO;
import vo.StockVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/17.
 */
public class SortStock {

    /**
     * 股票列表
     */
    private List<StockVO> stockVOs;

    public SortStock() {
        stockVOs = new ArrayList<>();

        loadStockList();
    }

    /**
     * 加载股票列表
     */
    private void loadStockList() {
        List<StockPO> stockPOs = new GetStockData().getAllInterestedStock();

        for (StockPO stockPO : stockPOs) {
            stockVOs.add(new StockVO(stockPO));
        }
    }

    public List<StockVO> getStockVOs() {
        return stockVOs;
    }

    /**
     * 将股票按涨幅从大到小排列
     *
     * @return List<StockVO>
     */
    public List<StockVO> increase_sort() {
        double[] increaseRate = new double[stockVOs.size()];
        for (int i = 0; i < increaseRate.length; i++) {
            increaseRate[i] = calculateIncreaseRate(stockVOs.get(i).getId());
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (increaseRate[j] > increaseRate[k]) {
                    k = j;
                }
            }
            stockVOs.add(stockVOs.get(k));
            increaseRate[k] = -999;
        }
        return stockVOs;
    }

    /**
     * 将股票按跌幅从大到小排列
     *
     * @return List<StockVO>
     */
    public List<StockVO> decrease_sort() {
        double[] increaseRate = new double[stockVOs.size()];
        for (int i = 0; i < increaseRate.length; i++) {
            increaseRate[i] = calculateIncreaseRate(stockVOs.get(i).getId());
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (increaseRate[j] < increaseRate[k]) {
                    k = j;
                }
            }
            stockVOs.add(stockVOs.get(k));
            increaseRate[k] = 999;
        }
        return stockVOs;
    }

    /**
     * 将股票按成交量从大到小排列
     *
     * @return List<StockVO>
     */
    public List<StockVO> volume_sort() {
        double[] volume = new double[stockVOs.size()];
        for (int i = 0; i < volume.length; i++) {
            volume[i] = calculateVolume(stockVOs.get(i).getId());
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (volume[j] > volume[k]) {
                    k = j;
                }
            }
            stockVOs.add(stockVOs.get(k));
            volume[k] = -1;
        }
        return stockVOs;
    }

    private double calculateIncreaseRate(String stockID) {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list" + stockID);
        String[] strings = s.split(",");
        double close_yesterday = Double.parseDouble(strings[2]);
        double currentPrice = Double.parseDouble(strings[3]);
        return (currentPrice - close_yesterday) / close_yesterday;
    }

    private double calculateVolume(String stockID) {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list" + stockID);
        String[] strings = s.split(",");
        return Double.parseDouble(strings[8]) / 100;
    }
}
