package web.servlet;

import pojo.Subject;
import service.SubjectService;
import service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YaoWenHao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/19 14:51
 */
@WebServlet(name = "DoModifyServlet", urlPatterns = "/doModify")
public class DoModifyServlet extends HttpServlet {

    private SubjectService subjectService;

    public DoModifyServlet() {
        this.subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long subjectId = Long.parseLong(request.getParameter("id"));
        Subject subject = new Subject();
        subject.setId(subjectId);
        try {
            subject = subjectService.getVoteSubject(subject);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
