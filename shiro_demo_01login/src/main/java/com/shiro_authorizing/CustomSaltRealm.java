package com.shiro_authorizing;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomSaltRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("========Authorization=========");
        System.out.println("身份信息(用户名):"+principals);
        //根据身份信息获取当前用户的角色信息以及权限信息
        // zhangsan admin user
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色权限
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
        // simpleAuthorizationInfo.addRole("super");

        //添加资源权限
        simpleAuthorizationInfo.addStringPermission("user:*:01");//只对01 用户有所有权限
        simpleAuthorizationInfo.addStringPermission("product:create");//主体对商品具有创建权限

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = "30d9516e90384e43a35ccd4a35d1e9ae";
        if ("zhangsan".equals(username)){
            return new SimpleAuthenticationInfo(username,
                    password,
                    ByteSource.Util.bytes("A*B*C"),
                    this.getName());
        }
        return null;
    }
}
