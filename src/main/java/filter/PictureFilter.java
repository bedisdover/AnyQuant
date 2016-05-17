package filter;

import bl.SortStock;
import blservice.SortStockService;
import vo.StockVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-5-17.
 *
 * 加载各种榜单
 */
@WebFilter(filterName = "PictureFilter")
public class PictureFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("PictureFilter.doFilter");

        SortStockService sortStock = new SortStock();

        List<StockVO> increase_rank = sortStock.increase_sort();
        List<StockVO> decrease_rank = sortStock.decrease_sort();
        List<StockVO> volume_rank = sortStock.volume_sort();

        request.setAttribute("increase_rank", increase_rank);
        request.setAttribute("decrease_rank", decrease_rank);
        request.setAttribute("volume_rank", volume_rank);

        System.out.println(increase_rank);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
