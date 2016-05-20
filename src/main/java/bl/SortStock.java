package bl;

import blservice.SortStockService;
import data.GetStockData;
import data.ReadData;
import database.GetStockData_DB;
import po.StockPO;
import vo.StockVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        List<StockPO> stockPOs = new GetStockData_DB().getAllStock();

        for (StockPO stockPO : stockPOs) {
            stockVOs.add(new StockVO(stockPO));
        }
    }

    /**
     * @param n 1表示按涨幅榜排序，2表示按成交量榜排序
     * @return List<StockVO>
     * @throws IOException
     * 得到排好序之后的股票列表
     */
    public List<StockVO> getResultList(int n) throws IOException {
        List<StockVO> result = new ArrayList<>();
        int length;
        long[] volume = new long[1];
        double[] pb = new double[1];
        double[] high = new double[1];
        double[] pe_ttm = new double[1];
        double[] adj_price = new double[1];
        double[] low = new double[1];
        String[] date = new String[1];
        double[] close = new double[1];
        double[] open = new double[1];
        double[] turnover = new double[1];
        if(n==1){
            List<StockVO> list = increase_sort();
            length = list.get(0).getDate().length;
            for(int i=0;i<list.size();i++){
                StockPO stockPO = new StockPO(1);
                volume[0] = list.get(i).getVolume()[length-1];
                pb[0] = list.get(i).getPb()[length-1];
                high[0] = list.get(i).getHigh()[length-1];
                pe_ttm[0] = list.get(i).getPe_ttm()[length-1];
                adj_price[0] = list.get(i).getAdj_price()[length-1];
                low[0] = list.get(i).getLow()[length-1];
                date[0] = list.get(i).getDate()[length-1];
                close[0] = list.get(i).getClose()[length-1];
                open[0] = list.get(i).getOpen()[length-1];
                turnover[0] = list.get(i).getTurnover()[length-1];
                stockPO.setId(list.get(i).getId());
                stockPO.setVolume(volume);
                stockPO.setPb(pb);
                stockPO.setHigh(high);
                stockPO.setPe_ttm(pe_ttm);
                stockPO.setAdj_price(adj_price);
                stockPO.setLow(low);
                stockPO.setDate(date);
                stockPO.setClose(close);
                stockPO.setOpen(open);
                stockPO.setTurnover(turnover);
                StockVO stockVO = new StockVO(stockPO);
                list.add(stockVO);
            }
            return list;
        }
        else{
            List<StockVO> list = volume_sort();
            length = list.get(0).getDate().length;
            for(int i=0;i<list.size();i++){
                StockPO stockPO = new StockPO(1);
                volume[0] = list.get(i).getVolume()[length-1];
                pb[0] = list.get(i).getPb()[length-1];
                high[0] = list.get(i).getHigh()[length-1];
                pe_ttm[0] = list.get(i).getPe_ttm()[length-1];
                adj_price[0] = list.get(i).getAdj_price()[length-1];
                low[0] = list.get(i).getLow()[length-1];
                date[0] = list.get(i).getDate()[length-1];
                close[0] = list.get(i).getClose()[length-1];
                open[0] = list.get(i).getOpen()[length-1];
                turnover[0] = list.get(i).getTurnover()[length-1];
                stockPO.setVolume(volume);
                stockPO.setPb(pb);
                stockPO.setHigh(high);
                stockPO.setPe_ttm(pe_ttm);
                stockPO.setAdj_price(adj_price);
                stockPO.setLow(low);
                stockPO.setDate(date);
                stockPO.setClose(close);
                stockPO.setOpen(open);
                stockPO.setTurnover(turnover);
                StockVO stockVO = new StockVO(stockPO);
                stockPO.setId(list.get(i).getId());
                list.add(stockVO);
            }
            return list;
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
        for (int i = 0; i < increaseRate.length; i++) {
            int length = stockVOs.get(i).getIncrease_decreaseRate().length;
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
        for (int i = 0; i < increaseRate.length; i++) {
            int length = stockVOs.get(i).getDate().length;
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

        for (int i = 0; i < volume.length; i++) {
            int length = stockVOs.get(i).getDate().length;
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


}
