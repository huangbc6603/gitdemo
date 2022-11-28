package org.example.test;

import org.example.dto.Result;
import org.example.utils.JsonUtils;
import org.example.utils.RegExpUtil;
import org.example.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Derek-huang
 */
public class Test {

    private static final Logger logger= LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        System.out.println(RegExpUtil.isMobilePhoneNum("19865076633"));
        System.out.println(RegExpUtil.isMobilePhoneNum("13684303366"));
        String jsonStr = JsonUtils.toJson(new User("Derek","18"));
        System.out.println(jsonStr);
        final Result<Void> test = test();
        System.out.println(test.getMsg());
        System.out.println(UuidUtil.getLongUUID());//598022427500945409
        System.out.println(UuidUtil.getStringUUID());//f3644ce2-a39e-4934-bb9c-a70b74e52843
        logger.info(JsonUtils.toJson(test));
        logger.info(JsonUtils.toJson(test1()));
    }

    public static Result<Void> test(){
        return Result.failureMsg("asd");
    }
    public static Result<User> test1(){
        User user = new User("Derek", "18");
        return Result.success(user);
    }
}
