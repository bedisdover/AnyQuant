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
     * todo 刚开始默认加载最近一个月的数据
     * todo 然后每次选择日期后，给我一个方法，根据两个时间点返回数据，最好只返回多出的那部分数据
     */

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

}
