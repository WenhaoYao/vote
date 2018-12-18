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

    List<Subject> list() throws Exception;

    Subject getVoteSubject(Subject subject) throws Exception;
}
