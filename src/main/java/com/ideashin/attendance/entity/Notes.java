package com.ideashin.attendance.entity;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:25
 * @Blog: ideashin.com
 */
public class Notes {
    private int noteID;
    private int departmentID;
    private int employeeID;
    private int noteTypeID;
    private String Cause;
    private Date fillInTime;
    private String directorSign;
    private String administrationSign;
    private String presidentSign;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private String vehicle;
    private String projectName;
    private int adminID;
    private String noteMemo;
    private int operatorID;
    private String isVerify;

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getNoteTypeID() {
        return noteTypeID;
    }

    public void setNoteTypeID(int noteTypeID) {
        this.noteTypeID = noteTypeID;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }

    public Date getFillInTime() {
        return fillInTime;
    }

    public void setFillInTime(Date fillInTime) {
        this.fillInTime = fillInTime;
    }

    public String getDirectorSign() {
        return directorSign;
    }

    public void setDirectorSign(String directorSign) {
        this.directorSign = directorSign;
    }

    public String getAdministrationSign() {
        return administrationSign;
    }

    public void setAdministrationSign(String administrationSign) {
        this.administrationSign = administrationSign;
    }

    public String getPresidentSign() {
        return presidentSign;
    }

    public void setPresidentSign(String presidentSign) {
        this.presidentSign = presidentSign;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getNoteMemo() {
        return noteMemo;
    }

    public void setNoteMemo(String noteMemo) {
        this.noteMemo = noteMemo;
    }

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    public String getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }
}
