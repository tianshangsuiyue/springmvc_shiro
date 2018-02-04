package com.lh.shiro;

import com.lh.dao.UserDao;
import com.lh.entity.User;
import com.lh.service.UserService;
import com.lh.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *shiro MyRealm 配置类
 * */
public class ShiroDbRealm extends AuthorizingRealm{

    @Autowired
    private UserServiceImpl userService;

    public static final String SESSION_USER_KEY="gray";

    /**
     *授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用，负责在应用程序中决定用户的访问控制方法
     * */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("doGetAuthorizationInfo-------");
        User user =(User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        SimpleAuthorizationInfo  info =new SimpleAuthorizationInfo ();
        info.addRole(user.getRole().trim());
        return info;

    }
    /**
     * 认证回调函数，登录信息和用户验证信息验证
     * */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("doGetAuthenticationInfo-------");
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) authenticationToken;
        User user=userService.doUserLogin(usernamePasswordToken.getUsername());
        if(user ==null){
            return null;
        }
        // 设置session
        Session session =SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroDbRealm.SESSION_USER_KEY,user);
        // 当前的realm 的name
        String realmName=this.getName();

        // 登录的主要信息：可以是一个实体类的对象，但该实体类的对象一定根据token的username 查询得到的
        Object principal =authenticationToken.getPrincipal();
        return new SimpleAuthenticationInfo(principal,user.getPwd(),realmName);
    }

    private User tokenToUser(UsernamePasswordToken usernamePasswordToken){
        System.out.println("tokenToUser-------");
        User user =new User();
        user.setUsername(usernamePasswordToken.getUsername());
        user.setPwd(usernamePasswordToken.getPassword().toString());
        return user;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
