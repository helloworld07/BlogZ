package com.zcy.controller;

import com.zcy.service.RegisterService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created by zcy on 2018/8/2.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/checkname")
    @ResponseBody
    public ReturnInfo checkname(String username,ReturnInfo r){
        if (("").equals(username)){
            r.setFlag(false);
            r.setInfo("请输入用户名！");
            return r;
        }
        int num = registerService.checkname(username);
        if (num>0){
            r.setFlag(false);
            r.setInfo("用户名重复！");
        }else {
            r.setFlag(true);
            r.setInfo("用户名可使用！");
        }
        return r;
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public ReturnInfo adduser(ReturnInfo r, @NotEmpty(message = "用户名不能为空") String username, @Pattern(regexp = "^[A-Za-z0-9]{9,20}$",message = "请输入符合规范的密码！")String password, @Email(message = "请输入正确的邮箱地址！") String email, String nickname, String telnum, String sex, ReturnInfo returnInfo){
        //此处已经升级为注解式validation验证机制
        if (("").equals(username)||("").equals(password)||("").equals(email)){
            r.setFlag(false);
            r.setInfo("有空值");
        }else {
            int adduser = registerService.adduser(username, password, email, nickname, telnum, sex);
            if (adduser>0) {
                r.setFlag(true);
            }else {
                r.setFlag(false);
                r.setInfo("插值失败");
            }
        }
        return r;

    }
}
