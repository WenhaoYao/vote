package dao.impl;

import dao.BaseDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.BaseQueryModel;
import util.QueryUtil;

import java.util.List;

/**
 * @author yaowenhao
 * @Title: BaseDaoImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:26
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    QueryRunner queryUtil = new QueryUtil();

    @Override
    public int insert(T t) throws Exception {
        List list = getInserSql(t);
        String sql = (String) list.get(0);
        Object[] objects = (Object[]) list.get(1);
        return queryUtil.update(sql, objects);
    }

    @Override
    public int update(T t) throws Exception {
        List list = getUpdateSql(t);
        String sql = (String) list.get(0);
        Object[] objects = (Object[]) list.get(1);
        return (int) queryUtil.update(sql, objects);
    }

    @Override
    public int delete(T t) throws Exception {
        List list = getDeleteSql(t);
        String sql = (String) list.get(0);
        Object[] objects = (Object[]) list.get(1);
        return (int) queryUtil.update(sql, objects);
    }

    @Override
    public List<T> findAll(Class<T> clazz) throws Exception {
        List<T> list = queryUtil.query(getFindAllSql(), new BeanListHandler<>(clazz));
        return list;
    }

    @Override
    public T findOne(T t, Class<T> clazz) throws Exception {
        List list = getFindOneSql(t);
        String sql = (String) list.get(0);
        Object[] objects = (Object[]) list.get(1);
        return queryUtil.query(sql, new BeanHandler<>(clazz), objects);
    }

    @Override
    public List<T> findByCondition(BaseQueryModel queryModel, Class<T> clazz) throws Exception {
        List list = getFindConditionSql(queryModel);
        String sql = (String) list.get(0);
        Object[] objects = (Object[]) list.get(1);
        return queryUtil.query(sql, new BeanListHandler<>(clazz), objects);
    }

    public abstract List getInserSql(T t);

    public abstract List getUpdateSql(T t);

    public abstract List getDeleteSql(T t);

    public abstract List getFindOneSql(T t);

    public abstract String getFindAllSql();

    public abstract List getFindConditionSql(BaseQueryModel queryModel);
}
