package com.shiro_realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 继承授权的Realm(授权的Realm继承了认证的Realm)
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        Set<String> realmNames = principalCollection.getRealmNames();
        System.out.println(realmNames);
        return null;
    }

    /**
     * 获取认证信息
     * @param authenticationToken token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证...");
//        获取令牌中封装的账号
        String username = (String) authenticationToken.getPrincipal();
//        获取令牌中封装的密码
//        String password = new String((char[])authenticationToken.getCredentials());
//        System.out.println(username+":"+password);
//        假设从数据库中获取的信息
        List<String> userList = Arrays.asList("foo", "bar");
        if (!userList.contains(username)){
            return null;
        }
//        在这层中不需要自己比较密码!我们直接交给比较器进行比较密码
//        select password form user where username = #{username}
        String password = null;
        if ("foo".equals(username) ){
//            认证通过,必须给用户身份
            password = "123";
//            return new SimpleAuthenticationInfo("aabb",password,"myRealm");
        }
        if ("bar".equals(username)){
            password = "456";
        }
//        没有通过返回null
        return new SimpleAuthenticationInfo("aabbcc",password,"myRealm");
    }
}
