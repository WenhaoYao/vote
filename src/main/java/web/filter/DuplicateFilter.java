package web.filter;

import dao.RecordDao;
import dao.impl.RecordDaoImpl;
import pojo.Record;
import pojo.Subject;
import pojo.User;
import querymodel.RecordQueryModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yaowenhao
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/20 13:17
 */
@WebFilter(filterName = "DuplicateFilter", urlPatterns = "/vote")
public class DuplicateFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter.............");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        RecordDao recordDao = new RecordDaoImpl();
        long subjectId = Long.parseLong(request.getParameter("id"));
        Subject subject = new Subject();
        subject.setId(subjectId);
        User user = (User) request.getSession().getAttribute("user");
        RecordQueryModel recordQueryModel = new RecordQueryModel();
        recordQueryModel.setUser(user);
        recordQueryModel.setSubject(subject);
        try {
            if (recordDao.findByCondition(recordQueryModel, Record.class) == null){
                chain.doFilter(request, response);
            }else {
                request.setAttribute("duplicateWarn", "请勿重复投票");
                request.getRequestDispatcher("/vote?id=" + subjectId).forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
