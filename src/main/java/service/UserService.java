package service;

import exception.RuleException;
import pojo.User;

/**
 * @author YaoWenHao
 * @Title: UserService
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/7 8:36
 */
public interface UserService {

    int register(User user) throws RuleException;

    int login(User user);
}
