package org.example.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * @author Derek-huang
 */
public class ShiroUtil {

    /**
     * 我是谁？
     * 如果是授权用户则返回AD
     * 非授权用户返回线程名
     *
     * @return userName
     */
    public static String whoAmI() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return subject.getPrincipal().toString();
        } else {
            return String.format("SYSTEM:%s", Thread.currentThread().getName());
        }
    }

    /**
     * 返回当前线程对应的user，
     * 如果非终端用户则抛异常
     *
     * @return userName
     * @throws AccountException
     */
    public static String whoAmINoRobot() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new AccountException();
        }
        return subject.getPrincipal().toString();
    }

    /**
     * 传入username/ad 判断是否是本人
     * 忽略大小写
     *
     * @param userName
     * @return boolean
     */
    public static boolean isMe(String userName) {
        return isMe(userName, true);
    }

    /**
     * 传入username/ad 判断是否是本人
     *
     * @param userName
     * @param ignoreCase
     * @return boolean
     */
    public static boolean isMe(String userName, boolean ignoreCase) {
        return Objects.nonNull(userName) &&
                ignoreCase ?
                userName.equalsIgnoreCase(whoAmI())
                : userName.equals(whoAmI());
    }
}