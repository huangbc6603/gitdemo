package org.example.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek.huang on 2023/5/22 21:32.
 * JDK1.8 当前时间及时间格式化
 * LocalDateTime 代替 Calendar (时间灵活,可直接获取年月日或时分秒)
 * DateTimeFormatter 代替 SimpleDateFormatter (DateTimeFormatter线程安全)
 * Instant 代替 Date
 */
public class TestDateTimeFormatter {

    public static void main(String[] args) {

    /*    LocalDateTime nowTime = LocalDateTime.now();
        int year = nowTime.getYear();
        int monthValue = nowTime.getMonthValue();
        int dayOfMonth = nowTime.getDayOfMonth();
        int hour = nowTime.getHour();
        int minute = nowTime.getMinute();
        int second = nowTime.getSecond();
        System.out.println(year + "年" + monthValue + "月" + dayOfMonth + "日"
                + hour + "时" + minute + "分" + second + "秒");
        //预定义的标准格式；如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        System.out.println(nowTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(nowTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(nowTime.format(DateTimeFormatter.ISO_LOCAL_TIME));


        LocalDate now = LocalDate.now();//yyyMMdd
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_DATE));
        System.out.println("当前时间-->年月日" + now);
        System.out.println("---------------------------------------------");

        LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 22
                , 21, 39, 55);
        System.out.println(localDateTime);

        LocalTime now1 = LocalTime.now();//hh:mm:ss
        System.out.println(now1.format(DateTimeFormatter.ISO_TIME));
        System.out.println(now1.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        System.out.println(now1.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("------------------------------");*/

        System.out.println("============================================================");


        // date与instant的相互转化 -- start
        Instant now = Instant.now();
        System.out.println("时间戳与北京时间相差8个时区:"+now);
        // 发现是采用的UTC，如果你需要使用北京时间，则需要增加8个小时
        Instant nowUTC = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("nowUTC :"+nowUTC );

        Date date = Date.from(now);
        System.out.println(date);

        Instant instant = date.toInstant();

        // LocalDate、LocalDateTime 的now()方法使用的是系统默认时区 不存在Instant.now()的时间问题。
        // date与instant的相互转化 -- end

        // LocalDateTime代替Calendar -- start
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        int year = localDateTime.getYear();// 年
        int month = localDateTime.getMonthValue();// 月
        int dayOfMonth = localDateTime.getDayOfMonth();// 日
        int hour = localDateTime.getHour();// 小时
        int minute = localDateTime.getMinute();// 分钟
        int second = localDateTime.getSecond();// 秒
        System.out.println(year);
        System.out.println(month);
        System.out.println(dayOfMonth);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        // LocalDateTime代替Calendar -- end


        // DateTimeFormatter代替SimpleDateFormat
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");// 24小时制

        // Date格式化成String -- start
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println("Date格式化成String:" + format);
        // Date格式化成String -- end

        // String获得Date -- start
        LocalDateTime parse = LocalDateTime.parse(format, dateTimeFormatter);
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        Instant instant2 = parse.toInstant(offset);
        Date date1 = Date.from(instant2);
        System.out.println("String获得Date:" + date1);
        // String获得Date -- end




    }


}
