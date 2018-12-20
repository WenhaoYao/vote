package dao.impl;

import dao.BaseDao;
import dao.RecordDao;
import pojo.Record;
import querymodel.BaseQueryModel;
import querymodel.RecordQueryModel;
import util.ReturnSqlUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yaowenhao
 * @Title RecordDaoImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/20 11:00
 */
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {

    @Override
    public List getInserSql(Record record) {
        String sql = "insert into t_record (subjectId, optionId, userId) values (?, ?, ?)";
        Object[] objects = {record.getSubject().getId(), record.getOption().getId(), record.getUser().getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getUpdateSql(Record record) {
        return null;
    }

    @Override
    public List getDeleteSql(Record record) {
        String sql = "delete from t_record where subjectId=?";
        Object[] objects = {record.getSubject().getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getFindOneSql(Record record) {
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
        RecordQueryModel recordQueryModel = (RecordQueryModel) queryModel;
        buffer.append("select count(*) from t_record where 1=1 ");
        if (recordQueryModel.getSubject() != null) {
            buffer.append("and subjectId=? ");
            params.add(recordQueryModel.getSubject().getId());
        }
        if (recordQueryModel.getUser() != null) {
            buffer.append("and userId=? ");
            params.add(recordQueryModel.getUser().getId());
        }
        if (recordQueryModel.getOption() != null) {
            buffer.append("and optionId=? ");
            params.add(recordQueryModel.getOption().getId());
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
        RecordQueryModel recordQueryModel = (RecordQueryModel) queryModel;
        buffer.append("select * from t_record where 1=1 ");
        if (recordQueryModel.getUser() != null) {
            buffer.append("and userId=? ");
            params.add(recordQueryModel.getUser().getId());
        }
        if (recordQueryModel.getSubject() != null) {
            buffer.append("and subjectId=? ");
            params.add(recordQueryModel.getSubject().getId());
        }
        if (recordQueryModel.getOption() != null) {
            buffer.append("and optionId=? ");
            params.add(recordQueryModel.getOption().getId());
        }
        list.add(buffer.toString());
        list.add(params.toArray());
        return list;
    }
}
