package service;

import exception.RuleException;
import pojo.Subject;
import pojo.User;

import java.util.List;

/**
 * @author yaowenhao
 * @Title SubjectService
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 22:26
 */
public interface SubjectService {
    void add(Subject subject, User user) throws RuleException;

    Subject getVoteSubject(Subject subject) throws Exception;

    List<Subject> list(User user) throws Exception;

    void update(Subject subject) throws Exception;
}
