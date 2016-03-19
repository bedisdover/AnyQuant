package netconnect;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by 宋益明 on 16-3-18.
 */
public class PingTest {
    public static void main(String[] args) throws IOException {
        String address = "www.baidu.com";

        Process process = Runtime.getRuntime().exec("ping " + address);
        InputStreamReader r = new InputStreamReader(process.getInputStream());
        LineNumberReader returnData = new LineNumberReader(r);

        String returnMsg = "";
        String line;
        if ((line = returnData.readLine()) != null) {
            returnMsg += line;
        }

        System.out.println(returnMsg + "....");

        if (!returnMsg.equals("")) {
            System.out.println("与 " + address + " 连接不畅通.");
        } else {
            System.out.println("与 " + address + " 连接畅通.");
        }

    }
}