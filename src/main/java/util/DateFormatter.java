package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yaowenhao
 * @Title DateFormatter
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 21:16
 */
public class DateFormatter {


    /**
      * @功能描述: 将长整型转化为日期格式的字符串
      * @author: yaowenhao
      * @date: 2018/12/13 21:22
      * @param: [time]
      * @return: java.lang.String
      */
    public static String toShortDate(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(new Date(time));
    }

    /**
      * @功能描述: 将日期格式的字符串转化为长整型
      * @author: yaowenhao
      * @date: 2018/12/13 21:22
      * @param: [date]
      * @return: java.lang.Long
      */
    public static Long toLong(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(date).getTime();
    }
}
