package web.filter;

import util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author YaoWenHao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 9:13
 */
@WebFilter(filterName = "TransactionFilter", urlPatterns = "/*")
public class TransactionFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            JdbcUtil.beginTransaction();
            chain.doFilter(req, resp);
            JdbcUtil.commitTrascation();
        } catch (SQLException e) {
            try {
                JdbcUtil.rollTrasaction();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        } finally {
            try {
                JdbcUtil.releaseConnection(JdbcUtil.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
