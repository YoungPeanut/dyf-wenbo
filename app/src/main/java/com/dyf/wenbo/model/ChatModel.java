package com.dyf.wenbo.model;

import java.util.Date;

public class ChatModel {
    private Integer id;

    private String chathxid;

    private String chatname;

    private Date createtime;

    private String chatcontent;

    private Integer chattype;

    private Integer chatstate;

    private Integer chatuserid;

    private UserModel user;

    private String other;

    private String chatimage;

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChathxid() {
        return chathxid;
    }

    public void setChathxid(String chathxid) {
        this.chathxid = chathxid == null ? null : chathxid.trim();
    }

    public String getChatname() {
        return chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname == null ? null : chatname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getChatcontent() {
        return chatcontent;
    }

    public void setChatcontent(String chatcontent) {
        this.chatcontent = chatcontent == null ? null : chatcontent.trim();
    }

    public Integer getChattype() {
        return chattype;
    }

    public void setChattype(Integer chattype) {
        this.chattype = chattype;
    }

    public Integer getChatstate() {
        return chatstate;
    }

    public void setChatstate(Integer chatstate) {
        this.chatstate = chatstate;
    }

    public Integer getChatuserid() {
        return chatuserid;
    }

    public void setChatuserid(Integer chatuserid) {
        this.chatuserid = chatuserid;
    }
    public String getChatimage() {
        return chatimage;
    }

    public void setChatimage(String chatimage) {
        this.chatimage = chatimage == null ? null : chatimage.trim();
    }
}