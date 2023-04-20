package com.shiro_authorizing;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class CustomMD5Hash {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        CustomSaltRealm realm = new CustomSaltRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");

        try {
            subject.login(token);
            System.out.println("认证通过,认证状态:"+subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
        }

        if (subject.isAuthenticated()){
           // 基于角色的权限控制
            System.out.println("admin:"+subject.hasRole("admin"));
            System.out.println("user:"+subject.hasRole("user"));
            System.out.println("super:"+subject.hasRole("super"));
            //判断是否同时拥有角色
            System.out.println("是否同时拥有admin和user:"+subject.hasAllRoles(Arrays.asList("admin", "user")));
            //判断是否有这些角色权限
            System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("admin", "user", "super"))));

            System.out.println("==============================================");
            // 基于资源的权限控制
            System.out.println("主体对01用户相关的所有权限:"+subject.isPermitted("user:*:01"));
            System.out.println("主体对商品是否有创建权限:"+subject.isPermitted("product:create"));
            //分别具有哪些权限
            System.out.println(Arrays.toString(subject.isPermitted("user:*:01", "order:*")));
            // 同时具有哪些权限
            System.out.println(subject.isPermittedAll("user:*:01", "product:create"));
        }
    }
}
