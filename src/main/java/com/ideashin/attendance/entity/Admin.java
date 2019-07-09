package com.ideashin.attendance.entity;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:02
 * @Blog: ideashin.com
 */
public class Admin {
    private int adminID;
    private String adminAccount;
    private String adminState;
    private String adminRight;
    private String AdminName;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
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
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }
}