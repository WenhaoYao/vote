package dao;

import querymodel.BaseQueryModel;

import java.util.List;

/**
 * @author yaowenhao
 * @Title: BaseDao
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/26 23:25
 */
public interface  BaseDao<T> {

    int insert(T t) throws Exception;

    int update(T t) throws Exception;

    int delete(T t) throws Exception;

    Long findId() throws Exception;

    List<T> findAll(Class<T> clazz) throws Exception;

    T findOne(T t, Class<T> clazz) throws Exception;

    List<T> findByCondition(BaseQueryModel queryModel, Class<T> clazz) throws Exception;
}
