package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("username:{},password:{}",user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.fail("用户名错误");
        }catch (IncorrectCredentialsException e){
            return Result.fail("密码错误");
        }
        return Result.success("登录成功");
    }

    @PostMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("退出成功");
    }

    @PostMapping
    public Result isLogin(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated){
            // 没有认证
            return Result.fail("请先登录");
        }
        return Result.success("is login");
    }

}
