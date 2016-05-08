package blservice;

import org.jfree.data.xy.XYDataset;
import vo.IndexVO;

import java.io.IOException;

/**
 * Created by user on 2016/3/24.
 */
public interface GetDataSetService {
    public XYDataset getStockDataSet_monthly();
    public XYDataset getStockDataSet_daily();
    public IndexVO getIndexDataSet() throws IOException;
    public XYDataset getIndexDataSet_daily();
}
