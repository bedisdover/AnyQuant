package bl;

import blservice.HistoryRecordStockService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by syz on 2016/3/11.
 */
public class HistoryRecordStock implements HistoryRecordStockService {

    private File file;

    private List<String> list;

    public HistoryRecordStock() {
        file = new File("client/src/main/resources/historyRecord.data");
        list = new ArrayList<String>();
    }

    @Override
    public void addStock(String id) throws IOException {
        if (!exist(id)) {
            list.add(id);
            store();
        }
    }

    @Override
    public void removeStock(String id) throws IOException {
        if (exist(id)) {
            list.remove(id);
            store();
        }
    }

    @Override
    public Iterator<String> getRecord() {
        return list.iterator();
    }

    @Override
    public boolean exist(String id) {
        return list.contains(id);
    }

    //加载股票代码列表
    public void load() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            String stockid;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((stockid = br.readLine()) != null) {
                list.add(stockid);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //存储更新后的股票列表
    public void store() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        for(String stockid:list){
            bw.write(stockid+"\n");
        }
        bw.close();
    }
}
