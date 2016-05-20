package filter;

import bl.SortStock;
import blservice.SortStockService;
import vo.StockVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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
        HttpSession session = request.getSession();

        SortStockService sortStock = null;
        try {
            sortStock = new SortStock();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<StockVO> increase_rank = sortStock.increase_sort();
        List<StockVO> decrease_rank = sortStock.decrease_sort();
        List<StockVO> volume_rank = sortStock.volume_sort();

        //三个榜单同时加载，只需判断一个即可
        if (session.getAttribute("increase_rank") == null) {
            session.setAttribute("increase_rank", increase_rank);
            session.setAttribute("decrease_rank", decrease_rank);
            session.setAttribute("volume_rank", volume_rank);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
