package com.shiro_realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public class App {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        ===============change=======================
//        使用自定义Realm
        Realm realm = new MyRealm();

//        ===============change======================
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        String username = "foo";
        String password = "123";

//        认证
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            subject.login(token);
            System.out.println("认证通过");
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
            e.printStackTrace();
        }
//        授权(认证没有通过,不进行授权)
        System.out.println("user:save  "+subject.isPermitted("user:save"));
        System.out.println("user:delete  "+subject.isPermitted("user:delete"));
        System.out.println("user:update  "+subject.isPermitted("user:update"));
        System.out.println("user:find  "+subject.isPermitted("user:find"));
    }
}
