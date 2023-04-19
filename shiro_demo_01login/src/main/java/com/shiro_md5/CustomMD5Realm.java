package com.shiro_md5;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 使用自定义Realm 加入md5 + salt + hash散列
 */
public class CustomMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String principal = (String) token.getPrincipal();
        //根据用户名查询数据库
        if("zhangsan".equals(principal)){
            return new SimpleAuthenticationInfo(principal, //数据库的用户名
                    "30d9516e90384e43a35ccd4a35d1e9ae", //数据库中的密码 MD5+salt+hash散列 之后的密码
                    ByteSource.Util.bytes("A*B*C"), //注册时的随机盐
                    this.getName() //当前Realm的名称
            );
        }
        //=========================================
        //注意我们只需要把盐返回,认证的时候会自动进行加盐处理!
        //=========================================

        return null;
    }
}
