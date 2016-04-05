package data.briefdata;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 宋益明 on 16-4-5.
 *
 * 股票主要信息数据类
 * 负责提供股票当前数据
 */
public class StockBriefData {

    private static final String APP_KEY = "5f2ce1ab06969ecf179311a72416ac3d";

    public static void getBriefData(String id) {
        String result;
        String url = "http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("gid", id);//股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009
        params.put("key", APP_KEY);//APP Key

        try {
            result = GetData.get(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
