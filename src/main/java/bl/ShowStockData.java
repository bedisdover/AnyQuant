package bl;

import data.GetStockData;
import data.ReadData;
import database.GetStockData_DB;
import dataservice.GetStockDataService;
import po.StockPO;
import vo.StockVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zcy on 2016/3/8.
 *
 */
public class ShowStockData {


    /**
     * @param id
     * @return StockVO
     * @throws IOException
     * 得到指定股票的所有历史数据
     */
    public StockVO getStockData(String id) throws IOException {
        GetStockData_DB getStockData = new GetStockData_DB();
        StockPO a = getStockData.getStockData_name(id,"2005-01-01",getDate_Today());
        StockVO stockVO = new StockVO(a);
        return stockVO;
    }

    private String getDate_Today(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args){
        ShowStockData showStockData = new ShowStockData();
        System.out.println(showStockData.getDate_Today());
    }
}
