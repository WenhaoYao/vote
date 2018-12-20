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

/**
 * @author yaowenhao
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/17 22:29
 */
@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private SubjectService subjectService;
    private RecordService recordService;

    public VoteServlet() {
        this.subjectService = new SubjectServiceImpl();
        this.recordService = new RecordServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long subjectId = Long.parseLong(request.getParameter("id"));
        Subject subject = new Subject();
        subject.setId(subjectId);
        try {
            subject = recordService.view(subjectService.getVoteSubject(subject));
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("/jsp/vote.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
