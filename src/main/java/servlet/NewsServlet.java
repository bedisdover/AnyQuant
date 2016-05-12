package servlet;

import database.GetStockNews;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by song on 16-5-11.
 *
 * 获取新闻
 */
public class NewsServlet extends HttpServlet {
    public NewsServlet() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

        GetStockNews stockNews = new GetStockNews();
    }
}
