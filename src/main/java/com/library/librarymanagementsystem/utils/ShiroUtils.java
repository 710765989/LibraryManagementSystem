package com.library.librarymanagementsystem.utils;

import com.library.librarymanagementsystem.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;

public class ShiroUtils {
    public static final String CURRENT_USER = "current_user";
    public static final String CURRENT_MANAGER = "current_manager";
    public static final String CURRENT_READER = "current_reader";

    public static Serializable login (User user) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = getSubject();
        subject.login(usernamePasswordToken);
        subject.getSession().setAttribute(CURRENT_USER, user);
        return subject.getSession().getId();
    }

    public static Session getSession () {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Object getUserPrincipal() {
        return SecurityUtils.getSubject().getPrincipal();
    }

    public static User getCurrentUser() {
        return isExists(CURRENT_USER) ? (User) getSession().getAttribute(CURRENT_USER) : null;
    }

    public static boolean isExists(String sessionKey) {
        return getSession().getAttribute(sessionKey) != null;
    }

    public static void setSession(User user) {
        getSession().setAttribute(CURRENT_USER, user);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
