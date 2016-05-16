package bl;

import database.GetStockNews;
import po.StockNewsPO;
import vo.StockNewsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowStockNews {
    /**
     * @param id 股票id
     * @return List<StockNewsVO>
     * 得到指定股票的新闻
     */
    public List<StockNewsVO> showStockNews(String id){
        GetStockNews getStockNews = new GetStockNews();
        List<StockNewsVO> list = new ArrayList<>();
        List<StockNewsPO> stockNewsPOs = getStockNews.getStockNews(id);
        for(int i=0;i<stockNewsPOs.size();i++){
            StockNewsVO stockNewsVO = new StockNewsVO();
            stockNewsVO.setId(stockNewsPOs.get(i).getId());
            stockNewsVO.setTitle(stockNewsPOs.get(i).getTitle());
            stockNewsVO.setContent(stockNewsPOs.get(i).getContent());
            stockNewsVO.setDate(stockNewsPOs.get(i).getDate());
            list.add(stockNewsVO);
        }
        return list;
    }
}
