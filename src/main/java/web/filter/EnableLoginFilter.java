package web.filter;

import dao.BaseDao;
import dao.impl.UserDaoImpl;
import exception.RuleException;
import pojo.User;
import querymodel.UserQueryModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * @author yaowenhao
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/8 22:45
 */
@WebFilter(filterName = "EnableLoginFilter"
        , urlPatterns = "/*"
        , initParams = {@WebInitParam(name = "sessionName", value = "user")
        , @WebInitParam(name = "DoLoginServlet", value = "DoLoginServlet")
        , @WebInitParam(name = "DoRegisterServlet", value = "DoRegisterServlet")
        , @WebInitParam(name = "LoginServlet", value = "LoginServlet")
        , @WebInitParam(name = "RegisterServlet", value = "RegisterServlet")
        , @WebInitParam(name = "login", value = "login.jsp")
        , @WebInitParam(name = "register", value = "register.jsp")})
public class EnableLoginFilter implements Filter {

    private String name = null;
    private String password = null;
    private String sessionName;
    private BaseDao<User> userDao;
    private String[] params;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        int isLoginOrRegister = 0;
        HttpSession session = request.getSession();
        String urlString = request.getRequestURI();
        if (urlString.endsWith(".css") || urlString.endsWith(".gif") || urlString.endsWith(".jpg")){
            chain.doFilter(request, response);
            return;
        }
//        判断是否为登录注册界面
        for (String param :
                params) {
            if (urlString.endsWith(param)) {
                isLoginOrRegister = 1;
                break;
            }
        }
        if (isLoginOrRegister == 1) {
//            当前访问的是登录注册界面且不存在session直接放行
//            否则直接跳转到list.jsp中
            if (session.getAttribute(sessionName) == null) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/jsp/list.jsp");
            }
        } else {
//            当前访问的不是登录注册界面
//            不存在session
            if (session.getAttribute(sessionName) == null) {
                Cookie[] cookies = request.getCookies();
//                判断浏览器中是否存在对应的用户cookie进行登录
//                存在cookie且登陆成功则进行放行
                if (cookies != null && cookies.length > 1) {
                    for (int i = 0; i < cookies.length; i++) {
                        if ("name".equals(cookies[i].getName())) {
                            name = cookies[i].getValue();
                        }
                        if ("password".equals(cookies[i].getName())) {
                            password = cookies[i].getValue();
                        }
                    }
                    if (name != null && password != null) {
                        User user = new User();
                        user.setName(name);
                        user.setPassword(password);
                        UserQueryModel queryModel = new UserQueryModel();
                        queryModel.setName(name);
                        queryModel.setPassword(password);
                        try {
                            List list = userDao.findByCondition(queryModel, User.class);
//                            登陆成功则不跳转直接放行，同时把session放入容器中
//                            登录失败则跳转到登录界面
                            if (list != null) {
                                user = (User) list.get(0);
                                session.setAttribute("user", user);
                                chain.doFilter(request, response);
                            } else {
                                throw new RuleException("cookie失效");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        sessionName = config.getInitParameter("sessionName");
        params = new String[7];
        Enumeration enumeration = config.getInitParameterNames();
        int i = 0;
        while (enumeration.hasMoreElements()) {
            params[i++] = config.getInitParameter((String) enumeration.nextElement());
        }
        userDao = new UserDaoImpl();
    }

}
