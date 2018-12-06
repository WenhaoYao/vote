package dao.impl;

import dao.UserDao;
import pojo.BaseQueryModel;
import pojo.User;
import pojo.UserQueryModel;

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
        String sql = "insert into t_user (name, pwd, online) values (?, ?, ?)";
        Object[] objects = {user.getName(), user.getPassword(), user.getOnline()};
        List<java.io.Serializable> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
    }

    @Override
    public List getUpdateSql(User user) {
        String sql = "update t_user (name, pwd, online) values (?, ?, ?)";
        Object[] objects = {user.getName(), user.getPassword(), user.getOnline()};
        List<java.io.Serializable> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
    }

    @Override
    public List getDeleteSql(User user) {
        String sql = "delete from t_user where id = ?";
        Object[] objects = {user.getId()};
        List<java.io.Serializable> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
    }

    @Override
    public List getFindOneSql(User user) {
        String sql = "select * from t_user where id = ?";
        Object[] objects = {user.getId()};
        List<java.io.Serializable> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
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
        UserQueryModel userQueryModel = (UserQueryModel) queryModel;
        buffer.append("select * from t_user where 1=1 ");
        if(userQueryModel.getName() != null && userQueryModel.getName().trim().length() > 0){
            buffer.append(" and name=?");
            objects[0] = userQueryModel.getName();
        }
        if (userQueryModel.getPassword() != null && userQueryModel.getPassword().trim().length() > 0){
            buffer.append(" and pwd=?");
            objects[1] = userQueryModel.getPassword();
        }
        if (userQueryModel.getOnline() > 0){
            buffer.append(" online=?");
            objects[2] = userQueryModel.getOnline();
        }
        list.add(buffer.toString());
        list.add(objects);
        return list;
    }
}
