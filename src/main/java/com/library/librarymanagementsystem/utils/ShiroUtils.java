package com.library.librarymanagementsystem.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.enums.UserTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;
import java.util.Objects;

/**
 * ShiroUtils
 */
public class ShiroUtils {
    // 当前登录用户
    public static final String CURRENT_USER = "current_user";
    // 当前登录管理员
    public static final String CURRENT_MANAGER = "current_manager";
    // 当前登录读者
    public static final String CURRENT_READER = "current_reader";
    // 缓存sessionId
    public static String sessionId = null;

    /**
     * 用户登录
     */
    public static Serializable login (User user) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = getSubject();
        subject.login(usernamePasswordToken);
        // 登录用户信息存入session
        subject.getSession().setAttribute(CURRENT_USER, user);
        if (UserTypeEnum.MANAGER.getKey().equals(user.getType())) {
            subject.getSession().setAttribute(CURRENT_MANAGER, user);
        } else {
            subject.getSession().setAttribute(CURRENT_READER, user);
        }
        // sessionId
        Serializable id = subject.getSession().getId();
        sessionId = id.toString();
        return id;
    }

    // 由于@CrossOrigin原因 暂无法使用
    //public static Session getSession () {
    //    return SecurityUtils.getSubject().getSession();
    //}

    /**
     * 取得session
     */
    public static Session getSession () {
        if (StringUtils.isNotBlank(sessionId)) {
            Session session = getSubject().getSession();
            System.out.println(session);
            return SecurityUtils.getSecurityManager().getSession(new DefaultSessionKey(sessionId));
        }
        return null;
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Object getUserPrincipal() {
        return SecurityUtils.getSubject().getPrincipal();
    }

    public static User getCurrentUser() {
        return isExists(CURRENT_USER) ? (User) Objects.requireNonNull(getSession()).getAttribute(CURRENT_USER) : null;
    }

    public static boolean isExists(String sessionKey) {
        return Objects.requireNonNull(getSession()).getAttribute(sessionKey) != null;
    }

    public static void setSession(User user) {
        Objects.requireNonNull(getSession()).setAttribute(CURRENT_USER, user);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
