package org.example.shiro;

/**
 * @author Derek-huang
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * 用户自定义的密码比较方式
 */
@Slf4j
public class CustomizeCredentialsMatcher implements CredentialsMatcher {

//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *当用户在Realm中设置了CredentialsMatcher这个之后，那么SimpleAuthenticationInfo
     * 中方式数据库密码和UsernamePasswordToken中放置的用户原始密码，比较的时候会自动的进入这个方法
     *
     * token -> 就是LoginController中UsernamePasswordToken
     * info -> 就是CustomizeRealm中doGetAuthenticationInfo的返回值
     *
     * 返回值：tr
     */

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        log.info("密码比对");
        char[] rawPassword = (char[]) token.getCredentials();
        String encodePassword = (String) info.getCredentials();
        if (StringUtils.isNotBlank(encodePassword)){
            if (encodePassword.equals(String.valueOf(rawPassword))){
                return true;
            }
        }
        return false;
    }
}

