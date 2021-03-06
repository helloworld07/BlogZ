package com.zcy.controller;

import com.github.pagehelper.PageInfo;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.CommentInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import com.zcy.utils.ReturnInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zcy on 2018/8/9.
 */
@Controller
@RequestMapping("/detail")
public class BlogDetailController {

    @Autowired
    private BlogService blogService;

    //文章详情
    @RequestMapping("/page")
    public String getdetail(HttpServletRequest request,ModelMap modelMap, @Param("id") String id, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "3") int pageSize) {
        int idnum = Integer.parseInt(id);
        List<BlogInfo> list = blogService.getpaperdetail(idnum);
        modelMap.addAttribute("pagelist", list);
        //评论
        List listc = getComment(idnum,pageNum,pageSize);
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(listc);
        modelMap.addAttribute("commentlist", listc);
        modelMap.addAttribute("pageInfo", pageInfo);
        //登录者信息（用于删除权限的判断、收藏文章判断）
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo!=null) {
            userinfo.setPassword("*****");
            String collids = userinfo.getCollids();
            String[] split = collids.split(",");
            String flag = "0";
            for (int i = 0; i < split.length; i++) {
                if (id.equals(split[i])){
                    flag = "1";
                }
            }
            modelMap.addAttribute("flag",flag);
        }
        modelMap.addAttribute("userinfo",userinfo);
        return "blogdetail";
    }

    //文章评论获取
    public List getComment(int id,int pageNum,int pageSize) {
        List<CommentInfo> listc = blogService.getcomment(id,pageNum,pageSize);
        return listc;
    }

    //提交评论
    @ResponseBody
    @RequestMapping("/addcomment")
    public ReturnInfo addComment(ReturnInfo r, String paperid, String content, HttpServletRequest request,@RequestParam(value = "commentid",defaultValue = "0") String commentid) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        String pubid = "";
        if (userinfo == null) {
            r.setInfo("登录后才可以留言！");
            return r;
        } else {
            pubid = userinfo.getId() + "";
        }
        if (paperid != null && !("").equals(paperid) && content != null && !("").equals(content)) {
            int id = Integer.parseInt(paperid);
            int commentnum = Integer.parseInt(commentid);
            int count = blogService.addComment(id, content, pubid,commentnum);
            if (count < 1) {
                r.setInfo("提交失败！");
                return r;
            }
            r.setFlag(true);
            r.setInfo("提交成功");
            return r;
        } else {
            r.setInfo("系统异常，请稍后提交！");
            return r;
        }
    }
    //文章作者可回复和删除评论
    @ResponseBody
    @RequestMapping("/delcomment")
    public ReturnInfo delComment(ReturnInfo r, String userid,String commentid, HttpServletRequest request){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo == null) {
            r.setInfo("登录后才可操作！");
            return r;
        }
        int id = Integer.parseInt(userid);
        if (id ==userinfo.getId()){
            //作者权限，可删除
            int cid = Integer.parseInt(commentid);
            int count = blogService.delComment(cid);
            if (count<1){
                r.setInfo("删除评论失败！");
                return r;
            }else {
                r.setFlag(true);
                r.setInfo("评论删除成功！");
                return r;
            }
        }else{
            r.setInfo("只有本文章作者可以删除此评论！");
            return r;
        }
    }

    //获取回复
    @ResponseBody
    @RequestMapping("/getreply")
    public List<CommentInfo> getreply(String replyid){
        int id = Integer.parseInt(replyid);
        List<CommentInfo> listr = blogService.getreply(id);
        return listr;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //点赞加一
    @ResponseBody
    @RequestMapping("/addzan")
    public String addZan(String paperid){
        int i = Integer.parseInt(paperid);
        /*int count = blogService.addZan(i);
        if(count<1){
            return "赞失败";
        }else {
            return "赞成功";
        }*/
        //rabbitmq发送
        rabbitTemplate.convertAndSend("queueForZan", i);
        return null;
    }

    //收藏文章
    @ResponseBody
    @RequestMapping("/addcoll")
    public ReturnInfo addColl(String paperid,HttpServletRequest request,ReturnInfo r){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo==null){
            r.setInfo("请登录后再收藏文章！");
            return r;
        }
        //拿到所有收藏信息
        List<Userinfo> userById = blogService.getUserById(userinfo.getId());
        Userinfo user = userById.get(0);
        String collids = user.getCollids();
        //加入新的收藏
        collids = collids + paperid + ",";
        int i = blogService.updateColl(collids, user.getId());
        if (i==1){
            r.setFlag(true);
            r.setInfo("收藏成功！");
        }else {
            r.setInfo("收藏出现问题了！");
            return r;
        }
        //刷新缓存
        List<Userinfo> updateinfo = blogService.getUserById(user.getId());
        Userinfo userinfoupdate = updateinfo.get(0);
        request.getSession().setAttribute("userinfo", userinfoupdate);
        return r;
    }

    //取消收藏文章
    @ResponseBody
    @RequestMapping("/calcoll")
    public ReturnInfo calColl(String paperid,HttpServletRequest request,ReturnInfo r){
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo==null){
            r.setInfo("请登录后再取消收藏！");
            return r;
        }
        //拿到所有收藏信息
        List<Userinfo> userById = blogService.getUserById(userinfo.getId());
        Userinfo user = userById.get(0);
        String collids = user.getCollids();
        //取消收藏
       if(collids.contains(paperid)){
           collids = collids.replace(paperid+",","");
       }else {
           r.setInfo("取消收藏失败！");
       }
        int i = blogService.updateColl(collids, user.getId());
        if (i==1){
            r.setFlag(true);
            r.setInfo("取消收藏成功！");
        }else {
            r.setInfo("取消收藏出现问题了！");
            return r;
        }
        //刷新缓存
        List<Userinfo> updateinfo = blogService.getUserById(user.getId());
        Userinfo userinfoupdate = updateinfo.get(0);
        request.getSession().setAttribute("userinfo", userinfoupdate);
        return r;
    }
}
