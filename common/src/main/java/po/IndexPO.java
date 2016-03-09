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

    public IndexPO(int numberOfDays){
        volume = new long[numberOfDays];
        high = new double[numberOfDays];
        adj_price = new double[numberOfDays];
        low = new double[numberOfDays];
        date = new String[numberOfDays];
        close = new double[numberOfDays];
        open = new double[numberOfDays];
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
}
