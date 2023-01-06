package org.example.controller;

import org.example.utils.ThreadLocalDateUtil;
import java.util.Date;

/**
 * @author Derek-huang
 */
public class MainApp {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//        HelloWorld obj = context.getBean("helloWorld",HelloWorld.class);
//        obj.getMessage();

        String formatDate = ThreadLocalDateUtil.formatDate(new Date());
        System.out.println(formatDate);
    }
}
