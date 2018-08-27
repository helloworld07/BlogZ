package com.zcy.controller;

import com.zcy.domain.Userinfo;
import com.zcy.service.ModifyService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcy on 2018/8/16.
 */
@Controller
@RequestMapping("/modify")
public class ModifyUserController {

    @Autowired
    private ModifyService modifyService;

    //修改密码
    @RequestMapping("/chgpwd")
    @ResponseBody
    public ReturnInfo changePwd(String oldpwd, @Pattern(regexp = "^[A-Za-z0-9]{9,20}$",message = "请输入符合规范的密码！") String newpwd, String newpwdaga, HttpServletRequest request, ReturnInfo r){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo==null){
            r.setInfo("请登录！");
            return r;
        }
        if (!oldpwd.equals(userinfo.getPassword())){
            r.setInfo("原密码错误！");
            return r;
        }
        if (!newpwd.equals(newpwdaga)){
            r.setInfo("两次输入密码不一致！");
            return r;
        }
        int count = modifyService.updatepwd(newpwd, userinfo.getId());
        if (count==1){
            r.setFlag(true);
            r.setInfo("修改成功！");
            return r;
        }else {
            r.setInfo("修改失败！");
            return r;
        }
    }

    //个人信息带入
    @RequestMapping("/getuserinfo")
    public String getUserinfo(HttpServletRequest request, ModelMap modelMap){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo==null){
            return "login";
        }
        Userinfo user = modifyService.getUser(userinfo.getId());
//        user.setId(777);
        user.setPassword("*****");//防止密码泄露
        modelMap.addAttribute("user",user);
        return "profilepage";
    }

    //修改个人信息
    @RequestMapping("/chginfo")
    @ResponseBody
    public ReturnInfo changePwd(@NotEmpty(message = "昵称不能为空")String nickname, String telnum, @Email(message = "请输入正确的邮箱地址！") String email, String sex, boolean pub, HttpServletRequest request, ReturnInfo r) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo == null) {
            r.setInfo("请登录！");
            return r;
        }
        if (("").equals(nickname)||("").equals(telnum)||("").equals(email)||("").equals(sex)||("").equals(pub)){
            r.setInfo("修改值不能为空！");
            return r;
        }
        String pubb;
        if(pub==true){
            pubb = "1";
        }else {
            pubb = "0";
        }
        int count = modifyService.updateInfo(nickname, telnum, sex, pubb, email, userinfo.getId());
        if (count==1){
            r.setFlag(true);
            r.setInfo("修改成功！");
            return r;
        }else {
            r.setInfo("修改失败！");
            return r;
        }
    }

    //查看他人信息带入
    @RequestMapping("/getperinfo")
    public String getPerinfo(HttpServletRequest request, ModelMap modelMap,String perid){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo==null){
            return "login";
        }
        if (perid==null||("").equals(perid))return "login";
        int id = Integer.parseInt(perid);
        Userinfo user = modifyService.getUser(id);
        user.setPassword("*****");//防止密码泄露
        if (user.getPub()=="0"){//保密
            user.setTelnum("*********");
            user.setEmail("*********");
        }
        modelMap.addAttribute("user",user);
        return "perdetail";
    }
}
