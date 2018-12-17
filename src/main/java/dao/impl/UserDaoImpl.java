package dao.impl;

import dao.UserDao;
import querymodel.BaseQueryModel;
import pojo.User;
import querymodel.UserQueryModel;
import util.ReturnSqlUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YaoWenHao
 * @Title: UserDaoImpl
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/11/30 8:23
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public List getInserSql(User user) {
        String sql = "insert into t_user (name, password, online) values (?, ?, ?)";
        Object[] objects = {user.getName(), user.getPassword(), user.getOnline()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getUpdateSql(User user) {
        String sql = "update t_user set name=?, password=?, online=? where id=?";
        Object[] objects = {user.getName(), user.getPassword(), user.getOnline(), user.getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getDeleteSql(User user) {
        String sql = "delete from t_user where id = ?";
        Object[] objects = {user.getId()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public List getFindOneSql(User user) {
        String sql = "select * from t_user where name = ?";
        Object[] objects = {user.getName()};
        return ReturnSqlUtil.returnSql(sql, objects);
    }

    @Override
    public String getFindAllSql() {
        return "select * from t_user";
    }

    @Override
    public List getFindConditionSql(BaseQueryModel queryModel) {
        ArrayList<java.io.Serializable> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        Object[] objects = new Object[3];
        ArrayList params = new ArrayList();
        UserQueryModel userQueryModel = (UserQueryModel) queryModel;
        buffer.append("select * from t_user where 1=1 ");
        if(userQueryModel.getName() != null && userQueryModel.getName().trim().length() > 0){
            buffer.append(" and name=?");
            params.add(userQueryModel.getName());
        }
        if (userQueryModel.getPassword() != null && userQueryModel.getPassword().trim().length() > 0){
            buffer.append(" and password=?");
            params.add(userQueryModel.getPassword());
        }
        if (userQueryModel.getOnline() > 0){
            buffer.append(" online=?");
            params.add(userQueryModel.getOnline());
        }
        list.add(buffer.toString());
        list.add(params.toArray());
        return list;
    }
}
