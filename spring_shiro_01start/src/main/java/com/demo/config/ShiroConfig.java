package com.demo.config;

import com.demo.shiro.realms.CustomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建shiroFilter,负责拦截所以请求
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统的受限资源
        Map<String,String> map = new HashMap<>();
        //authc和anon是什么?
        //是shiro提供的多个默认的过滤器,我们可以使用这些过滤器配置控制指定的url权限
        //authc:指定的url需要form表单登陆
        //anon:指定url可以匿名访问
        //perms:指定url需要指定权限才能访问
        //roles:需要指定角色才能访问
        map.put("/product","anon");//商品不需要登陆认证就可以访问
        map.put("/","anon");//首页也是一样的
        map.put("/user","anon");
        map.put("/order","authc");
        map.put("/info","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }

    // 创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置自定义Realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 创建自定义的Realm
    @Bean
    public Realm realm(CustomRealm customRealm){
        return customRealm;
    }
}
