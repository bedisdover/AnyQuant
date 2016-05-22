package filter;

import bl.ManageSelfSelectStock;
import bl.ShowStockData;
import bl.ShowStockIDName;
import bl.ShowStockNews;
import vo.StockIDNameVO;
import vo.StockNewsVO;
import vo.StockVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 16-5-14.
 * <p>
 * 加载自选股票列表
 */
@WebFilter(filterName = "PortfolioFilter")
public class PortfolioFilter implements Filter {

    private List<String> stockIDList;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

//        if (request.getAttribute("UserId") != null) {
//  todo 判断是否登录
        ManageSelfSelectStock selfSelectStock = new ManageSelfSelectStock();
        stockIDList = selfSelectStock.getAllInterestedStock("123456789@qq.com");
//        }

        if (session.getAttribute("stockList") == null) {
            loadPortfolio(session);
            loadNews(session);
        }
        if (session.getAttribute("stockIDNameList") == null) {
            loadStockNameList(session);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * 加载自选股列表
     *
     * @param session session对象
     * @throws IOException
     */
    private void loadPortfolio(HttpSession session) throws IOException {

        ShowStockData stockData = new ShowStockData();
        List<StockVO> stockList = new ArrayList<>();

        for (String temp : stockIDList) {
            stockList.add(stockData.getStockData(temp));
        }

        session.setAttribute("stockList", stockList);
    }

    /**
     * 加载股票新闻
     *
     * @param session session对象
     */
    private void loadNews(HttpSession session) {
        ShowStockNews stockNews = new ShowStockNews();

        for (String id : stockIDList) {
            session.setAttribute("news_" + id, stockNews.showStockNews(id));
        }
    }

    /**
     * 加载所有股票名称代码列表
     *
     * @param session session对象
     */
    private void loadStockNameList(HttpSession session) {
        ShowStockIDName stockIDName = new ShowStockIDName();

        List<StockIDNameVO> stockIDNameList = stockIDName.getAllStockIdAndName();

        session.setAttribute("stockIDNameList", stockIDNameList);
    }
}
