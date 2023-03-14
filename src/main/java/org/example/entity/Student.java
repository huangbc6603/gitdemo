package org.example.entity;

import java.io.Serializable;

/**
 * @author Derek-huang
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L; //一会就说这个是做什么的
    String name;
    int age;
    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "name:"+name+"\tage:"+age;
    }
}