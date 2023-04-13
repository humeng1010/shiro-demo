package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class ShiroRun {
    public static void main(String[] args) {
//        创建一个安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        加载配置文件,把配置文件中的信息加载到IniRealm对象中. Realm:领域
        IniRealm realm = new IniRealm("classpath:shiro.ini");
//        安全管理器就是shiro中的总管(认证,授权)
        securityManager.setRealm(realm);
//        把securityManager设置为全局
        SecurityUtils.setSecurityManager(securityManager);

//        获取要认证的主体,就是需要登陆的对象
        Subject subject = SecurityUtils.getSubject();

//        获取主体提交的账户和密码,大部分是从表单提交的
        String username = "bar";
        String password = "456";

//        拿着客户的账户密码,封装成shiro的令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        System.out.println("认证前的身份:"+subject.getPrincipal());//null
        try {
//        让主体拿着令牌去认证
            subject.login(token);
//        如果认证失败会抛出异常
            System.out.println("认证通过");
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
            e.printStackTrace();
        }
        System.out.println("是否通过认证:"+subject.isAuthenticated());

        System.out.println("认证后的身份:"+subject.getPrincipal());//bar

//        进行授权操作(前提是通过认证之后)
//        default format
//                                        who                   what  how
        System.out.println("user:save  "+subject.isPermitted("user:save"));
        System.out.println("user:delete  "+subject.isPermitted("user:delete"));
        System.out.println("user:update  "+subject.isPermitted("user:update"));
        System.out.println("user:find  "+subject.isPermitted("user:find"));


//        主体登出
        subject.logout();
        System.out.println("是否通过认证:"+subject.isAuthenticated());


    }
}
