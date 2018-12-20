package service.impl;

import dao.RecordDao;
import dao.impl.RecordDaoImpl;
import exception.RuleException;
import pojo.Option;
import pojo.Record;
import pojo.Subject;
import pojo.User;
import service.RecordService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaowenhao
 * @Title RecordServiceImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/20 11:01
 */
public class RecordServiceImpl implements RecordService {

    RecordDao recordDao;

    public RecordServiceImpl() {
        this.recordDao = new RecordDaoImpl();
    }

    @Override
    public void vote(Subject subject, String[] selections, User user) throws RuleException {
        if (selections == null || selections.length == 0) {
            throw new RuleException("请选择至少一个选项");
        }
        List<Option> optionList = new ArrayList<>();
        for (String select:
             selections) {
            Option option = new Option();
            option.setId(Long.parseLong(select));
            optionList.add(option);
        }
        Record record = new Record();
        record.setSubject(subject);
        record.setUser(user);
        try {
            for (Option option:
                 optionList) {
                record.setOption(option);
                recordDao.insert(record);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
