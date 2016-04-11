package po;

/**
 * Created by user on 2016/3/9.
 */
public class IndexPO {
    private String name;
    private long[] volume;
    private double[] high;
    private double[] adj_price;
    private double[] low;
    private String[] date;
    private double[] close;
    private double[] open;
    /**
     * 该股票对应日期内的涨跌幅
     */
    private double[] increase_decreaseRate;
    /**
     * 该股票对应日期内的涨跌额
     */
    private double[] increase_decreaseNum;

    public IndexPO(int numberOfDays) {
        volume = new long[numberOfDays];
        high = new double[numberOfDays];
        adj_price = new double[numberOfDays];
        low = new double[numberOfDays];
        date = new String[numberOfDays];
        close = new double[numberOfDays];
        open = new double[numberOfDays];
        increase_decreaseRate = new double[numberOfDays];
        increase_decreaseNum = new double[numberOfDays];
    }


    public long[] getVolume() {
        return volume;
    }

    public void setVolume(long[] volume) {
        this.volume = volume;
    }

    public double[] getHigh() {
        return high;
    }

    public void setHigh(double[] high) {
        this.high = high;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
