package DaoTest;

import dao.impl.BaseDaoImpl;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

/**
 * @author YaoWenHao
 * @Title: UserDaoImplTest
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/3 10:44
 */
public class UserDaoImplTest {

    BaseDaoImpl<User> userDao = new UserDaoImpl();

    @Test
    public void TestInsert() throws Exception {
        User user = new User();
        user.setName("张三");
        user.setPassword("12345");
        System.out.println(userDao.insert(user));
    }

    @Test
    public void TestFindOne() throws Exception {
        User user = new User();
        user.setName("yaowenhao");
        System.out.println(userDao.findOne(user, User.class).toString());
    }

}
