package config;

import org.dom4j.Element;

/**
 * Created by 宋益明 on 16-4-10.
 *
 * 股票数据配置类
 */
public class StockDataConfig {

    /**
     * 配置信息
     */
    private Element stockData;

    StockDataConfig(Element dataConfig) {
        this.stockData = dataConfig.element("StockData");
    }

    /**
     * 获得数据更新间隔
     *
     * @return 时间间隔，单位为分钟
     */
    public int getUpdateLag() {
        //理论最小间隔为2分钟
        int lag = 2;

        try {
            lag = Integer.parseInt(stockData.attributeValue("updateLag"));
        } catch (Exception e) {
            //若发生类型转换异常，不作处理，直接返回最小值
        }

        return lag;
    }

    /**
     * 设置更新间隔
     *
     * @param lag 时间间隔，单位为分钟
     */
    public void setUpdateLag(int lag) {
        stockData.addAttribute("updateLag", lag + "");
    }

    public boolean isHighSelected() {
        return Boolean.valueOf(stockData.element("high").attributeValue("selected"));
    }

    public boolean isLowSelected() {
        return Boolean.valueOf(stockData.element("low").attributeValue("selected"));
    }

    public boolean isOpenSelected() {
        return Boolean.valueOf(stockData.element("open").attributeValue("selected"));
    }

    public boolean isCloseSelected() {
        return Boolean.valueOf(stockData.element("close").attributeValue("selected"));
    }

    public boolean isVolumeSelected() {
        return Boolean.valueOf(stockData.element("volume").attributeValue("selected"));
    }

    public boolean isPbSelected() {
        return Boolean.valueOf(stockData.element("pb").attributeValue("selected"));
    }

    public boolean isPe_ttmSelected() {
        return Boolean.valueOf(stockData.element("pe_ttm").attributeValue("selected"));
    }

    public boolean isAdjPriceSelected() {
        return Boolean.valueOf(stockData.element("adjPrice").attributeValue("selected"));
    }

    public boolean isTurnOverSelected() {
        return Boolean.valueOf(stockData.element("turnOver").attributeValue("selected"));
    }

    public void changeHigh() {
        Element high = stockData.element("high");
        high.addAttribute("selected", !isHighSelected() + "");
    }

    public void changeLow() {
        Element low = stockData.element("low");
        low.addAttribute("selected", !isLowSelected() + "");
    }

    public void changeOpen() {
        Element open = stockData.element("open");
        open.addAttribute("selected", !isOpenSelected() + "");
    }

    public void changeClose() {
        Element close = stockData.element("close");
        close.addAttribute("selected", !isCloseSelected() + "");
    }

    public void changeVolume() {
        Element volume = stockData.element("volume");
        volume.addAttribute("selected", !isVolumeSelected() + "");
    }

    public void changePb() {
        Element pb = stockData.element("pb");
        pb.addAttribute("selected", !isPbSelected() + "");
    }

    public void changePe_ttm() {
        stockData.element("pe_ttm").addAttribute("selected", !isPe_ttmSelected() + "");
    }

    public void changeAdjPrice() {
        stockData.element("adjPrice").addAttribute("selected", !isAdjPriceSelected() + "");
    }

    public void changeTurnOver() {
        stockData.element("").addAttribute("selected", !isTurnOverSelected() + "");
    }
}
