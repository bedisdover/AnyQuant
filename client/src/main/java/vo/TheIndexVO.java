package vo;

/**
 * Created by zcy on 2016/4/13.
 * 股票和大盘的各项指标
 */
public class TheIndexVO {
    /**
     * 乖离率
     */
    private double bias;

    /**
     * 相对强弱指标
     */
    private double RSI;

    /**
     * 威廉超买超卖指标
     */
    private double WM;

    /**
     * 人气指标
     */
    private double AR;

    /**
     * 意愿指标
     */
    private double BR;

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getRSI() {
        return RSI;
    }

    public void setRSI(double RSI) {
        this.RSI = RSI;
    }

    public double getWM() {
        return WM;
    }

    public void setWM(double WM) {
        this.WM = WM;
    }

    public double getAR() {
        return AR;
    }

    public void setAR(double AR) {
        this.AR = AR;
    }

    public double getBR() {
        return BR;
    }

    public void setBR(double BR) {
        this.BR = BR;
    }
}
