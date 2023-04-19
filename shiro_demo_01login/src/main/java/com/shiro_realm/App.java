package com.shiro_realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class App {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        securityManager.setRealm(new MyRealm());

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("foo", "123456");

        try {
            subject.login(token);
            System.out.println("success");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
