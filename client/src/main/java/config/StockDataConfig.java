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
        this.stockData = dataConfig;
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

    public void setHigh(boolean selected) {
        stockData.element("high").addAttribute("selected", selected + "");
    }

    public void setLow(boolean selected) {
        stockData.element("low").addAttribute("selected", selected + "");
    }

    public void setOpen(boolean selected) {
        stockData.element("open").addAttribute("selected", selected + "");
    }

    public void setClose(boolean selected) {
        stockData.element("close").addAttribute("selected", selected + "");
    }

    public void setVolume(boolean selected) {
        stockData.element("volume").addAttribute("selected", selected + "");
    }

    public void setPb(boolean selected) {
        stockData.element("pb").addAttribute("selected", selected + "");
    }

    public void setPe_ttm(boolean selected) {
        stockData.element("pe_ttm").addAttribute("selected", selected + "");
    }

    public void setAdjPrice(boolean selected) {
        stockData.element("adjPrice").addAttribute("selected", selected + "");
    }

    public void setTurnOver(boolean selected) {
        stockData.element("turnOver").addAttribute("selected", selected + "");
    }
}
