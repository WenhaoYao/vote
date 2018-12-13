package service.impl;

import dao.OptionDao;
import dao.SubjectDao;
import dao.impl.OptionDaoImpl;
import dao.impl.SubjectDaoImpl;
import pojo.Option;
import pojo.Subject;
import pojo.User;
import service.SubjectService;

/**
 * @author yaowenhao
 * @Title SubjectServiceImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 22:27
 */
public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;
    private OptionDao optionDao;

    public SubjectServiceImpl() {
        this.subjectDao = new SubjectDaoImpl();
        this.optionDao = new OptionDaoImpl();
    }

    @Override
    public void add(Subject subject, User user) throws Exception {
        Long currentTime = System.currentTimeMillis();
        subject.setStartTime(currentTime);
        subject.setEndTime(currentTime + 24 * 60 * 60 * 1000);
        subject.setUser(user);
        subject.setId(subjectDao.findId());
        int i = 1;
        for (Option option : subject.getOptionList()) {
            option.setIdx(i++);
            option.getSubject().setId(subject.getId());

            optionDao.insert(option);
        }
    }
}
