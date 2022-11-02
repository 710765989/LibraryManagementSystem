package com.library.librarymanagementsystem.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfig {
    //将自己的验证方式加入容器
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //// 加密对象
        //HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //// 加密类型
        //matcher.setHashAlgorithmName("md5");
        //// 迭代加密次数
        //matcher.setHashIterations(3);
        //UserRealm userRealm = userRealm();
        //userRealm.setCredentialsMatcher(matcher);
        securityManager.setRealm(userRealm());
        securityManager.setSessionManager(sessionManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * 配置session管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(simpleCookie());

        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationInterval(1000 * 60 * 60);
        sessionManager.setGlobalSessionTimeout(24 * 1000 * 60 * 60);
        return sessionManager;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("simpleCookie");
        simpleCookie.setSecure(false);
        simpleCookie.setSameSite(Cookie.SameSiteOptions.NONE);
        simpleCookie.setHttpOnly(false);
        simpleCookie.setMaxAge(24 * 1000 * 60 * 60);
        return simpleCookie;
    }

    /**
     * shiro拦截器
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 不认证就可以访问的资源
        definition.addPathDefinition("/login", "anon");
        // 需要认证才能访问的资源
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
