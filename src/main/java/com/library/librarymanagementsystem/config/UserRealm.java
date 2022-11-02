package com.library.librarymanagementsystem.config;

import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

//@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 自定义授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 查询
        User user = userService.getByUsername(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getType());
        return simpleAuthorizationInfo;
    }

    /**
     * 自定义登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (ObjectUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        //Claims claims = JwtUtils.getClaimByToken(name);
        //if (claims != null) {
        //    String username = claims.getId();
        //}

        User user = userService.getByUsername(name);
        if (user != null) {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword()
                    //, ByteSource.Util.bytes("salt")
                    , name);
        }
        //这里返回后会报出对应异常
        return null;
    }
}
