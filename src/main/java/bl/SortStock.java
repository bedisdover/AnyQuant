package bl;

import blservice.SortStockService;
import data.GetStockData;
import data.ReadData;
import database.GetStockData_DB;
import po.StockPO;
import vo.StockVO;

import java.io.IOException;
import java.sql.SQLException;
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

    public SortStock() throws IOException, SQLException {
        stockVOs = new ArrayList<>();

        loadStockList();
    }

    /**
     * 加载股票列表
     */
    private void loadStockList() throws IOException, SQLException {
        GetStockData_DB getStockData_db = new GetStockData_DB();
        List<StockPO> stockPOs = getStockData_db.getLatestStock();
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
        for (int i = 0; i < increaseRate.length; i++) {
            increaseRate[i] = stockVOs.get(i).getIncrease_decreaseRate()[0];
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
