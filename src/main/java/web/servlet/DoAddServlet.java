package web.servlet;

import exception.RuleException;
import pojo.Option;
import pojo.Subject;
import pojo.User;
import service.SubjectService;
import service.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author yaowenhao
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/15 22:13
 */
@WebServlet(name = "DoAddServlet", urlPatterns = "/Add")
public class DoAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectService subjectService = new SubjectServiceImpl();
        String title = request.getParameter("title");
        Integer number = request.getParameter("voteType") != null
                ? Integer.parseInt(request.getParameter("voteType")) : null;
        String[] options = request.getParameterValues("options");
        Subject subject = new Subject();
        subject.setTitle(title);
        subject.setNumber(number);
        for (String optionContent:
                options) {
            Option option = new Option();
            option.setContent(optionContent);
            subject.getOptionList().add(option);
        }
        User user = (User) request.getSession().getAttribute("user");
        try{
            subjectService.add(subject, user);
            response.sendRedirect("jsp/list.jsp");
        }catch (RuleException e){
            request.setAttribute("subject", subject);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}