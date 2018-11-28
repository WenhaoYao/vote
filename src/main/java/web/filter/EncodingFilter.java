package web.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author yaowenhao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:28
 */
@javax.servlet.annotation.WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Logger logger = Logger.getLogger("EncodingFilter");
        logger.info("EncodingFilter executed");
    }

    @Override
    public void destroy() {
        Logger logger = Logger.getLogger("EncodingFilter");
        logger.info("EncodingFilter destroyed");
    }

    @Override
    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }

}
