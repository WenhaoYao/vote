package UserServiceTest;

import exception.RuleException;
import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author YaoWenHao
 * @Title: UserServiceTest
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/7 8:59
 */
public class UserServiceTest {

    @Test
    public void TestRegister() throws RuleException {
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setName("yaowenhao");
        user.setPassword("123456");
        user.setConfirmPassword("123456");
        service.register(user);
    }
}
