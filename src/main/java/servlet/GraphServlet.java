package servlet;

import database.GetIndexData_DB;
import po.IndexPO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zmj on 2016/5/8.
 */
public class GraphServlet extends HttpServlet {

        public GraphServlet() {
            super();
            System.out.println("GraphServlet.GraphServlet");
        }

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            GetIndexData_DB getIndexData_db = new GetIndexData_DB();
            IndexPO indexPO = getIndexData_db.getLatestIndexData();
            long volume[]=indexPO.getVolume();
            double[] high =indexPO.getHigh();
            double[] adj_price =indexPO.getAdj_price();
            double[] low = indexPO.getLow();
            double[] close =indexPO.getClose();
            double[] open = indexPO.getOpen();
            double[] increase_decreaseRate = indexPO.getIncrease_decreaseRate();
            double[] increase_decreaseNum = indexPO.getIncrease_decreaseNum();
            String date[]=indexPO.getDate();

            request.setAttribute("volume",volume);
            request.setAttribute("date",date);
            request.setAttribute("high",high);
            request.setAttribute("adj_price",adj_price);
            request.setAttribute("low",low);
            request.setAttribute("close",close);
            request.setAttribute("open",open);
            request.setAttribute("increase_decreaseRate",increase_decreaseRate);
            request.setAttribute("increase_decreaseNum",increase_decreaseNum);

        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            super.doGet(request, response);


        }


}
