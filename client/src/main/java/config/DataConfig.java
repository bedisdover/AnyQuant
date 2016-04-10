package config;

import org.dom4j.Element;

/**
 * Created by 宋益明 on 16-4-10.
 *
 * 数据配置类
 */
public class DataConfig {

    /**
     * 配置信息
     */
    private Element dataConfig;

    DataConfig(Element dataConfig) {
        this.dataConfig = dataConfig;
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
            Element stockData = dataConfig.element("StockData");
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
        Element stockData = dataConfig.element("StockData");
        stockData.addAttribute("updateLag", lag + "");
    }
}
