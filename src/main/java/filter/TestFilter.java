package filter;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-5-14.
 * <p>
 * 过滤器
 */
@WebFilter(filterName = "TestFilter")
public class TestFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("test", "test_value");
        SelfSelectStockService selfSelect = new SelfSelectStock();
        List<String> stockList = selfSelect.getFollowedStocks();
        stockList.add(0, "1234567");
        System.out.println(stockList);

        request.setAttribute("list", stockList);
        System.out.println("TestFilter.doFilter");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
