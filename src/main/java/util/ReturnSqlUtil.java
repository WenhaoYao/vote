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

    public static List returnSql(String sql, Object[] objects){
        List<Object> list = new ArrayList<>();
        list.add(sql);
        list.add(objects);
        return list;
    }

}
