package filter;

import database.GetIndexData_DB;
import po.IndexPO;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zmj on 2016/5/14.
 */
@WebFilter(filterName = "GraphFilter")
public class GraphFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
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
        System.out.println(volume.length+"volume");
        System.out.println(date.length+"date");

        double[] highshort=new double[10];
        double[] lowshort=new double[10];
        double[] openshort=new double[10];
        double[] closeshort=new double[10];
        String[] dateshort=new String[10];
        for(int i=0;i<10;i++){
            highshort[i]=high[i];
            lowshort[i]=low[i];
            openshort[i]=open[i];
            closeshort[i]=close[i];
            dateshort[i]=date[i];

        }

        request.setAttribute("dateshort",dateshort);
        request.setAttribute("highshort",highshort);
        request.setAttribute("lowshort",lowshort);
        request.setAttribute("closeshort",closeshort);
        request.setAttribute("openshort",openshort);

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
