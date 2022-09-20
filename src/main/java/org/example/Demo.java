package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Derek-huang
 */
public class Demo {

    private static final Logger logger = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {
        //取交集
        Map<String, String> map =new HashMap<>(1);
        map.put("s0","1");
        map.put("s","1,2");
        map.put("s1","1,4,9");
        map.put("s2","3,1");
        final Object strings = verify(map);
        logger.info("取交集{}",strings);

        HashMap<String, String> str = new HashMap<>();
        str.put("ab","cd");
        str.put("e","f");
        logger.info("str-->map{}",str);

        if (BigDecimal.ZERO.compareTo(new BigDecimal(0)) < 0){
            logger.info("============");
        }
        logger.info("============");
        //四舍五入
        final BigDecimal pretax = BigDecimal.valueOf(1.5555555);
        final double v = pretax.setScale(6, RoundingMode.HALF_UP).doubleValue();
        logger.info("============{}",v);
    }

    public static List<String> verify(Map<String, String> map) {
        List<List<String>> allOwner = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String owner = entry.getValue();
            List<String> owners = new ArrayList<>();
            if (owner.contains(",")) {
                final String[] split = owner.split(",");
                owners.addAll(Arrays.asList(split));
                allOwner.add(owners);
            }else {
                owners.add(owner);
                allOwner.add(owners);
            }
        }
        List<String> owners = allOwner.get(0);
        allOwner.forEach(owners::retainAll);
        return owners;
    }
}
