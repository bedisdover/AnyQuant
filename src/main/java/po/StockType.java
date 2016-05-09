package po;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 宋益明 on 16-4-13.
 * <p>
 * 股票类型（沪深、美股）判断类
 */
public class StockType {
    private StockType() {
    }

    /**
     * 判断股票是否是美股
     *
     * @param stockID 股票ID
     * @return 若是，返回true，否则返回false
     */
    public static boolean isUS(String stockID) {
        //沪深股票以sh或sz开始，其余均为数字
        return !isNumber(stockID.substring(2));
    }

    /**
     * 判断字符串是否为数字
     *
     * @param string 字符串
     * @return 若是，返回true，否则返回false
     */
    private static boolean isNumber(String string) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }
}
