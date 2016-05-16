package vo;

import data.GetStockData;
import data.StockId2Name;
import po.StockPO;
import po.Transfer;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by zcy on 2016/3/8.
 *
 */
public class StockVO {
    /**
     * 该股票的名称
     */
    private String name;
    /**
     * 该股票的代号
     */
    private String id;
    /**
     * 该股票对应日期内的成交量
     */
    private long[] volume;
    /**
     * 该股票对应日期内的平均市净率
     */
    private double[] pb;
    /**
     * 该股票对应日期内的最高价
     */
    private double[] high;
    /**
     * 该股票对应日期内的市盈率
     */
    private double[] pe_ttm;
    /**
     * 该股票对应日期内的后复权价
     */
    private double[] adj_price;
    /**
     * 该股票对应日期内的最低价
     */
    private double[] low;
    /**
     * 数据的日期区间
     */
    private String[] date;
    /**
     * 该股票对应日期内的收盘价
     */
    private double[] close;
    /**
     * 该股票对应日期内的开盘价
     */
    private double[] open;
    /**
     * 该股票对应日期内的周转率
     */
    private double[] turnover;
    /**
     * 该股票对应日期内的涨跌幅
     */
    private double[] increase_decreaseRate;
    /**
     * 该股票对应日期内的涨跌额
     */
    private double[] increase_decreaseNum;

    public StockVO(StockPO spo) throws IOException {
        id = spo.getId();
        name = StockId2Name.getStockName(id);
        volume = spo.getVolume();
        pb = spo.getPb();
        high = spo.getHigh();
        pe_ttm = spo.getPe_ttm();
        adj_price = spo.getAdj_price();
        low = spo.getLow();
        date = spo.getDate();
        close = spo.getClose();
        open = spo.getOpen();
        turnover = spo.getTurnover();
        increase_decreaseRate = spo.getIncrease_decreaseRate();
        increase_decreaseNum = spo.getIncrease_decreaseNum();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long[] getVolume() {
        return volume;
    }

    public void setVolume(long[] volume) {
        this.volume = volume;
    }

    public double[] getPb() {
        return pb;
    }

    public void setPb(double[] pb) {
        this.pb = pb;
    }

    public double[] getHigh() {
        return high;
    }

    public void setHigh(double[] high) {
        this.high = high;
    }

    public double[] getPe_ttm() {
        return pe_ttm;
    }

    public void setPe_ttm(double[] pe_ttm) {
        this.pe_ttm = pe_ttm;
    }

    public double[] getAdj_price() {
        return adj_price;
    }

    public void setAdj_price(double[] adj_price) {
        this.adj_price = adj_price;
    }

    public double[] getLow() {
        return low;
    }

    public void setLow(double[] low) {
        this.low = low;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public double[] getClose() {
        return close;
    }

    public void setClose(double[] close) {
        this.close = close;
    }

    public double[] getOpen() {
        return open;
    }

    public void setOpen(double[] open) {
        this.open = open;
    }

    public double[] getTurnover() {
        return turnover;
    }

    public void setTurnover(double[] turnover) {
        this.turnover = turnover;
    }

    public double[] getIncrease_decreaseRate() {
        return increase_decreaseRate;
    }

    public void setIncrease_decreaseRate(double[] increase_decreaseRate) {
        this.increase_decreaseRate = increase_decreaseRate;
    }

    public double[] getIncrease_decreaseNum() {
        return increase_decreaseNum;
    }

    public void setIncrease_decreaseNum(double[] increase_decreaseNum) {
        this.increase_decreaseNum = increase_decreaseNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
