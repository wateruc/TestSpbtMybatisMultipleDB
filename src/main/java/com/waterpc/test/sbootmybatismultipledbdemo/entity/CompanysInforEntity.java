package com.waterpc.test.sbootmybatismultipledbdemo.entity;

import java.util.Date;

public class CompanysInforEntity {
    private Long id;

    private String name;

    private Date registertime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }
}