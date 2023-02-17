package org.example.test;

/**
 * @author Derek-huang
 */
public class User {

    private String name;

    private String age;

    private Integer weight;

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
