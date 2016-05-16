package bl;

import blservice.HistoryRecordStockService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by syz on 2016/3/11.
 *
 */
public class HistoryRecordStock implements HistoryRecordStockService {


    @Override
    public void addStock(String id) throws IOException {

    }

    @Override
    public void removeStock(String id) throws IOException {

    }

    @Override
    public Iterator<String> getRecord() {
        return null;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }
}
