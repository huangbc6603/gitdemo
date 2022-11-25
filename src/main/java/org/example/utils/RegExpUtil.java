package org.example.utils;

/**
 * @author Derek-huang
 */
public class RegExpUtil {
    //中国手机正则 ---宽松的匹配
    public static final String REG_MOBILE = "^(((\\+)?86)|(\\((\\+)?86\\)))?1[34578]\\d{9}$";
    //邮箱正则表达式
    public static final String REG_EMAIL = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";

    public static boolean isMobilePhoneNum(String mobilePhoneNum) {
        if (mobilePhoneNum == null || "".equals(mobilePhoneNum))
            return false;
        return mobilePhoneNum.matches(REG_MOBILE);
    }

    public static boolean isEmailAddress(String emailAddress) {
        if (emailAddress == null || "".equals(emailAddress))
            return false;
        return emailAddress.matches(REG_EMAIL);
    }
}
