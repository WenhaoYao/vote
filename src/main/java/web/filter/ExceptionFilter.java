package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author yaowenhao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:44
 */
@WebFilter(filterName = "ExceptionFilter"
        , dispatcherTypes = DispatcherType.ERROR
        , initParams = {@WebInitParam(name = "", value = "")
        , @WebInitParam(name = "errorPage", value = "")})
public class ExceptionFilter implements Filter {

    private String errorPage;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        try {
            chain.doFilter(req, resp);
        } catch (Exception e){
            req.setAttribute("exception", e);
            req.getRequestDispatcher(errorPage).forward(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.errorPage = config.getInitParameter("errorPage");
    }

}
