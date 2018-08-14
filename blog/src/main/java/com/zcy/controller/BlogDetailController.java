package com.zcy.controller;

import com.github.pagehelper.PageInfo;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.CommentInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import com.zcy.utils.ReturnInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String getdetail(ModelMap modelMap, @Param("id") String id, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "3") int pageSize) {
        int idnum = Integer.parseInt(id);
        List<BlogInfo> list = blogService.getpaperdetail(idnum);
        BlogInfo blogInfo = list.get(0);
        String title = blogInfo.getTitle();
        //处理时间格式
        modelMap.addAttribute("pagelist", list);
        //评论
        List listc = getComment(idnum,pageNum,pageSize);
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(listc);
        modelMap.addAttribute("commentlist", listc);
        modelMap.addAttribute("pageInfo", pageInfo);
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
    public ReturnInfo addComment(ReturnInfo r, String paperid, String content, HttpServletRequest request) {
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
            int count = blogService.addComment(id, content, pubid);
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
    //文章作者可隐藏评论
}
