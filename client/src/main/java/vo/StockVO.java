package vo;

import po.StockPO;

/**
 * Created by zcy on 2016/3/8.
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
     * 该股票对应日期内的？
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

    public StockVO(StockPO spo){
        id = spo.getId();
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
    }

    public String getId() {
        return id;
    }

    public long[] getVolume() {
        return volume;
    }

    public double[] getPb() {
        return pb;
    }

    public double[] getHigh() {
        return high;
    }

    public double[] getPe_ttm() {
        return pe_ttm;
    }

    public double[] getAdj_price() {
        return adj_price;
    }

    public double[] getLow() {
        return low;
    }

    public String[] getDate() {
        return date;
    }

    public double[] getClose() {
        return close;
    }

    public double[] getOpen() {
        return open;
    }

    public double[] getTurnover() {
        return turnover;
    }

    public String getName() {
        return name;
    }
}
