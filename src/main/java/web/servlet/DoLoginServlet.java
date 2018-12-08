package web.servlet;

import exception.RuleException;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author yaowenhao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/8 15:50
 */
@WebServlet(name = "DoLoginServlet", urlPatterns = "/DoLoginServlet")
public class DoLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        ArrayList<Object> list = new ArrayList<>();
        UserService service = new UserServiceImpl();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            list.add(request.getParameter(paramName));
        }
        user.setName((String) list.get(0));
        user.setPassword((String) list.get(1));
        try {
            service.login(user);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
        } catch (RuleException e) {
            request.setAttribute("user", user);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
