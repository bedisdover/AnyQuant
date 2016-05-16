package bl;

import database.GetBusinessNews;
import po.BusinessNewsPO;
import vo.BusinessNewsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowBusinessNews {
    /**
     * @return List<BusinessNewsVO>
     * 得到银行行业新闻
     */
    public List<BusinessNewsVO> showBusinessNews(){
        GetBusinessNews getBusinessNews = new GetBusinessNews();
        List<BusinessNewsPO> list = getBusinessNews.getBusinessNews();
        List<BusinessNewsVO> news = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            BusinessNewsVO businessNewsVO = new BusinessNewsVO(list.get(i));
            news.add(businessNewsVO);
        }
        return news;
    }
}
