package com.zcy.controller;

import com.zcy.domain.Userinfo;
import com.zcy.service.LoginService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zcy on 2018/7/31.
 */
@Controller
@RequestMapping("/page")
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @Autowired
    private LoginService loginService;

    //登录逻辑，判断登录是否成功
    @RequestMapping("/check")
    @ResponseBody
    public ReturnInfo check(String username, String password, ReturnInfo r, HttpServletRequest request) {
        //普通登录逻辑
        if (!("").equals(username) || !("").equals(password)) {
            List<Userinfo> list = loginService.check(username, password);
            if (list.isEmpty()){
                r.setFlag(false);
                r.setInfo("用户名或密码错误！");
            }else {
                Userinfo userinfo = list.get(0);
                request.getSession().setAttribute("userinfo",userinfo);
                r.setFlag(true);
                /*String nickname = userinfo.getNickname();
                r.setFlag(true);
                //r.setInfo("欢迎登录"+nickname);
                request.getSession().setAttribute("nickname", nickname);*/
            }
            return r;
        }
        r.setFlag(false);
        r.setInfo("用户名或密码为空！");
        return r;
    }

    //登录成功后跳转获取用户信息
    @RequestMapping("/getinfo")
    @ResponseBody
    public ReturnInfo getinfo(ReturnInfo r,HttpServletRequest request, ModelMap modelMap){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo == null){
            r.setFlag(false);
            r.setInfo("请登录~");
        }else {
            String nickname = userinfo.getNickname();
            r.setFlag(true);
            r.setInfo(nickname);
        }
        return r;
    }

    //注销登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();//清除 session 中的所有信息
        return "redirect:/page/blogpage.html";
    }
}
