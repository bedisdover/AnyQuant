package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("PortfolioServlet.service");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

//        SelfSelectStockService selfSelect = new SelfSelectStock();
//        List<String> stockList = selfSelect.getFollowedStocks();

//        String test = "request";

//        req.setAttribute("test", test);

        System.out.println("PortfolioServlet.doGet");
//        req.getRequestDispatcher("portfolio.jsp").forward(req, resp);
//        System.out.println("doGet....");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>test</h1>");
        writer.flush();
        writer.close();
    }
}
