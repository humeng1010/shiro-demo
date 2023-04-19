package com.shiro_md5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestCustomMD5Realm {
    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //设置自定义Realm
        CustomMD5Realm realm = new CustomMD5Realm();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置hash算法名称
        credentialsMatcher.setHashAlgorithmName("md5");
        //设置hash散列次数
        credentialsMatcher.setHashIterations(1024);

        realm.setCredentialsMatcher(credentialsMatcher);

        securityManager.setRealm(realm);

        //将安全管理器注入到安全工具
        SecurityUtils.setSecurityManager(securityManager);


        //获取主体
        Subject subject = SecurityUtils.getSubject();

        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");

        try {
            //登陆认证
            subject.login(token);
            System.out.println("登陆成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
    }
}
