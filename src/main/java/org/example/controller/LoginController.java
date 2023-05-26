package org.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Derek-huang
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @GetMapping
    public Object login(String username, String password, HttpSession session){
        //获取一个主题，将来用于封装用户的用户信息(登录成功后),该subject，shiro会自动管理
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        Map<String,Object> result = new HashMap<>();
        try {
            //执行登录（失败就报异常）
            subject.login(token);
            result.put("code",1);
            result.put("msg","success");
            result.put("token",session.getId());
            return result;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            result.put("code",-1);
            result.put("msg","用户名或密码错误");
            result.put("token",session.getId());
        }
        return result;
    }

    @GetMapping("/un")
    public Object unLogin(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",-1);
        result.put("msg","你还未登录，请登录");
        return result;
    }
}
