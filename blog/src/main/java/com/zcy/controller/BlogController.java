package com.zcy.controller;

import com.github.pagehelper.PageInfo;
import com.zcy.domain.BlogInfo;
import com.zcy.domain.ClassifyInfo;
import com.zcy.domain.Userinfo;
import com.zcy.service.BlogService;
import com.zcy.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by zcy on 2018/8/4.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    //获取文章列表
    @RequestMapping("/getpaper")
    public ReturnInfo getpaper(ReturnInfo r, HttpServletRequest request, ModelMap modelMap, int pageNum, int pageSize,String classify) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        //暂时在博客列表不用用户信息
        /*if (userinfo!=null) {
            String author = userinfo.getUsername();
            List<BlogInfo> list = blogService.getpaper(author, pageNum, pageSize);
            PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
            r.setPageinfo(pageInfo);
            r.setList(list);
        }else {
            String author = null;
            List<BlogInfo> list = blogService.getpaper(author, pageNum, pageSize);
            PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
            r.setPageinfo(pageInfo);
            r.setList(list);
        }*/
        List<BlogInfo> list;
        //如果私密
        if (("4").equals(classify)){
            int userid = userinfo.getId();
            list = blogService.getlockpaper(userid, pageNum, pageSize);
        }else {
            list = blogService.getpaper(classify, pageNum, pageSize);
        }
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(list);
        r.setPageinfo(pageInfo);
        r.setList(list);
        return r;
    }

    //获取文章分类
    @RequestMapping("/getclassify")
    public ReturnInfo getClassify(ReturnInfo r) {
        List<ClassifyInfo> list = blogService.getClassify();
        r.setList(list);
        return r;
    }

    //提交博客
    @RequestMapping("/commmitblog")
    public ReturnInfo commitBlog(ReturnInfo r, String title, String content, String flag, String classify, HttpServletRequest request) {
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("userinfo");
        if (userinfo == null) {
            r.setFlag(false);
            r.setInfo("您还没有登录，请登录后再提交！");
            return r;
        }
        String author = userinfo.getNickname();
        int userid = userinfo.getId();
        String pubtime = LocalDateTime.now().toString();
        int count = blogService.commitBlog(title, content, pubtime, flag, author,userid, classify);
        if (count < 1) {
            r.setFlag(false);
            r.setInfo("保存博客失败，请重新提交！");
            return r;
        }
        r.setFlag(true);
        r.setInfo("保存成功！");
        return r;
    }

    //UEditor图片上传路径名称等配置项
    @RequestMapping("/ueditor")
    public String ueditor(HttpServletRequest request, HttpServletResponse response) {

        String s = "{\n"+
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"file\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;

    }

    ////UEditor图片上传方法
    @RequestMapping(value = "/imgUpdate")
    public String imgUpdate(MultipartFile file,HttpServletRequest request) {
        if (file.isEmpty()) {
            return "error：file is empty!";
        }
        Random random = new Random();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 这里我使用随机字符串来重新命名图片
        fileName = Calendar.getInstance().getTimeInMillis() + random.nextInt(10) + suffixName;
        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        //这个地方springboot每次启动存放的文件夹都会变化，所以图片会出现取不到的情况,所以没有使用realPath去获取绝对路径
        //String realPath = request.getSession().getServletContext().getRealPath("/");
        //在本地使用项目全路径的方式配置到项目内，便于提交项目和图片(但是需要重启后才可以看到图片)
        File dest = new File("E:\\SpringMVCDemo\\BlogZ\\web\\src\\main\\resources\\static\\uploadimgs\\" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"" + "http://localhost:8080/uploadimgs/" + fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";
            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error:saving picture exception!";
    }
}
