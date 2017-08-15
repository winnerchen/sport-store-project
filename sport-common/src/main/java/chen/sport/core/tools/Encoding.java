package chen.sport.core.tools;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 21:28 2017/8/15
 * @Modified by:
 */
public class Encoding {
    /**
     * 进行get方式编码处理
     *
     * @param str
     * @return
     */
    public static String encodeGetRequest(String str) {
        try {
            if (str != null) {
                return new String(str.getBytes("ISO8859-1"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

}
