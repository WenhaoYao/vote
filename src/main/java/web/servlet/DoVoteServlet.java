package web.servlet;

import exception.RuleException;
import pojo.Subject;
import pojo.User;
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
 * @date 2018/12/18 11:16
 */
@WebServlet(name = "DoVoteServlet", urlPatterns = "/doVote")
public class DoVoteServlet extends HttpServlet {

    private RecordService recordService;
    private SubjectService subjectService;

    public DoVoteServlet() {
        this.recordService = new RecordServiceImpl();
        this.subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        long subjectId = Long.parseLong(request.getParameter("id"));
        Subject subject = new Subject();
        subject.setId(subjectId);
        String[] selections = request.getParameterValues("options");
        try {
            subject = subjectService.getVoteSubject(subject);
            recordService.vote(subject, selections, user);
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (RuleException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("jsp/vote.jsp").forward(request, response);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
