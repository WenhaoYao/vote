package dao.impl;

import dao.OptionDao;
import pojo.Subject;
import querymodel.BaseQueryModel;
import pojo.Option;
import querymodel.OptionQueryModel;
import util.ReturnSqlUtil;

import java.io.Serializable;
import java.util.ArrayList;
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
    public List getNumbersSql(BaseQueryModel queryModel) {
        ArrayList<Object> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        OptionQueryModel optionQueryModel = (OptionQueryModel) queryModel;
        buffer.append("select count(*) from t_option where 1=1 ");
        if (optionQueryModel.getSubject() != null){
            buffer.append(" and subjectId=?");
            params.add(optionQueryModel.getSubject().getId());
        }
        list.add(buffer.toString());
        list.add(params.toArray());
        return list;
    }


    @Override
    public List getFindConditionSql(BaseQueryModel queryModel) {
        ArrayList<Object> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        OptionQueryModel optionQueryModel = (OptionQueryModel) queryModel;
        buffer.append("select * from t_option where 1=1 ");
        if(optionQueryModel.getId() != null && optionQueryModel.getId() > 0){
            buffer.append(" and id=?");
            params.add(optionQueryModel.getId());
        }
        if (optionQueryModel.getIdx() > 0){
            buffer.append(" and idx=?");
            params.add(optionQueryModel.getIdx());
        }
        if (optionQueryModel.getSubject() != null){
            buffer.append(" and subjectId=?");
            params.add(optionQueryModel.getSubject().getId());
        }
        list.add(buffer.toString());
        list.add(params.toArray());
        return list;
    }
}
