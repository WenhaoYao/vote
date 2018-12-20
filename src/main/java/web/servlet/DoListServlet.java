package web.servlet;

import service.RecordService;
import service.SubjectService;
import service.impl.RecordServiceImpl;
import service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yaowenhao
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/17 14:35
 */
@WebServlet(name = "DoListServlet", urlPatterns = "/list")
public class DoListServlet extends HttpServlet {

    private SubjectService subjectService;
    private RecordService recordService;

    public DoListServlet() {
        this.subjectService = new SubjectServiceImpl();
        this.recordService = new RecordServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List subjectList = subjectService.list(null);
            request.setAttribute("subjectList", subjectList);
            request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
