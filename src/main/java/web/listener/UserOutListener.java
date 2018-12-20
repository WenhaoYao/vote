package web.listener; /**
 * @Title ${NAME}
 * @ProjectName vote
 * @Description: TODO
 * @author yaowenhao
 * @date 2018/12/20 15:16
 */

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class UserOutListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public UserOutListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        User user = (User) se.getSession().getAttribute("user");
        UserService userService = new UserServiceImpl();
        try {
            userService.offline(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
