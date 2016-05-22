package servlet;

import bl.SelfSelectStock;
import blservice.SelfSelectStockService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
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
        String test = "request----1";

        if (req.getAttribute("test") == null) {
            req.setAttribute("test", test);
        }

        BufferedReader reader = req.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        List<String> list = new ArrayList<>();

        list.add("123");
        list.add("456");
        list.add("789");

        PrintWriter out = resp.getWriter();
        out.println("{name: zhangsan, age: 30}");

//        req.getRequestDispatcher("test/testAjax.jsp").forward(req, resp);
//        req.getRequestDispatcher("portfolio.jsp").include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
