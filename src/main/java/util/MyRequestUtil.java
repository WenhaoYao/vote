package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * @author YaoWenHao
 * @Title: MyRequestUtil
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/6 8:31
 */
public class MyRequestUtil extends HttpServletRequestWrapper {

    private String encode;

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public MyRequestUtil(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name){
        String result = null;
        try {
            byte[] bytes = super.getParameter(name).getBytes("ISO-8859-1");
            result = new String(bytes, encode);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
