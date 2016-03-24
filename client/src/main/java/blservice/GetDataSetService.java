package blservice;

import org.jfree.data.xy.XYDataset;

/**
 * Created by user on 2016/3/24.
 */
public interface GetDataSetService {
    public XYDataset getStockDataSet_monthly();
    public XYDataset getStockDataSet_daily();
    public XYDataset getIndexDataSet_monthly();
    public XYDataset getIndexDataSet_daily();
}
