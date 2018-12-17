package dao.impl;

import dao.OptionDao;
import querymodel.BaseQueryModel;
import pojo.Option;
import util.ReturnSqlUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author yaowenhao
 * @Title OptionDaoImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 22:11
 */
public class OptionDaoImpl extends BaseDaoImpl<Option> implements OptionDao {
    @Override
    public List getInserSql(Option option) {
        String sql = "insert into t_option (content, idx, subjectId) values (?, ?, ?)";
        Object[] objects = {option.getContent(), option.getIdx(), option.getSubject().getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getUpdateSql(Option option) {
        return null;
    }

    @Override
    public List getDeleteSql(Option option) {
        return null;
    }

    @Override
    public List getFindOneSql(Option option) {
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
