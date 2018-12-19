package dao.impl;

import dao.SubjectDao;
import pojo.Subject;
import querymodel.BaseQueryModel;
import querymodel.SubjectQueryModel;
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
        Object[] objects = {subject.getTitle(), subject.getNumber(), subject.getStartTime(), subject.getEndTime(), subject.getUser().getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getUpdateSql(Subject subject) {
        String sql = "update t_subject set title=?, number=? where id=?";
        Object[] objects = {subject.getTitle(), subject.getNumber(), subject.getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getDeleteSql(Subject subject) {
        return null;
    }

    @Override
    public List getFindOneSql(Subject subject) {
        String sql = "select * from t_subject where id=?";
        Object[] objects = {subject.getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public String getFindAllSql() {
        String sql = "select id, title, number, starttime, endtime, userId from t_subject";
        return sql;
    }

    @Override
    public List getNumbersSql(BaseQueryModel queryModel) {
        return null;
    }

    @Override
    public List getFindConditionSql(BaseQueryModel queryModel) {
        ArrayList<Object> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        SubjectQueryModel subjectQueryModel = (SubjectQueryModel) queryModel;
        buffer.append("select * from t_subject where 1=1 ");
        if (subjectQueryModel.getUser() != null) {
            buffer.append("and userId=? ");
            params.add(subjectQueryModel.getUser().getId());
        }
        if (subjectQueryModel.getTitle() != null
                && subjectQueryModel.getTitle().trim().length() > 0) {
            buffer.append("and title=? ");
            params.add(subjectQueryModel.getTitle());
        }
        list.add(buffer.toString());
        list.add(params.toArray());
        return list;
    }
}
