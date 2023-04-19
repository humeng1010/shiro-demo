package com.shiro_realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * 继承授权的Realm(授权的Realm继承了认证的Realm)
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权...");
        Set<String> realmNames = principalCollection.getRealmNames();
        System.out.println(realmNames);
        return null;
    }

    /**
     * 认证
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //在token中获取用户名
        Object principal = authenticationToken.getPrincipal();
        System.out.println(principal);
        //根据身份信息使用jdbc或者mybatis查询相关数据库
        if ("foo".equals(principal)){
            //参数1:返回数据库中正确的用户名; 参数二:返回数据库中正确的密码; 参数三:提供当前Realm的名字
            return new SimpleAuthenticationInfo(principal,"123456",this.getName());
        }

        return null;

    }
}
