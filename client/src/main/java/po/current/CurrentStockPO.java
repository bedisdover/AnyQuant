package po.current;

import net.sf.json.JSONObject;
import util.Format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by 宋益明 on 16-4-5.
 *
 * 股票当前数据持久化接口
 * 包括
 */
public class CurrentStockPO {

    /**
     * 股票ID
     */
    private String id;

    /**
     * 股票名称
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
     * {"buyFive":"105539","buyFivePri":"16.220","buyFour":"77538","buyFourPri":"16.230",
     * "buyOne":"10400","buyOnePri":"16.260","buyThree":"46893","buyThreePri":"16.240",
     * "buyTwo":"9400","buyTwoPri":"16.250","competitivePri":"16.260","date":"2016-04-05",
     * "gid":"sh601009","increPer":"0.62","increase":"0.100","name":"南京银行","nowPri":"16.260",
     * "reservePri":"16.280","sellFive":"31905","sellFivePri":"16.320","sellFour":"49300",
     * "sellFourPri":"16.310","sellOne":"13635","sellOnePri":"16.280","sellThree":"163547",
     * "sellThreePri":"16.300","sellTwo":"57016","sellTwoPri":"16.290","time":"15:00:00",
     * "todayMax":"16.400","todayMin":"15.880","todayStartPri":"16.070","traAmount":"428221667.000",
     * "traNumber":"264373","yestodEndPri":"16.160"}
     */
    public CurrentStockPO(String data) {
        JSONObject object = JSONObject.fromObject(data);

        id = object.getString("gid");
        name = object.getString("name");

        {//日期和时间分开存储
            String temp = object.getString("date");
            temp = temp + " " + object.getString("time");
            try {
                time = Format.dateFormat.parse(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        price = object.getDouble("nowPri");
        increase = object.getDouble("increase");
        increasePer = object.getDouble("increPer");
        high = object.getDouble("todayMax");
        low = object.getDouble("todayMin");
        open = object.getDouble("todayStartPri");
        close = object.getDouble("yestodEndPri");
        dealNum = new BigInteger(object.getString("traNumber"));
        dealAmount = new BigDecimal(object.getString("traAmount"));
    }

    public String getId() {
        return id;
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

    /**
     * 将成交量进行数量级换算为万/亿后，以字符串形式返回
     *
     * @return 换算后的成交量
     */
    public String getDealNum() {
        int length =dealNum.toString().length();
        StringBuilder temp = new StringBuilder(dealNum.toString());

        if (length >= 9) {//9位数，数量级为亿,保留到百万位
            temp.delete(temp.length() - 6, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("亿手");
        } else if (length >= 5) {//5位数，数量级为万，保留到百位
            temp.delete(temp.length() - 2, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("万手");
        } else {
            temp.append("手");
        }

        return temp.toString();
    }

    /**
     * 将成交额进行数量级换算为万/亿后，以字符串形式返回
     *
     * @return 换算后的成交额
     */
    public String getDealAmount() {
        int length =dealAmount.toString().length();
        StringBuilder temp = new StringBuilder(dealAmount.toString());

        if (length >= 13) {//9位数(包含小数点后三位)，数量级为亿,保留到百万位
            temp.delete(temp.length() - 10, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("亿");
        } else if (length >= 9) {//5位数，数量级为万，保留到百位
            temp.delete(temp.length() - 6, temp.length());
            temp.insert(temp.length() - 2, '.');
            temp.append("万");
        }

        return temp.toString();
    }
}
