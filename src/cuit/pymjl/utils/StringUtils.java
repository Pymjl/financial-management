package cuit.pymjl.utils;

import java.util.regex.Pattern;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:38
 **/
public class StringUtils {
    /**
     * 是否为null
     *
     * @param str str
     * @return boolean
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * 是否为空
     *
     * @param str str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.length() == 0;
        }
    }

    /**
     * 是否为空
     *
     * @param str str
     * @return boolean
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }

    /**
     * 判断多个字符串是否为空
     *
     * @param strings 字符串
     * @return boolean
     */
    public static boolean isBlank(String... strings) {
        for (String str : strings) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param strings 字符串
     * @return boolean
     */
    public static boolean isDigit(String... strings) {
        for (String s : strings) {
            if (isBlank(s)) {
                return false;
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            boolean matches = pattern.matcher(s).matches();
            if (!matches) {
                return false;
            }
        }
        return true;
    }
}
