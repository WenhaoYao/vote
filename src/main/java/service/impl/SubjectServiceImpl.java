package service.impl;

import dao.OptionDao;
import dao.RecordDao;
import dao.SubjectDao;
import dao.impl.OptionDaoImpl;
import dao.impl.RecordDaoImpl;
import dao.impl.SubjectDaoImpl;
import exception.RuleException;
import pojo.Option;
import pojo.Record;
import pojo.Subject;
import pojo.User;
import querymodel.OptionQueryModel;
import querymodel.RecordQueryModel;
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
    private RecordDao recordDao;

    public SubjectServiceImpl() {
        this.subjectDao = new SubjectDaoImpl();
        this.optionDao = new OptionDaoImpl();
        this.recordDao = new RecordDaoImpl();
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
    public Subject getVoteSubject(Subject subject) throws Exception {
        OptionQueryModel optionQueryModel = new OptionQueryModel();
        RecordQueryModel recordQueryModel = new RecordQueryModel();
        subject = subjectDao.findOne(subject, Subject.class);
        recordQueryModel.setSubject(subject);
        optionQueryModel.setSubject(subject);
        subject.setOptionList(optionDao.findByCondition(optionQueryModel, Option.class));
        subject.setRecordNumbers(recordDao.findNumbers(recordQueryModel).intValue());
        return subject;
    }

    @Override
    public List<Subject> list(User user) throws Exception {
        List<Subject> subjectList;
        if (user != null){
            SubjectQueryModel subjectQueryModel = new SubjectQueryModel();
            subjectQueryModel.setUser(user);
            subjectList = subjectDao.findByCondition(subjectQueryModel, Subject.class);
        }else {
            subjectList = subjectDao.findAll(Subject.class);
        }
        OptionQueryModel optionQueryModel = new OptionQueryModel();
        RecordQueryModel recordQueryModel = new RecordQueryModel();
        for (Subject subject:
             subjectList) {
            optionQueryModel.setSubject(subject);
            recordQueryModel.setSubject(subject);
            subject.setOptionNumbers(optionDao.findNumbers(optionQueryModel).intValue());
            subject.setRecordNumbers(recordDao.findNumbers(recordQueryModel).intValue());
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
        Record record = new Record();
        option.setSubject(subject);
        record.setSubject(subject);
        try {
            recordDao.delete(record);
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
