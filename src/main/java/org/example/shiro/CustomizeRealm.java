package org.example.shiro;

/**
 * @author Derek-huang
 */

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.dao.SysUserMapper;
import org.example.entity.SysUser;
import org.example.entity.SysUserExample;

import java.util.HashSet;
import java.util.Set;

/**
 * Realm:用户所有的认证和授权都是在这里完成的
 */

public class CustomizeRealm extends AuthorizingRealm {

    private SysUserMapper sysUserMapper;

    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        //查询用户对应的权限
        SimpleAuthorizationInfo ai = new SimpleAuthorizationInfo();
        Set<String> permission = new HashSet<>();
        //给予权限
        permission.add("user#list");
        ai.setStringPermissions(permission);
        return ai;
    }

    //用户的认证在这里完成
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            String username = upt.getUsername();
            char[] password = upt.getPassword();
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andLoginNameEqualTo(username)
                    .andUserPasswordEqualTo(String.valueOf(password));
            //根据用户名在数据库中查询到对应的用户  http://localhost:8080/login?username=admin&password=admin123
            SysUser sysUser = sysUserMapper.selectByExample(sysUserExample).get(0);
            //表示没有对应的用户
            if (null == sysUser) {
                //直接抛出异常，会在LoginController中捕获
                throw new UnknownAccountException(String.format("<%s>不存在", username));
            } else {
                //根据用户名查询有该用户，然后接着要校验密码
                //密码的校验这个工作不是我们来做的
                /**
                 * SimpleAuthenticationInfo 构造方法接收三个参数
                 * 1.第一个是用户名
                 * 2.第二个是数据库的密码
                 * 3.第三个是用户的真实的姓名
                 * shiro底层会自己比对token中密码和数据库密码
                 */
                SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(username, sysUser.getUserPassword(), username);//进入CustomizeCredentialsMatcher
                return sai;
            }
        }
        System.out.println(token);
        return null;
    }
}

