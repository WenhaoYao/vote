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
@WebServlet(name = "DoAddServlet", urlPatterns = "/doAdd")
public class DoAddServlet extends HttpServlet {

    private SubjectService subjectService;
    public DoAddServlet() {
        this.subjectService = new SubjectServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        Integer number = request.getParameter("voteType") != null
                ? Integer.parseInt(request.getParameter("voteType")) : null;
        String[] options = request.getParameterValues("options");
        Subject subject = new Subject();
        if (!"".equals(id)){
            subject.setId(Long.valueOf(id));
        }
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
            if (subject.getId() == null || subject.getId() == 0){
                subjectService.update(subject);
            }else {
                subjectService.add(subject, user);
            }
            response.sendRedirect(request.getContextPath() + "/list");
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
