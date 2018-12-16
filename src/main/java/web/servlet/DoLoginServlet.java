package web.servlet;

import exception.RuleException;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.MD5Class;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        int isRemember = 0;
        ArrayList<Object> list = new ArrayList<>();
        UserService service = new UserServiceImpl();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = (String) enumeration.nextElement();
            list.add(request.getParameter(paramName));
        }
        user.setName((String) list.get(0));
        user.setPassword(MD5Class.stringToMd5((String) list.get(1)));
        String remember = request.getParameter("remember");
        if (remember != null){
            isRemember = 1;
        }
        try {
            service.login(user);
            request.getSession().setAttribute("user", user);
            if(isRemember == 1){
                Cookie cookie = new Cookie("name", user.getName());
                Cookie cookie1 = new Cookie("password", user.getPassword());
                cookie.setMaxAge(864000);
                cookie1.setMaxAge(864000);
                response.addCookie(cookie);
                response.addCookie(cookie1);
            }
            request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
        } catch (RuleException e) {
            request.setAttribute("user", user);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher( "jsp/login.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
