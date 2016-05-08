package po;

/**
 * Created by user on 2016/3/31.
 */
public class DailyStockPO {
    /**
     *
     */
    private String[] time;
    /**
     *
     */
    private double[] price;

    public DailyStockPO(){
        time = new String[240];
        price = new double[240];
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public double[] getPrice() {
        return price;
    }

    public void setPrice(double[] price) {
        this.price = price;
    }
}
