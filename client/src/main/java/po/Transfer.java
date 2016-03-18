package po;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-3-9.
 * <p>
 * 股票代码-名称转换类
 * 可根据代码获得股票名称
 */
public class Transfer {

    /**
     * 代码-名称对应表
     */
    private static Map<String, String> name_ID;

    /**
     * 文件路径
     */
    private static final File FILE_NAME;

    /**
     * 见SystemConfig.TEST_FILE
     */
    private static final File TEST_FILE;

    /**
     * 见SystemConfig.test
     */
    private static boolean test = false;

    static {
        name_ID = new HashMap<>();

        FILE_NAME = new File("client/src/main/resources/bank_stock.txt");
        TEST_FILE = new File("src/main/resources/bank_stock.txt");

        init();
    }

    private static void init() {
        try {
            FileInputStream fileInputStream;

            if (test) {
                fileInputStream = new FileInputStream(TEST_FILE);
            } else {
                fileInputStream = new FileInputStream(FILE_NAME);
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fileInputStream));

            String line;
            StringTokenizer tokenizer;
            while ((line = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    name_ID.put(tokenizer.nextToken(), tokenizer.nextToken());
                }
            }

            fileInputStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getName(String stockID) {
        return name_ID.get(stockID);
    }
}
