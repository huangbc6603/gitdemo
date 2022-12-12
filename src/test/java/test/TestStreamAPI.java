/**
 * @author Derek-huang
 */
package test;

import org.example.entity.Person;
import org.example.utils.JsonUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一 Stream的三个步骤
 * 1. 创建Stream流
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestStreamAPI {

    /**
     * 筛选与切片
     * filter---接收Lambda,从流中排除某些元素;filter(能产生boolean结果的Lambda)，如果参数Lambda产生了true值,则要元素;
     * 如果产生了false，则不要这个元素。
     * limit()---截断流,使其不超过给定数量
     * skip()---跳过元素,返回一个扔掉了前n个元素的流,若流中元素不足n个,则返回一个空流,与limit(n)互补
     * distinct--筛选,通过流所生成元素的hashcode()和equals()去除重复元素
     */

    @Test
    public void test3() {
        List<Person> persons = getPerson();
        //test
        final List<Person> collect = persons.stream()
                .filter((x) -> x.getAge() > 17)
                .skip(2)
                .distinct().collect(Collectors.toList());
        System.out.println("result>>>>>>>>>>" + JsonUtils.toJson(collect));
    }


    @Test
    public void test2() {
        List<Person> persons = getPerson();
        persons.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getAge() > 20;
                })
                .limit(2)
                .forEach(System.out::println);


    }

    //内部迭代:迭代操作有Stream API完成
    @Test
    public void test1() {
        List<Person> persons = getPerson();
        //中间操作
        Stream<Person> personStream = persons.stream()
                .filter((e) -> {
                    System.out.println("Stream API的中间操作");
                    return e.getAge() > 22;
                });

        //终止操作:一次性执行全部内容,即"惰性求值"
        personStream.forEach(System.out::println);
    }


    public List<Person> getPerson() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setName("lisi");
        person.setAge(18);
        person.setSalary(BigDecimal.valueOf(1000));
        personList.add(person);

        Person person1 = new Person();
        person1.setName("zhangsan");
        person1.setAge(22);
        person1.setSalary(BigDecimal.valueOf(2000));
        personList.add(person1);

        Person person2 = new Person();
        person2.setName("wangwu");
        person2.setAge(23);
        person2.setSalary(BigDecimal.valueOf(3000));
        personList.add(person2);
        return personList;
    }


    /**
     * 映射
     * map---接收Lambda,将元素转换成其他形式或提取信息,接收一个函数作为参数,该函数会被应用到每个元素上,并将其映射成一个新的元素
     * flatMap--- 接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流连接成一个流
     */
    @Test
    public void test4() {
        List<String> lists = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        lists.stream()
                .map((x) -> x.toUpperCase())
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("hepengju", 28, BigDecimal.valueOf(30000.0)));
        list.add(new Person("lisi", 44, BigDecimal.valueOf(40000.0)));
        list.add(new Person("wangwu", 55, BigDecimal.valueOf(50000.0)));
        list.add(new Person("zhaoliu", 66, BigDecimal.valueOf(60000.0)));
        list.add(new Person("zhangsan", 33, BigDecimal.valueOf(33333.0)));
        list.add(new Person("zhangsan", 23, BigDecimal.valueOf(10000.0)));

        // 1.按照年龄分组
        Map<Integer, List<Person>> collect = list.stream().collect(Collectors.groupingBy(Person::getAge));
        collect.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
    }

    @Test
    public void test6() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    @Test
    public void test7() {
//        System.out.println(Calendar.getInstance());
    }

    @Test
    public void test8() {

    }

    @Test
    public void test9() {

    }
}



