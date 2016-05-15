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

        if (request.getAttribute("UserSession") != null) {
            request.setAttribute("stockList", stockList);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
