package service.impl;

import dao.OptionDao;
import dao.SubjectDao;
import dao.impl.OptionDaoImpl;
import dao.impl.SubjectDaoImpl;
import exception.RuleException;
import pojo.Option;
import pojo.Subject;
import pojo.User;
import querymodel.OptionQueryModel;
import querymodel.SubjectQueryModel;
import service.SubjectService;

import java.util.List;

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
            long currentTime = System.currentTimeMillis();
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

    @Override
    public List<Subject> list() throws Exception{
        List<Subject> subjectList;
        try {
            subjectList = subjectDao.findAll(Subject.class);
            OptionQueryModel queryModel = new OptionQueryModel();
            for (Subject subject:
                 subjectList) {
                queryModel.setSubject(subject);
                subject.setOptionNumbers(optionDao.findNumbers(queryModel).intValue());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return subjectList;
    }

    @Override
    public Subject getVoteSubject(Subject subject) throws Exception {
        OptionQueryModel queryModel = new OptionQueryModel();
        subject = subjectDao.findOne(subject, Subject.class);
        queryModel.setSubject(subject);
        subject.setOptionList(optionDao.findByCondition(queryModel, Option.class));
        return subject;
    }

    @Override
    public List<Subject> listByUser(User user) throws Exception {
        List<Subject> subjectList;
        SubjectQueryModel subjectQueryModel = new SubjectQueryModel();
        subjectQueryModel.setUser(user);
        subjectList = subjectDao.findByCondition(subjectQueryModel, Subject.class);
        OptionQueryModel optionQueryModel = new OptionQueryModel();
        for (Subject subject:
             subjectList) {
            optionQueryModel.setSubject(subject);
            subject.setOptionNumbers(optionDao.findNumbers(optionQueryModel).intValue());
        }
        return subjectList;
    }

    @Override
    public void update(Subject subject) throws RuleException {
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
        Option option = new Option();
        option.setSubject(subject);
        try {
            optionDao.delete(option);
            subjectDao.update(subject);
            int i = 1;
            for (Option o : subject.getOptionList()) {
                o.setIdx(i++);
                o.setSubject(subject);
                optionDao.insert(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
