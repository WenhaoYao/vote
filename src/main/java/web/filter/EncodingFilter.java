package web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.MyRequestUtil;

import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yaowenhao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:28
 */
@javax.servlet.annotation.WebFilter(filterName = "EncodingFilter"
        , urlPatterns = "/*"
        , initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class EncodingFilter implements javax.servlet.Filter {

    private String encode;
    private static final Logger logger = LoggerFactory.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("EncodingFilter executed");
        encode = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
        logger.info("EncodingFilter destroyed");
    }

    @Override
    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
//        处理POST请求
        request.setCharacterEncoding(encode);
        response.setContentType("text/html;charset=utf-8");

        if("GET".equals(request.getMethod().toUpperCase())){
            MyRequestUtil myRequestUtil = new MyRequestUtil(request);
            myRequestUtil.setEncode(encode);
            request = myRequestUtil;
        }

        chain.doFilter(request, response);
    }

}
