package web.servlet;

import pojo.Subject;
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
 * @date 2018/12/20 16:25
 */
@WebServlet(name = "ViewServlet", urlPatterns = "/view")
public class ViewServlet extends HttpServlet {

    private RecordService recordService;
    private SubjectService subjectService;

    public ViewServlet() {
        this.recordService = new RecordServiceImpl();
        subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long subjectId = Long.parseLong(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/vote?id=" + subjectId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
