package bl;

import blservice.SortStockService;
import data.GetStockData;
import data.ReadData;
import po.StockPO;
import vo.StockVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/3/17.
 *
 */
public class SortStock implements SortStockService{

    /**
     * 股票列表
     */
    private List<StockVO> stockVOs;

    public SortStock() throws IOException {
        stockVOs = new ArrayList<>();

        loadStockList();
    }

    /**
     * 加载股票列表
     */
    private void loadStockList() throws IOException {
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
    public List<StockVO> increase_sort() throws IOException {
        List<StockVO> list = new ArrayList<StockVO>();
        double[] increaseRate = new double[stockVOs.size()];
        int length = stockVOs.get(0).getDate().length;
        for (int i = 0; i < increaseRate.length; i++) {
            increaseRate[i] = stockVOs.get(i).getIncrease_decreaseRate()[length-1];
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (increaseRate[j] > increaseRate[k]) {
                    k = j;
                }
            }
            list.add(stockVOs.get(k));
            increaseRate[k] = -999;
        }
        return list;
    }

    /**
     * 将股票按跌幅从大到小排列
     *
     * @return List<StockVO>
     */
    public List<StockVO> decrease_sort() throws IOException {
        List<StockVO> list = new ArrayList<StockVO>();
        double[] increaseRate = new double[stockVOs.size()];
        int length = stockVOs.get(0).getDate().length;
        for (int i = 0; i < increaseRate.length; i++) {
            increaseRate[i] = stockVOs.get(i).getIncrease_decreaseRate()[length-1];
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (increaseRate[j] < increaseRate[k]) {
                    k = j;
                }
            }
            list.add(stockVOs.get(k));
            increaseRate[k] = 999;
        }
        return list;
    }

    /**
     * 将股票按成交量从大到小排列
     *
     * @return List<StockVO>
     */
    public List<StockVO> volume_sort() throws IOException {
        List<StockVO> list = new ArrayList<StockVO>();
        double[] volume = new double[stockVOs.size()];
        int length = stockVOs.get(0).getDate().length;
        for (int i = 0; i < volume.length; i++) {
            volume[i] = stockVOs.get(i).getVolume()[length-1];
        }
        for (int i = 0; i < stockVOs.size(); i++) {
            int k = 0;
            for (int j = 0; j < stockVOs.size(); j++) {
                if (volume[j] > volume[k]) {
                    k = j;
                }
            }
           list.add(stockVOs.get(k));
            volume[k] = -1;
        }
        return list;
    }

    private double calculateIncreaseRate(String stockID) throws IOException {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list=" + stockID);
        String[] strings = s.split(",");
        double close_yesterday = Double.parseDouble(strings[2]);
        double currentPrice = Double.parseDouble(strings[3]);
        return (currentPrice - close_yesterday) / close_yesterday;
    }

    private double calculateVolume(String stockID) throws IOException {
        ReadData readData = new ReadData();
        String s = readData.getCurrentData("http://hq.sinajs.cn/list=" + stockID);
        String[] strings = s.split(",");
        return Double.parseDouble(strings[8]) / 100;
    }

    public static void main(String[] args){
        try {
            SortStock sortStock = new SortStock();
            int length = sortStock.stockVOs.get(0).getDate().length;
            List<StockVO> stockVOs = sortStock.increase_sort();
            for(int i=0;i<stockVOs.size();i++){
                System.out.println(stockVOs.get(i).getId()+" "+stockVOs.get(i).getIncrease_decreaseRate()[length-1]);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
