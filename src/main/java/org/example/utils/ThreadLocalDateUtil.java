package org.example.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Derek-huang
 */
public class ThreadLocalDateUtil {

    private ThreadLocalDateUtil() {

    }
    /**
     * 说明：使用ThreadLocal, 也是将共享变量变为独享，
     * 线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。
     * 如果对性能要求比较高的情况下，一般推荐使用这种方法。
     */

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<>();

    public static DateFormat getDateFormat() {
        DateFormat df = THREAD_LOCAL.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            THREAD_LOCAL.set(df);
        }
        return df;
    }

    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
}
