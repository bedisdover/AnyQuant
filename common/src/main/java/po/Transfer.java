package po;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by 宋益明 on 16-3-9.
 * <p>
 * 股票代码-名称转换类
 * 可根据代码获得股票名称
 */
public class Transfer {

    private static Map<String, String> name_ID_sh;

    private static Map<String, String> name_ID_sz;


    private static final File SH_FILE;

    private static final File SZ_FILE;

    private static final Set<Map.Entry<String, String>> SH_SET;
    private static final Set<Map.Entry<String, String>> SZ_SET;

    static {
        name_ID_sh = new HashMap<>();
        name_ID_sz = new HashMap<>();
        SH_SET = name_ID_sh.entrySet();
        SZ_SET = name_ID_sz.entrySet();

        SH_FILE = new File("common/src/main/resources/sh.txt");
        SZ_FILE = new File("common/src/main/resources/sz.txt");

        init();
    }

    private static void init() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(SH_FILE)));
            String line;
            StringTokenizer tokenizer;
            while ((line = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    name_ID_sh.put(tokenizer.nextToken(), tokenizer.nextToken());
                }
            }

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(SZ_FILE)));

            while ((line = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    System.out.println(line);
                    name_ID_sz.put(tokenizer.nextToken(), tokenizer.nextToken());
                }
            }
            System.out.println(name_ID_sz.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getName(String stockID) {
        if (stockID.startsWith("sh")) {
            stockID = stockID.substring(2);
            for (Map.Entry<String, String> entry : SH_SET) {
                if (entry.getValue().equals(stockID)) {
                    return entry.getKey();
                }
            }
        }
        if (stockID.startsWith("sz")) {
            stockID = stockID.substring(2);
            for (Map.Entry<String, String> entry : SZ_SET) {
                if (entry.getValue().equals(stockID)) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }
}
