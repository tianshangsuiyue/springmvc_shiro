package com.lh.controller;

import com.lh.entity.User;
import com.lh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("login")
public class login {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String login(){
        List<User> list =userService.selectAll();
        for (User user: list) {
            System.out.println("user ： "+user.toString());
        }
            System.out.println("进入方法");
        return "/login";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String dologin(User user,Model model){

        System.out.println("dologin:"+user .toString());
        String info =loginUser(user);

        if(!"SUCC".equals(info)){
            model.addAttribute("faiMsg","用户不存在或密码错误");
            return "fail";
        }else{
            model.addAttribute("successMsg","登录成功！");
            model.addAttribute("name",user.getUsername());
            return "success"; // 返回的页面
        }

    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request , HttpServletResponse response)throws IOException{
        Subject subject= SecurityUtils.getSubject();
        if(subject!=null){
            try{
                subject.logout();
            }catch (Exception e){

            }
        }
        response.sendRedirect("/index");
    }

    private String loginUser(User user){
        if(isRelogin(user)){
            return "SUCC"; // 如果登录，无需重新登录
        }

        return shiroLogin(user);//调用shiro 的登录验证
    }

    private String shiroLogin(User user){

        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPwd());
        token.setRememberMe(true);
        // shiro 登录
        try{
            SecurityUtils.getSubject().login(token);
        }catch(UnknownAccountException ex){
            return "用户不存在或者密码错误";
        }catch (IncorrectCredentialsException ex){
            return "用户不存在或者密码错误";
        }catch (AuthenticationException ex){
            return ex.getMessage(); // 自定义报错
        }catch (Exception ex){
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }
    private boolean isRelogin(User user){
        Subject us=SecurityUtils.getSubject();
        if(us.isAuthenticated()){
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false ;  // 需要重新登录
    }
}
