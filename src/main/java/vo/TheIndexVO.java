package vo;

import java.io.Serializable;

/**
 * Created by zcy on 2016/4/13.
 * 股票和大盘的各项指标
 */
public class TheIndexVO implements Serializable{
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

    /**
     * 成交量变异率
     */
    private double VR;

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

    public double getVR() {
        return VR;
    }

    public void setVR(double VR) {
        this.VR = VR;
    }

    public String conclusion1() {
        if (bias >= 2) {
            return "短期获利较大，获利回吐的可能性较高。";
        } else if (bias <= -2) {
            return "空头回补的可能性较高。";
        } else {
            return "股价小幅波动，比较稳定。";
        }
    }

    public String conclusion2() {
        if (RSI > 80) {
            return "进入超买区，股价随时可能形成短期回档。";
        } else if (RSI < 20) {
            return "进入超卖区，股价随时可能形成短期反弹。";
        } else if (RSI >= 50) {
            return "处于多头行情";
        } else {
            return "处于空头行情";
        }
    }

    public String conclusion3() {
        if (WM > 80) {
            return "处于超卖状态，行情即将见底。";
        } else if (WM < 20) {
            return "处于超买状态，行情即将见顶。";
        } else {
            return "尚未进入超买超卖状态。";
        }
    }

    public String conclusion4() {
        if (AR >= 60 && AR <= 120) {
            return "属盘整行情,股价走势平稳,不会出现剧烈波动。";
        } else if (AR < 60) {
            return "股价有可能随时反弹上升。";
        } else {
            return "股价随时可能回档下跌。";
        }
    }

    public String conclusion5() {
        if (VR<7040) {
            return "股票属于可以买进区域";
        } else if (VR>=7040&&VR<15080) {
            return "股票属于正常分布区域";
        } else if(VR<450160&&VR>=15080){
            return "股价较高，应考虑获利了结";
        } else{
            return "股价已过高";
        }
    }

}
