package util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaowenhao
 * @Title ReturnSqlUtil
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 22:22
 */
public class ReturnSqlUtil {

    public static List<java.io.Serializable> returnSql(String sql, Object[] objects){
        List<java.io.Serializable> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
    }

}
