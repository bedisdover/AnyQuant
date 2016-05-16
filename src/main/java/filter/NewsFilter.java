package filter;

import bl.ShowBusinessNews;
import bl.ShowStockNews;
import database.GetStockNews;
import vo.BusinessNewsVO;
import vo.StockNewsVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-5-14.
 *
 * 负责加载新闻
 */
@WebFilter(filterName = "NewsFilter")
public class NewsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        ShowBusinessNews businessNews = new ShowBusinessNews();
        List<BusinessNewsVO> news = businessNews.showBusinessNews();

        request.setAttribute("news", news);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
