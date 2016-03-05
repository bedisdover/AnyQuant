package bl;

import net.sf.json.util.JSONTokener;

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
public class ReadDataTest {
    public void getData(String url) {
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
    }
    public static void main(String[] args){
        String url = "http://121.41.106.89:8010/api/stocks/?year=2014&exchange=sh";
        ReadDataTest rdt = new ReadDataTest();
        rdt.getData(url);
//        JSONTokener jsonParser = new JSONTokener(json);
    }
}
