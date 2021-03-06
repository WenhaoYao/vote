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

/**
 * @author YaoWenHao
 * @Title: ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/5 15:23
 */
@WebServlet(name = "DoRegisterServlet", urlPatterns = "/DoRegisterServlet")
public class DoRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        String confirmPassword = request.getParameter("confirmPwd");
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        UserService userService = new UserServiceImpl();
        try {
            userService.register(user);
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        } catch (RuleException e) {
            request.setAttribute("user", user);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
