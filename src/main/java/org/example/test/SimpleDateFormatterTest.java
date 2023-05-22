package org.example.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Derek.huang on 2023/5/22 21:15.
 */
public class SimpleDateFormatterTest {

    //代码小农：大神勿喷，如有错误请指出，谢谢支持！！

    private static final ThreadLocal<DateFormat> threadLocalFormatter = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String threadLocalFormatDate(Date date) {
        return threadLocalFormatter.get().format(date);
    }


}
