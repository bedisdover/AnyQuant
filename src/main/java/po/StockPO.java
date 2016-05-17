package po;

/**
 * Created by zcy on 2016/3/7.
 *
 */
public class StockPO {
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

    public StockPO(int numberOfDays) {
        volume = new long[numberOfDays];
        pb = new double[numberOfDays];
        high = new double[numberOfDays];
        pe_ttm = new double[numberOfDays];
        adj_price = new double[numberOfDays];
        low = new double[numberOfDays];
        date = new String[numberOfDays];
        close = new double[numberOfDays];
        open = new double[numberOfDays];
        turnover = new double[numberOfDays];
        increase_decreaseRate = new double[numberOfDays];
        increase_decreaseNum = new double[numberOfDays];
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setVolume(long[] volume) {
        this.volume = volume;
    }

    public void setPb(double[] pb) {
        this.pb = pb;
    }

    public void setHigh(double[] high) {
        this.high = high;
    }

    public void setPe_ttm(double[] pe_ttm) {
        this.pe_ttm = pe_ttm;
    }

    public void setAdj_price(double[] adj_price) {
        this.adj_price = adj_price;
    }

    public void setLow(double[] low) {
        this.low = low;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setClose(double[] close) {
        this.close = close;
    }

    public void setOpen(double[] open) {
        this.open = open;
    }

    public void setTurnover(double[] turnover) {
        this.turnover = turnover;
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
