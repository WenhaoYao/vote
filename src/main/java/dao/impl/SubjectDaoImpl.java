package dao.impl;

import dao.SubjectDao;
import pojo.BaseQueryModel;
import pojo.Subject;
import util.ReturnSqlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaowenhao
 * @Title SubjectDaoImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 22:10
 */
public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao {
    @Override
    public List getInserSql(Subject subject) {
        String sql = "insert into t_subject (title, number, starttime, endtime, userId) values (?, ?, ?, ?, ?)";
        Object[] objects = {subject.getTitle(), subject.getNumber(), subject.getStartTime(),subject.getEndTime(), subject.getUser().getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getUpdateSql(Subject subject) {
        return null;
    }

    @Override
    public List getDeleteSql(Subject subject) {
        return null;
    }

    @Override
    public List getFindOneSql(Subject subject) {
        return null;
    }

    @Override
    public String getFindAllSql() {
        return null;
    }

    @Override
    public List getFindConditionSql(BaseQueryModel queryModel) {
        return null;
    }
}
