package filter;

import bl.ManageSelfSelectStock;
import bl.ShowStockData;
import bl.ShowStockIDName;
import vo.StockIDNameVO;
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
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

//        if (request.getAttribute("UserId") != null) {
//  todo 判断是否登录
//        }

        if (session.getAttribute("stockList") == null) {
            loadPortfolio(session, "123456789@qq.com");
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
     * @param ID      用户ID
     * @throws IOException
     */
    private void loadPortfolio(HttpSession session, String ID) throws IOException {
        ManageSelfSelectStock selfSelectStock = new ManageSelfSelectStock();
        List<String> stockID = selfSelectStock.getAllInterestedStock(ID);

        ShowStockData stockData = new ShowStockData();
        List<StockVO> stockList = new ArrayList<>();

        for (String temp : stockID) {
            stockList.add(stockData.getStockData(temp));
        }

        session.setAttribute("stockList", stockList);
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
