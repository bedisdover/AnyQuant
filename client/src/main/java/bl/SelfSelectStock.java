package bl;

import blservice.SelfSelectStockService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2016/3/4.
 * <p>
 * 自选股票逻辑实现类
 * 负责管理自选股票的添加与删除
 */
public class SelfSelectStock implements SelfSelectStockService {

    /**
     * 存储关注列表的文件
     */
    private final File FILE;

    /**
     * 股票代码列表
     */
    private List<String> list;

    public SelfSelectStock() {
        FILE = new File("client/src/main/resources/follow.data");
        list = new ArrayList<>();

        load();
    }

    /**
     * 关注股票
     *
     * @param id 股票代码
     * @return 若关注成功, 返回true, 否则返回false
     */
    public void addStock(String id) throws IOException {
        if (!exist(id)) {
            list.add(id);
            store();
        }
    }

    /**
     * 取消关注
     *
     * @param id 股票代码
     */
    public void removeStock(String id) throws IOException {
        if (exist(id)) {
            list.remove(id);
            store();
        }
    }

    /**
     * 获得关注股票列表
     *
     * @return 股票代码列表的迭代器
     */
    public Iterator<String> getFollowed() {
        return list.iterator();
    }

    /**
     * 判断是否已关注股票
     *
     * @param id 股票列表
     * @return 若已关注, 返回true, 否则返回false
     */
    public boolean exist(String id) {
        return list.contains(id);
    }

    /**
     * 加载股票代码列表
     */
    private void load() {
        try {
            if (!FILE.exists()) {
                FILE.createNewFile();
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(FILE)));

            String stockID;
            while ((stockID = reader.readLine()) != null) {
                list.add(stockID);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储更新后的股票代码列表
     */
    private void store() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE)));

        for (String stockID : list) {
            writer.write(stockID + "\n");
        }

        writer.close();
    }
}
