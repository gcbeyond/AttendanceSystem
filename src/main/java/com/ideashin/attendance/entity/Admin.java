package com.ideashin.attendance.entity;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:02
 * @Blog: ideashin.com
 */
public class Admin {
    private Integer adminID;
    private String adminAccount;
    private String adminPwd;
    private String adminState;
    private String adminRight;
    private String adminName;

    private Integer popedomID;
    private Integer secondDID;
    private String secondDName;
    private Integer firstDID;
    private String firstDName;

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    public String getAdminRight() {
        return adminRight;
    }

    public void setAdminRight(String adminRight) {
        this.adminRight = adminRight;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getPopedomID() {
        return popedomID;
    }

    public void setPopedomID(Integer popedomID) {
        this.popedomID = popedomID;
    }

    public Integer getSecondDID() {
        return secondDID;
    }

    public void setSecondDID(Integer secondDID) {
        this.secondDID = secondDID;
    }

    public String getSecondDName() {
        return secondDName;
    }

    public void setSecondDName(String secondDName) {
        this.secondDName = secondDName;
    }

    public Integer getFirstDID() {
        return firstDID;
    }

    public void setFirstDID(Integer firstDID) {
        this.firstDID = firstDID;
    }

    public String getFirstDName() {
        return firstDName;
    }

    public void setFirstDName(String firstDName) {
        this.firstDName = firstDName;
    }
}