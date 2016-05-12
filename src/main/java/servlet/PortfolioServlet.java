package servlet;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-5-11.
 *
 * 获取关注列表
 */
public class PortfolioServlet extends HttpServlet{

    public PortfolioServlet() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

//        SelfSelectStockService selfSelect = new SelfSelectStock();
//        List<String> stockList = selfSelect.getFollowedStocks();

//        String test = "test";

//        req.setAttribute("test", test);

//        RequestDispatcher dispatcher =req.getRequestDispatcher("http://localhost:8080/page/portfolio.jsp");
//        dispatcher.forward(req, resp);
        System.out.println("doGet....");
    }
}
