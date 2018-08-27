package com.zcy.domain;


import java.io.Serializable;

/**
 * Created by zcy on 2018/8/1.
 */
public class Userinfo implements Serializable{
    private int id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String telnum;
    private String sex;
    private String pub;
    private String collids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getCollids() {
        return collids;
    }

    public void setCollids(String collids) {
        this.collids = collids;
    }
}
