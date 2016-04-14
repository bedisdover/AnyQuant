package config;

import org.dom4j.Element;

/**
 * Created by 宋益明 on 16-4-14.
 * <p>
 * 大盘指数数据配置类
 */
public class IndexDataConfig {
    /**
     * 配置信息
     */
    private Element indexData;

    IndexDataConfig(Element dataConfig) {
        this.indexData = dataConfig;
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
            lag = Integer.parseInt(indexData.attributeValue("updateLag"));
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
        indexData.addAttribute("updateLag", lag + "");
    }

    public boolean isHighSelected() {
        return Boolean.valueOf(indexData.element("high").attributeValue("selected"));
    }

    public boolean isLowSelected() {
        return Boolean.valueOf(indexData.element("low").attributeValue("selected"));
    }

    public boolean isOpenSelected() {
        return Boolean.valueOf(indexData.element("open").attributeValue("selected"));
    }

    public boolean isCloseSelected() {
        return Boolean.valueOf(indexData.element("close").attributeValue("selected"));
    }

    public boolean isVolumeSelected() {
        return Boolean.valueOf(indexData.element("volume").attributeValue("selected"));
    }

    public boolean isAdjPriceSelected() {
        return Boolean.valueOf(indexData.element("adjPrice").attributeValue("selected"));
    }

    public void setHigh(boolean selected) {
        indexData.element("high").addAttribute("selected", selected + "");
    }

    public void setLow(boolean selected) {
        indexData.element("low").addAttribute("selected", selected + "");
    }

    public void setOpen(boolean selected) {
        indexData.element("open").addAttribute("selected", selected + "");
    }

    public void setClose(boolean selected) {
        indexData.element("close").addAttribute("selected", selected + "");
    }

    public void setVolume(boolean selected) {
        indexData.element("volume").addAttribute("selected", selected + "");
    }

    public void setAdjPrice(boolean selected) {
        indexData.element("adjPrice").addAttribute("selected", selected + "");
    }
}
