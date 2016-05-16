package bl;

import org.junit.Test;
import po.BusinessNewsPO;
import vo.BusinessNewsVO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/16.
 *
 */
public class ShowBusinessNewsTest {
    @Test
    public void showBusinessNewsTest(){
        ShowBusinessNews showBusinessNews = new ShowBusinessNews();
        List<BusinessNewsVO> list = showBusinessNews.showBusinessNews();
        assertEquals(40,list.size());
        assertEquals("2016-05-13",list.get(0).getDate()); //maybe changed
    }
}