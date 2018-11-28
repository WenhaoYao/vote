package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author yaowenhao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:44
 */
@WebFilter(filterName = "ExceptionFilter", dispatcherTypes = DispatcherType.ERROR)
public class ExceptionFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
