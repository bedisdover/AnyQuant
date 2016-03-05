package bl;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

/**
 * Created by zcy on 2016/3/3.
 */
public class ReadDataTest {

    public String getData(String url) {
        StringBuilder json = new StringBuilder();
        try{
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) uc;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Auth-Code","868f903da1795d23fd59e58707b7f6fa");
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while((inputLine = br.readLine())!=null){
                json.append(inputLine);
            }
            br.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(json.toString());
        return json.toString();
    }
    public String[] parseJson(String jsonStr) throws JSONException,ParseException {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        String[] result = new String[jsonArray.size()];
        for(int i=0;i<jsonArray.size();i++){
            System.out.print(jsonArray.getString(i)+" ");
            JSONObject temp = JSONObject.fromObject(jsonArray.getString(i));
            result[i] = temp.getString("link");
        }
        return result;
    }

    public static void main(String[] args){
        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sh";
        ReadDataTest rdt = new ReadDataTest();
        String result = rdt.getData(url);
        String[] info = null;
        try {
            info = rdt.parseJson(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<info.length;i++){
            System.out.println(info[i]);
        }
    }
}
