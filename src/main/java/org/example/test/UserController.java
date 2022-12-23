package org.example.test;

/**
 * @author Derek-huang
 */
public class UserController{
    @MyAnnotation(name = "张三",age = 18, hobby ={"跑步","打游戏"})//注解里面的字段
    public String get(){
        return "Hello Annotation";
    }
}

