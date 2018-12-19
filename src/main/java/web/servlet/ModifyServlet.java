package web.servlet;

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
import java.util.List;

/**
 * @author YaoWenHao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/19 14:17
 */
@WebServlet(name = "ModifyServlet", urlPatterns = "/modify")
public class ModifyServlet extends HttpServlet {

    SubjectService subjectService;

    public ModifyServlet() {
        this.subjectService = new SubjectServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Subject subject = new Subject();
        subject.setUser(user);
        try {
            List<Subject> subjectListByUser = subjectService.listByUser(user);
            request.setAttribute("subjectList", subjectListByUser);
            request.getRequestDispatcher("jsp/modify.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
