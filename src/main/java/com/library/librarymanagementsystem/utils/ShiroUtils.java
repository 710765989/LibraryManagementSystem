package com.library.librarymanagementsystem.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.enums.UserTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
    public static void login (User user) throws AuthenticationException {
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

    public static User getCurrentManager() {
        return isExists(CURRENT_MANAGER) ? (User) Objects.requireNonNull(getSession()).getAttribute(CURRENT_MANAGER) : null;
    }

    public static User getCurrentReader() {
        return isExists(CURRENT_READER) ? (User) Objects.requireNonNull(getSession()).getAttribute(CURRENT_READER) : null;
    }

    public static boolean isExists(String sessionKey) {
        return Objects.requireNonNull(getSession()).getAttribute(sessionKey) != null;
    }

    public static void setSession(User user) {
        Objects.requireNonNull(getSession()).setAttribute(CURRENT_USER, user);
    }

    public static void logout() {
        Session session = getSession();
        if (Objects.nonNull(session)) {
            session.removeAttribute(CURRENT_USER);
            session.removeAttribute(CURRENT_MANAGER);
            session.removeAttribute(CURRENT_READER);
        }
        sessionId = null;
        SecurityUtils.getSubject().logout();
    }


    /**
     * md5加密
     *
     * @param str 待加密字符串
     * @return 加密后结果
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException {
        return new Md5Hash(str, "salt", 3).toHex();
    }
}
