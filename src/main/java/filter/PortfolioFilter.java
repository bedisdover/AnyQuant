package filter;

import bl.SelfSelectStock;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-5-14.
 *
 * 加载自选股票列表
 */
@WebFilter(filterName = "PortfolioFilter")
public class PortfolioFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        SelfSelectStock selfSelectStock = new SelfSelectStock();
        List<String> stockList = selfSelectStock.getFollowedStocks();

        stockList.add("sh600004");
        stockList.add("sh600005");
        stockList.add("sh600006");

        //todo 登陆状态
//        if (request.getAttribute("UserSession") != null) {
            request.setAttribute("stockList", stockList);
//        }

        System.out.println("PortfolioFilter.doFilter");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
