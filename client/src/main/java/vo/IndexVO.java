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

    public IndexVO(IndexPO indexPO){
        volume = indexPO.getVolume();
        high = indexPO.getHigh();
        adj_price = indexPO.getAdj_price();
        low = indexPO.getLow();
        date = indexPO.getDate();
        close = indexPO.getClose();
        open = indexPO.getOpen();
        name = indexPO.getName();
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

}
