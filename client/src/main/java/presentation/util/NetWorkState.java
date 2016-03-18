package presentation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 宋益明 on 16-3-18.
 * <p>
 * 检查网络连接是否正常
 */
public class NetWorkState {
    private NetWorkState() {}

    public static boolean netConnect() throws IOException {
        String address = "www.baidu.com";

        Process process = Runtime.getRuntime().exec("ping " + address);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line = reader.readLine();

        return line.equals("");
    }
}
