package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by song on 16-5-14.
 *
 * 用户登陆状态过滤器
 * 判断用户是否登陆，若未登陆，转到登陆界面
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

//        System.out.println("request.contextPath:" + request.getContextPath() + "-----++++++");
//        System.out.println(request.getPathInfo());
//        System.out.println(request.getPathTranslated());
//        System.out.println(request.getRequestURL());
        System.out.println(request.getServletPath());
        if (session.getAttribute("UserSession") == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
