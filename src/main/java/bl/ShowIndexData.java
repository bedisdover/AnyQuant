package bl;

import blservice.ShowIndexDataService;
import data.GetIndexData;
import database.GetIndexData_DB;
import dataservice.GetIndexDataService;
import po.IndexPO;
import vo.IndexVO;

import java.io.IOException;

/**
 * Created by zcy on 2016/3/9.
 *
 */
public class ShowIndexData implements ShowIndexDataService{

    /**
     * @return IndexVO
     * @throws IOException
     * 得到最新的大盘数据（默认为2012-10-10至今）
     */
    public IndexVO getLatestIndexData() throws IOException {
        GetIndexData_DB getIndexData = new GetIndexData_DB();
        IndexPO indexPO = getIndexData.getLatestIndexData();
        IndexVO indexVO = new IndexVO(indexPO);
        return indexVO;
    }

    /**
     * @param date1
     * @param date2
     * @return indexVO
     * 得到指定时间段内的大盘数据
     */
    public IndexVO getIndexDataBetween(String date1,String date2){
        GetIndexData_DB getIndexData = new GetIndexData_DB();
        IndexPO indexPO = getIndexData.getIndexDataBetween(date1,date2);
        IndexVO indexVO = new IndexVO(indexPO);
        return indexVO;
    }

}
