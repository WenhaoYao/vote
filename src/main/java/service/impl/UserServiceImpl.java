package service.impl;

import dao.UserDao;
import dao.impl.BaseDaoImpl;
import dao.impl.UserDaoImpl;
import exception.RuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;
import pojo.UserQueryModel;
import service.UserService;
import util.MD5Class;
import web.filter.EncodingFilter;

import java.util.List;

/**
 * @author YaoWenHao
 * @Title: UserServiceImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/7 8:36
 */
public class UserServiceImpl implements UserService {

    private BaseDaoImpl<User> userDao = new UserDaoImpl();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public int register(User user) throws RuleException {
        if (user.getName() == null || user.getName().trim().length() == 0) {
            logger.error("username cannot be null");
            throw new RuleException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().length() == 0) {
            logger.error("password cannot be null");
            throw new RuleException("密码不能为空");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            logger.error("Please confirm your password");
            throw new RuleException("请确认密码");
        }
        try {
            UserQueryModel queryModel = new UserQueryModel();
            queryModel.setName(user.getName());
            List list = userDao.findByCondition(queryModel, User.class);
            if (list.size() > 0){
                throw new RuleException("用户已被注册");
            }
            user.setOnline(1);
            user.setPassword(MD5Class.stringToMd5(user.getPassword()));
            return userDao.insert(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int login(User user) throws RuleException {
        if (user.getName() == null || user.getName().length() == 0){
            throw new RuleException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().length() == 0) {
            throw new RuleException("密码不能为空");
        }
        String password = MD5Class.stringToMd5(user.getPassword());
        try {
            User user_1 = userDao.findOne(user, User.class);
            if (user_1 == null){
                throw new RuleException("用户未注册");
            }
            if (!password.equals(user_1.getPassword())){
                throw new RuleException("用户名或者密码错误");
            }
            user_1.setOnline(1);
            return userDao.update(user_1);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
