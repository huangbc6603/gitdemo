package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Derek-huang
 */
public class Demo {

    private static final Logger logger = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {
        //取交集
        Map<String, String> materialOwnerMap =new HashMap<>(1);
        materialOwnerMap.put("s0","1");
        materialOwnerMap.put("s","1,2");
        materialOwnerMap.put("s1","1,4,9");
        materialOwnerMap.put("s2","3,1");
        final List<String> strings = verifyOwner(materialOwnerMap);
        logger.info("取交集{}",strings);

        HashMap<String, String> str = new HashMap<>();
        str.put("ab","cd");
        str.put("e","f");
        logger.info("str-->map{}",str);

        if (BigDecimal.ZERO.compareTo(new BigDecimal(0)) < 0){
            System.out.println("");
        }
        System.out.println("");

        final BigDecimal pricetax = new BigDecimal(1.12);
        final double v = pricetax.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(v);
    }

    public static List<String> verifyOwner(Map<String, String> materialOwnerMap) {
        List<List<String>> allOwner = new ArrayList();
        for (Map.Entry<String, String> entry : materialOwnerMap.entrySet()) {
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
