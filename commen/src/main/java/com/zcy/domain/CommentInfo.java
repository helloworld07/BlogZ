package com.zcy.domain;


import java.util.Date;

/**
 * Created by zcy on 2018/8/14.
 */
public class CommentInfo {
    private int id;
    private int paperid;
    private String content;
    private String pubid;
    private String flag;
    private String pubtime;
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubid() {
        return pubid;
    }

    public void setPubid(String pubid) {
        this.pubid = pubid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
