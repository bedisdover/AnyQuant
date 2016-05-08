package data;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Created by syz on 2016/4/9.
 */
public class JuheDemo {
    public final String DEF_CHATSET = "UTF-8";
    public final int DEF_CONN_TIMEOUT = 30000;
    public String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public final String APPKEY = "5cd97982c4a9f8352b5497694f26be84";

    public String getRequest1(String stockName) {
        String result = null;
        String url = "http://op.juhe.cn/onebox/stock/query";
        Map params = new HashMap();
        params.put("key", APPKEY);
        params.put("dtype", "");
        params.put("stock", stockName);

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") != 0) {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout((DEF_CONN_TIMEOUT));
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeByte(Integer.parseInt(urlencode(params)));
                } catch (Exception e) {

                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    public String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        JuheDemo j = new JuheDemo();
        String result = j.getRequest1("长江实业");
        System.out.println(result);
        ReadData rd = new ReadData();
        String t = rd.parseJSON(result,"result");
        System.out.println(t);
        String d = rd.parseJSON(t,"timeChart");
        System.out.println(d);
        String stock = rd.parseJSON(d,"p");
        stock = stock.substring(1,stock.length()-1);
        System.out.println(stock);
        String dongping = rd.parseJSON(stock,"time");
        System.out.println(dongping);
    }
}

