package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Derek-huang
 */
public class Test {

    private static final Logger logger= LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        List<String> woNos = new ArrayList<>(1);
        woNos.add("abc");
        woNos.add("abce");
        woNos.add("abce");
        woNos.add("abcf");
        //重复单号
        Set<String> woNo = new HashSet<>();
        Set<String> repeatOrder = woNos.stream().filter(n -> !woNo.add(n)).collect(Collectors.toSet());
        logger.info("重复单号{}",repeatOrder);
    }
}
