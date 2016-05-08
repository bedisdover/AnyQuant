package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by 宋益明 on 16-4-5.
 *
 * 格式类
 * 包含时间格式
 */
public interface Format {

    /**
     * 时间格式
     *
     * @see po.current.CurrentStockPO
     * @see po.current.CurrentIndexPO
     */
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
}
