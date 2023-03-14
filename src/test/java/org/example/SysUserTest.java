package org.example;

import org.example.entity.Person;
import org.example.entity.Student;
import org.example.rest.SelectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @author Derek-huang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {

    @Autowired
    private SelectService selectService;

    @Test
    public void demo() {

        String massPrice = "(1,2] 13.45; (2,3] 12.3;";
        if (massPrice.indexOf("]") != -1) {
            massPrice = massPrice.substring(massPrice.indexOf("]") + 1, massPrice.indexOf(";")).trim();
        }
        System.out.println(massPrice);


        String massPriceq = "13.45";
        if (massPriceq.indexOf("]") != -1) {
            massPriceq = massPriceq.substring(massPriceq.indexOf("]") + 1, massPriceq.indexOf(";")).trim();
        }
        System.out.println(massPriceq);
    }

    @Test
    public void test() {
        File file = new File("file" + File.separator + "me");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(fos);
                Student person = new Student("tom", 22);
                System.out.println(person);
                oos.writeObject(person);            //写入对象
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("oos关闭失败：" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件：" + e.getMessage());
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fis);
                try {
                    Person person = (Person) ois.readObject();   //读出对象
                    System.out.println(person);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("ois关闭失败：" + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件：" + e.getMessage());
        }
    }

    @Test
    public void test1(){
//        List<WorkFlowFilter> flowFilters = Arrays.asList(
//                WorkFlowFilter.create("AAA").put("NAME ", "99").put("AGE","88"));
//        System.out.println(JsonUtils.toJson(flowFilters));
    }
}
