package com.zcy.controller;

import com.zcy.domain.Userinfo;
import com.zcy.service.IdentityService;
import com.zcy.service.LoginService;
import com.zcy.service.ModifyService;
import com.zcy.service.RedisServiceImpl;
import com.zcy.utils.RandonNumberUtils;
import com.zcy.utils.ReturnInfo;
import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

/**
 * Created by zcy on 2018/9/3.
 */
@RestController
@RequestMapping("/identify")
public class IdentifyController {
    @Autowired
    private IdentityService identityService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private ModifyService modifyService;

    private Logger logger = Logger.getLogger(IdentifyController.class);

    //认证注册邮箱并发送验证码
    @RequestMapping("nametoemail")
    public ReturnInfo NameToEmail(ReturnInfo r, String username, String email) {
        List<Userinfo> list = identityService.Identify(username, email);
        if (list.size() == 0) {
            r.setInfo("请输入正确的注册邮箱地址！");
            return r;
        }
        Userinfo userinfo = list.get(0);
        int id = userinfo.getId();
        r.setFlag(true);
        r.setInfo(id + "");
        //设置验证码
        String randonString = RandonNumberUtils.getRandonString(6);
        //存数据入redis,5分钟自动清除
        redisService.set("emailIdentify" + id, randonString, 300L);
        //交给队列来发送邮件
        rabbitTemplate.convertAndSend("qForEmail", randonString + userinfo.getEmail());
        return r;
    }

    //修改密码
    @RequestMapping("/chgpwd")
    public ReturnInfo idtychgpwd(@NotNull String idyid,@NotNull String userid, @Pattern(regexp = "^[A-Za-z0-9]{9,20}$", message = "请输入符合规范的密码！") String newpwd, String newpwdaga, HttpServletRequest request, ReturnInfo r) {
        //从redis取回验证码值
        String valicode = "";
        try {
            valicode = (String) redisService.get("emailIdentify" + userid);
        } catch (Exception e) {
            logger.error("redis取回验证码失败："+e);
        }
        //校验验证码
        if (idyid.equals(valicode)){
            //更改密码
            if (!newpwd.equals(newpwdaga)){
                r.setInfo("两次输入密码不一致！");
                return r;
            }
            int id = Integer.parseInt(userid);
            int count = modifyService.updatepwd(newpwd,id);
            if (count==1){
                r.setFlag(true);
                r.setInfo("修改成功！");
                return r;
            }else {
                r.setInfo("修改失败！");
                return r;
            }
        }else{
            r.setInfo("验证码不正确");
            return r;
        }
    }
}
