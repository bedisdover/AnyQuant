package data.currentdata;

import dataservice.current.CurrentStockDataService;
import net.sf.json.JSONObject;
import po.current.CurrentStockPO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 宋益明 on 16-4-5.
 * <p>
 * 股票当前信息数据类
 * 负责提供股票当前数据
 */
public class CurrentStockData implements CurrentStockDataService {

    /**
     * 请求码，用于请求网络数据
     */
    private static final String APP_KEY = "5f2ce1ab06969ecf179311a72416ac3d";

    /**
     * 根据股票代码获取股票当前数据
     *
     * @param id 股票代码
     * @return 股票数据持久化对象
     * @throws Exception
     */
    @Override
    public CurrentStockPO getCurrentStockPO(String id) throws Exception {
        return new CurrentStockPO(getCurrentData(id));
    }

    /**
     * 获取当前数据
     *
     * @param id 股票ID
     * @return json数据包
     * @throws Exception
     */
    private String getCurrentData(String id) throws Exception {
        String result;
        String url = "http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
        Map<String, String> params = new HashMap<>();//请求参数
        params.put("gid", id);//股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009
        params.put("key", APP_KEY);//APP Key

        result = GetData.get(url, params, "GET");
        JSONObject object = JSONObject.fromObject(result);

        //去掉首尾的'['和']'
        String temp = object.getString("result");
        temp = temp.substring(1, temp.length() - 1);

        return JSONObject.fromObject(temp).getString("data");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new CurrentStockData().getCurrentData("sh601009"));
    }
}
