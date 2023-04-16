
> 该包主要是进行realm的了解案例
> 我们从shiro包中了解到了:shiro是从realm中获取我们存好的账户密码,以及角色信息的
> 而Realm是一个接口,我们可以有多个实现.
> 这个包将扩展这个接口,来实现自定义的Realm
> 

通过继承AuthorizingRealm(授权Realm,因为授权在认证之后,要授权必须先认证)
重写该类的两个方法:`doGetAuthorizationInfo`和`doGetAuthenticationInfo`
来进行自定义授权和认证,具体代码请查看`MyRealm.java`~
