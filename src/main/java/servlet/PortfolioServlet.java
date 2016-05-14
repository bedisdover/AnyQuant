package servlet;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by song on 16-5-11.
 *
 * 获取关注列表
 */
public class PortfolioServlet extends HttpServlet{

    public PortfolioServlet() {
        System.out.println("PortfolioServlet.PortfolioServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        SelfSelectStockService selfSelect = new SelfSelectStock();
//        List<String> stockList = selfSelect.getFollowedStocks();

        String test = "request----1";
        System.out.println("doGet....");

        req.setAttribute("test", test);
        PrintWriter out = resp.getWriter();
//        out.println("out");
        req.getRequestDispatcher("page/portfolio.jsp").forward(req, resp);
//        req.getRequestDispatcher("portfolio.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
