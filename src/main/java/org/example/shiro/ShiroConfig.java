package org.example.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.example.dao.SysUserMapper;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Derek-huang
 */
@Configuration
public class ShiroConfig {

    private SysUserMapper sysUserMapper;

    public ShiroConfig(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    //Shiro框架的入口
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //用户未登录的时候访问的接口
        shiroFilterFactoryBean.setLoginUrl("/login/un");
        //loginUrl:login.jsp
        //shiroFilterFactoryBean.setLoginUrl("login.html");
        Map<String,String> filterChain = new HashMap<>();
        //表示这个uri是用户未登录的场景下是可以访问的
        //filterChain.put("/login.html","anon");
        filterChain.put("/login","anon"); //anonymous匿名访问
        filterChain.put("/login/un","anon");
        //表示访问 /user/**,需要登录才能访问
        filterChain.put("/**","authc");//authentication

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(new CustomizeSessionManager());
        return securityManager;
    }

    //这是Realm，SysUserMapper会自动的注入进来
    @Bean
    public Realm realm(){
        CustomizeRealm customizeRealm = new CustomizeRealm();
        customizeRealm.setSysUserMapper(sysUserMapper);
        //setCredentialsMatcher() 设置用户自定义的密码比较方式
        customizeRealm.setCredentialsMatcher(new CustomizeCredentialsMatcher());
        return customizeRealm;
    }

    /**
     *  该类的作用是，实现注解的方式来设置权限
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisor = new DefaultAdvisorAutoProxyCreator();
        advisor.setProxyTargetClass(true);
        return advisor;
    }

    /**
     * 实现注解的方式来配置权限
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager((SecurityManager) securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

