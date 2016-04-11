package vo;

import po.IndexPO;

/**
 * Created by user on 2016/3/9.
 */
public class IndexVO {
    private String name;
    private long[] volume;
    private double[] high;
    private double[] adj_price;
    private double[] low;
    private String[] date;
    private double[] close;
    private double[] open;
    /**
     * 大盘对应日期内的涨跌幅
     */
    private double[] increase_decreaseRate;
    /**
     * 大盘对应日期内的涨跌额
     */
    private double[] increase_decreaseNum;

    public IndexVO(IndexPO indexPO){
        volume = indexPO.getVolume();
        high = indexPO.getHigh();
        adj_price = indexPO.getAdj_price();
        low = indexPO.getLow();
        date = indexPO.getDate();
        close = indexPO.getClose();
        open = indexPO.getOpen();
        name = indexPO.getName();
        increase_decreaseRate = indexPO.getIncrease_decreaseRate();
        increase_decreaseNum = indexPO.getIncrease_decreaseNum();
    }


    public long[] getVolume() {
        return volume;
    }


    public double[] getHigh() {
        return high;
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


    public String getName() {
        return name;
    }

    public double[] getIncrease_decreaseRate() {
        return increase_decreaseRate;
    }

    public double[] getIncrease_decreaseNum() {
        return increase_decreaseNum;
    }
}
