package com.waterpc.test.sbootmybatismultipledbdemo.entity;

public class UsersInforEntity {
    private Long id;

    private String name;

    private Integer age;

    private String country;

    private String identifycode;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getIdentifycode() {
        return identifycode;
    }

    public void setIdentifycode(String identifycode) {
        this.identifycode = identifycode == null ? null : identifycode.trim();
    }
}