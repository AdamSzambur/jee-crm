package pl.coderslab.controllers.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");

        // attribute 'cat' needed to set actual catalogue in main menu
        String[] uriTab = request.getRequestURI().split("/");
        request.setAttribute("cat", uriTab[uriTab.length - 2]);
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
