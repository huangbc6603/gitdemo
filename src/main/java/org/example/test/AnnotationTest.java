package org.example.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Derek-huang
 */
public class AnnotationTest {

    public static void main(String[] args) {
        try {
            //获取Class对象
            Class mylass = Class.forName("org.example.test.UserController");
            //获得该对象身上配置的所有的注解
            Annotation[] annotations = mylass.getAnnotations();
            System.out.println(annotations.toString());
            //获取里面的一个方法
            Method method = mylass.getMethod("get");
            //判断该元素上是否配置有某个指定的注解
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println("UserController类的get方法上配置了MyAnnotation注解！");
                //获取该元素上指定类型的注解
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                System.out.println("name: " + myAnnotation.name() + ", age: " + myAnnotation.age()
                        + ",sex:" + myAnnotation.sex() + ", hobby: " + myAnnotation.hobby()[0]);
            } else {
                System.out.println("UserController类的get方法上没有配置MyAnnotation注解！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
