package filter;

import bl.ManageSelfSelectStock;
import bl.SelfSelectStock;
import bl.ShowStockData;
import vo.StockNewsVO;
import vo.StockVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
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

//        if (request.getAttribute("UserId") != null) {
        ManageSelfSelectStock selfSelectStock = new ManageSelfSelectStock();
        List<String> stockID = selfSelectStock.getAllInterestedStock("123456789@qq.com");

        ShowStockData stockData = new ShowStockData();
        List<StockVO> stockList = new ArrayList<>();

        for (String temp : stockID) {
            stockList.add(stockData.getStockData(temp));
        }

        request.setAttribute("stockList", stockList);
//        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
