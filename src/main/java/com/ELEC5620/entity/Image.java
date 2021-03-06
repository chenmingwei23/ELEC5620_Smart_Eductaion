package com.ELEC5620.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "uid")
    private long uid;

    @Column(name  = "uname")
    private String uname;

    @Column(name = "face_token")
    private String face_token;

    @Column(name = "img_str")
    private String img_str;

    public Image() {
    }

    public Image(long uid, String uname, String face_token, String img_str) {
        this.uid = uid;
        this.uname = uname;
        this.face_token = face_token;
        this.img_str = img_str;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public String getImg_str() {
        return img_str;
    }

    public void setImg_str(String img_str) {
        this.img_str = img_str;
    }
}
