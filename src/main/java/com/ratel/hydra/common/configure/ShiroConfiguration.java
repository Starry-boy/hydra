package com.ratel.hydra.common.configure;

import com.ratel.hydra.security.realm.HydraRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ratel
 * @date 2020-05-22
 */
@Slf4j
@Configuration
public class ShiroConfiguration {

    @Bean
    public Realm realm(){
        HydraRealm realm = new HydraRealm();
        return realm;
    }

//    @Bean //将方法中返回的对象注入到IOC容器中
//    public FilterRegistrationBean registeShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
//        FilterRegistrationBean reFilter = new FilterRegistrationBean();
//        reFilter.setFilter(shiroFilterFactoryBean); //创建并注册TestFilter
//        reFilter.addUrlPatterns("/*"); //拦截的路径（对所有请求拦截）
//        reFilter.setName("TestFilter"); //拦截器的名称
//        reFilter.setOrder(1); //拦截器的执行顺序。数字越小越先执行
//        return reFilter;
//    }

    @Bean
    public DefaultSecurityManager securityManager(Realm realm){
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
//        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录的 URL
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //登录成功过跳转的URL
//        shiroFilterFactoryBean.setSuccessUrl("/admin/index.html");
        //未授权URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String,String> filterChainMap = new LinkedHashMap<>();

        //静态资源 不拦截
        filterChainMap.put("/css/**","anon");
        filterChainMap.put("/js/**","anon");
        filterChainMap.put("/fonts/**","anon");
        filterChainMap.put("/img/**","anon");
        filterChainMap.put("/*.html","anon");

        //druid 监控不拦截
        filterChainMap.put("/druid/**","anon");
        //登录接口
        filterChainMap.put("/user/login","anon");
        //注册
        filterChainMap.put("/user/add","anon");
        filterChainMap.put("/","anon");


        //登出接口
        filterChainMap.put("/logout","logout");

        //首页不拦截
//        filterChainMap.put("/**","user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        //剩下所有接口都需要拦截
        return shiroFilterFactoryBean;
    }

    public SimpleCookie rememberMeCookie(){
        //设置cookie名称 对应 <input type="checkbox" name= "HydraRememberMe"/>
        SimpleCookie cookie = new SimpleCookie("HydraRememberMe");
        //指定cookie过期时间 单位s
        cookie.setMaxAge(86400);
        return cookie;
    }



    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie 加密的秘钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


}