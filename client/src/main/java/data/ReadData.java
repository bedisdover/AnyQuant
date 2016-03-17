package data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zcy on 2016/3/3.
 */
public class ReadData {

    public String getData(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) uc;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Auth-Code", "868f903da1795d23fd59e58707b7f6fa");
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                json.append(inputLine);
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sh";
        ReadData rdt = new ReadData();
        String result = rdt.getData(url);
        String[] info = rdt.parseJson(result, "data", "link");
        for (int i = 0; i < info.length; i++) {
            System.out.println(info[i]);
        }

        String[] names = new String[info.length];
        String[][] volume = null;
        for (int n = 0; n < info.length; n++) {
            if (n == 7) {
                continue;
            }
            String s1 = rdt.getData(info[n]);
            String s = rdt.parseJSON(s1, "data");
            names[n] = rdt.parseJSON(s, "name");
            System.out.println(names[n]);
//            String[] sa = rdt.parseJSON_array(s,"trading_info");
//            if(volume==null){
//                volume = new String[info.length][sa.length];
//            }
//            for(int i=0;i<sa.length;i++){
//                volume[n][i] = rdt.parseJSON(sa[i],"volume");
//                System.out.print(volume[n][i]+" ");
//            }
//            System.out.println();
        }
    }
}
