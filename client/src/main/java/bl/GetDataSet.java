package bl;

import blservice.GetDataSetService;
import data.GetIndexData;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import po.IndexPO;
import vo.IndexVO;

import java.io.IOException;

/**
 * Created by user on 2016/3/24.
 */
public class GetDataSet implements GetDataSetService{

    public XYDataset getStockDataSet_monthly() {
        return null;
    }


    public XYDataset getStockDataSet_daily() {
        return null;
    }


    public IndexVO getIndexDataSet() throws IOException {
        GetIndexData getIndexData = new GetIndexData();
        IndexPO indexPO = getIndexData.getLatestIndexData();
        IndexVO indexVO = new IndexVO(indexPO);

        return indexVO;
    }


    public XYDataset getIndexDataSet_daily() {
        return null;
    }
}
