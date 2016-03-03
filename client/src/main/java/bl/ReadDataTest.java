package bl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
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
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%88%90%E9%83%BD&output=json&ak=rnm8udmHdWaHFWZTO2tuTiG8";
        ReadDataTest rdt = new ReadDataTest();
        rdt.getData(url);
    }
}
