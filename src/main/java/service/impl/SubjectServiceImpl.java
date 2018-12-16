package service.impl;

import dao.OptionDao;
import dao.SubjectDao;
import dao.impl.OptionDaoImpl;
import dao.impl.SubjectDaoImpl;
import exception.RuleException;
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
    public void add(Subject subject, User user) throws RuleException {
        if (subject.getTitle() == null || subject.getTitle().trim().length() == 0){
            throw new RuleException("标题不能为空");
        }
        if(subject.getNumber() == null){
            throw new RuleException("请选择类型");
        }
        for (Option option:
                subject.getOptionList()) {
            if (option == null || "".equals(option.getContent())){
                throw new RuleException("请将选项填写完整");
            }
        }
        try{
            Long currentTime = System.currentTimeMillis();
            subject.setStartTime(currentTime);
            subject.setEndTime(currentTime + 24 * 60 * 60 * 1000);
            subject.setUser(user);
            subjectDao.insert(subject);
            subject.setId(subjectDao.findId().longValue());
            int i = 1;
            for (Option option : subject.getOptionList()) {
                option.setIdx(i++);
                option.setSubject(subject);
                optionDao.insert(option);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
