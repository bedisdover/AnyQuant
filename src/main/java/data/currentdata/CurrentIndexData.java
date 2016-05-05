package data.currentdata;

import dataservice.current.CurrentIndexService;
import net.sf.json.JSONObject;
import po.current.CurrentIndexPO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 宋益明 on 16-4-5.
 */
public class CurrentIndexData implements CurrentIndexService {

    /**
     * 请求码，用于请求网络数据
     */
    private static final String APP_KEY = "5f2ce1ab06969ecf179311a72416ac3d";

    /**
     * 获取深证指数当前数据
     *
     * @return 当前数据持久化对象
     * @throws Exception
     */
    @Override
    public CurrentIndexPO getCurrentIndexPO_SZ() throws Exception {
        return new CurrentIndexPO(getCurrentData(1));
    }

    /**
     * 获取上证指数当前数据
     *
     * @return 当前数据持久化对象
     * @throws Exception
     */
    @Override
    public CurrentIndexPO getCurrentIndexPO_SH() throws Exception {
        return new CurrentIndexPO(getCurrentData(0));
    }

    /**
     * 获取当前数据
     *
     * @param type 编号，0代表上证，1代表深证
     * @return json数据包
     * @throws Exception
     */
    private String getCurrentData(int type) throws Exception {
        String result;
        String url = "http://web.juhe.cn:8080/finance/stock/hs";//请求接口地址
        Map<String, String> params = new HashMap<>();//请求参数

        params.put("key", APP_KEY);//APP Key
        params.put("type", type + "");

        result = GetData.get(url, params, "GET");
        JSONObject object = JSONObject.fromObject(result);

        return object.getString("result");
    }
}
