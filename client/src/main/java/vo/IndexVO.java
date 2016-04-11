package vo;

import po.IndexPO;

import java.math.BigDecimal;

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

    /**
     * 将成交量进行数量级换算为万/亿后，以字符串形式返回
     *
     * @return 换算后的成交量
     */
    public String getDealNum() {
        StringBuffer temp = new StringBuffer(volume[volume.length - 1] + "");
        int length = temp.length();

        if (length >= 9) {//9位数，数量级为亿,保留到百万位
            temp.delete(temp.length() - 7, temp.length() - 1);
            temp.insert(temp.length() - 2, '.');
            temp.append("亿手");
        } else if (length >= 5) {//5位数，数量级为万，保留到百位
            temp.delete(temp.length() - 3, temp.length() - 1);
            temp.insert(temp.length() - 2, '.');
            temp.append("万手");
        } else {
            temp.append("手");
        }

        return temp.toString();
    }

    /**
     * 将成交额进行数量级换算为万/亿后，以字符串形式返回
     *
     * @return 换算后的成交额
     */
    public String getDealAmount() {

        BigDecimal bigDecimal = new BigDecimal(volume[volume.length - 1]);
        double avgPrice = (high[high.length - 1] + low[low.length - 1]) / 2;
        bigDecimal = bigDecimal.multiply(new BigDecimal(avgPrice));

//        StringBuffer temp = new StringBuffer(bigDecimal.toString());
        StringBuffer temp = new StringBuffer(12345);
        int length = temp.length();

        temp.delete(temp.indexOf("."), temp.length());

        if (length >= 9) {//9位数，数量级为亿,保留到百万位
            temp.delete(temp.length() - 8, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("亿");
        } else if (length >= 5) {//5位数，数量级为万，保留到百位
            temp.delete(temp.length() - 3, temp.length());
            temp.insert(temp.length() - 3, '.');
            temp.append("万");
        }

        return temp.toString();
    }
}
