package org.example.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: Derek.huang on 2023/5/23 7:56.
 */
public class TestDate {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Instant now = Instant.now();
        Date date = Date.from(now);
        System.out.println("String获得Date:" + date);

    }

}
