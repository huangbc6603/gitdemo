package org.example;

import java.util.*;

/**
 * @author Derek-huang
 */
public class Demo {
    public static void main(String[] args) {
        //取交集
        Map<String, String> materialOwnerMap =new HashMap<>(1);
        materialOwnerMap.put("s0","1");
        materialOwnerMap.put("s","1,2");
        materialOwnerMap.put("s1","1,4");
        materialOwnerMap.put("s2","3,1");
        final List<String> strings = verifyOwner(materialOwnerMap);
        System.out.println(strings);
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
