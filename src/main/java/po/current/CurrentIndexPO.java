package po.current;

import net.sf.json.JSONObject;
import util.Format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by 宋益明 on 16-4-5.
 * <p>
 * 当前大盘指数持久化对象
 * 包括 名称、当前时间、当前价格、涨跌幅、涨跌百分比、
 *      最高价、最低价、今开、昨收、成交量、成交额
 */
public class CurrentIndexPO {

    /**
     * 名称
     */
    private String name;

    /**
     * 当前时间
     */
    private Date time;

    /**
     * 当前价格
     */
    private double price;

    /**
     * 涨跌额
     */
    private double increase;

    /**
     * 涨跌百分比
     */
    private double increasePer;

    /**
     * 最高
     */
    private double high;

    /**
     * 最低
     */
    private double low;

    /**
     * 今开
     */
    private double open;

    /**
     * 昨收
     */
    private double close;

    /**
     * 成交量
     */
    private BigInteger dealNum;

    /**
     * 成交额
     */
    private BigDecimal dealAmount;

    /**
     * @param data json数据包格式, 形如：
     * {"dealNum":"26293583594","dealPri":"436963378767.980","highPri":"10656.152",
     * "increPer":"2.51","increase":"260.621","lowpri":"10373.539","name":"深证成指",
     * "nowpri":"10640.268","openPri":"10393.650","time":"2016-04-05 15:08:03","yesPri":"10379.647"}
     */
    public CurrentIndexPO(String data) {
        JSONObject jsonObject = JSONObject.fromObject(data);

        dealNum = new BigInteger(jsonObject.getString("dealNum"));
        dealAmount = new BigDecimal(jsonObject.getString("dealPri"));
        high = jsonObject.getDouble("highPri");
        increasePer = jsonObject.getDouble("increPer");
        increase = jsonObject.getDouble("increase");
        low = jsonObject.getDouble("lowpri");
        name = jsonObject.getString("name");
        price = jsonObject.getDouble("nowpri");
        open = jsonObject.getDouble("openPri");
        try {
            time = Format.dateFormat.parse(jsonObject.getString("time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        close = jsonObject.getDouble("yesPri");
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public double getIncrease() {
        return increase;
    }

    public double getIncreasePer() {
        return increasePer;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public BigInteger getDealNum() {
        return dealNum;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }
}
