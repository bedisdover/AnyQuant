package blservice;

import vo.StockVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2016/3/19.
 */
public interface SortStockService {
    public List<StockVO> increase_sort() throws IOException;
    public List<StockVO> decrease_sort() throws IOException;
    public List<StockVO> volume_sort() throws IOException;
}
