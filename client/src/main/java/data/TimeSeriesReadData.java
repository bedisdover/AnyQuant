package data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by syz on 2016/4/9.
 */
public class TimeSeriesReadData {
    //5cd97982c4a9f8352b5497694f26be84
    //"http://op.juhe.cn/onebox/stock/query "
    public String getData(String url) throws IOException {
        StringBuilder json = new StringBuilder();
        URL urlObject = new URL(url);
        URLConnection uc = urlObject.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) uc;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("X-Auth-Code", "5cd97982c4a9f8352b5497694f26be84");
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            json.append(inputLine);
        }
        br.close();

        return json.toString();
    }

    public String getCurrentData(String url) throws IOException {
        StringBuilder json = new StringBuilder();

        URL urlObject = new URL(url);
        HttpURLConnection httpUrl = (HttpURLConnection) urlObject.openConnection();
        httpUrl.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(httpUrl.getInputStream()));
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            json.append(inputLine);
        }

        br.close();

        return json.toString();
    }

    public String parseJSON(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        return jsonObject.getString(key);
    }

    public String[] parseJSON_array(String jsonStr, String key) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        String[] result = new String[jsonArray.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    /**
     * 第一个key得到的是array，第二个key得到的不是array
     */
    public String[] parseJson(String jsonStr, String key, String subKey) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        String[] result = new String[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject temp = JSONObject.fromObject(jsonArray.getString(i));
            result[i] = temp.getString(subKey);
        }
        return result;
    }

    /*
    第一个得到的不是array，第二个得到的是array
    */
    public String[] parseJson2(String jsonStr, String key, String subKey) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String tradingInfo = jsonObject.getString(key);
        JSONObject jsonObject1 = JSONObject.fromObject(tradingInfo);
        JSONArray jsonArray = jsonObject1.getJSONArray(subKey);
        String[] result = new String[jsonObject1.size()];
        for (int i = 0; i < result.length; i++) {
            JSONObject temp = JSONObject.fromObject(jsonArray.getString(i));
            result[i] = temp.getString("date");
        }
        return result;
    }

    public static void main(String[] args){
        TimeSeriesReadData timeSeriesReadData = new TimeSeriesReadData();

        String str = null;
        try {
            str = timeSeriesReadData.getData("http://op.juhe.cn/onebox/stock/query ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);

    }
}
