package service;

import exception.RuleException;
import pojo.Subject;
import pojo.User;

/**
 * @author yaowenhao
 * @Title RecordService
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/20 11:00
 */
public interface RecordService {
    void vote(Subject subject, String[] selections, User user) throws RuleException;
}
