package com.ideashin.attendance.entity;

import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:25
 * @Blog: ideashin.com
 */
public class Note {
    private Integer noteId;
    private Integer departmentId;
    private Integer employeeId;
    private Integer noteTypeId;
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
    private Integer adminId;
    private String noteMemo;
    private Integer operatorId;
    private String isVerify;

    /**
     * 查询中需要使用的额外字段
     */
    /** 员工卡号 */
    private String cardNumber;
    /** 申请人姓名 */
    private String employeeName;
    /** 二级部门编号,等效于数据库中的departmentId */
    private String twoDId;
    /** 二级部门名称 */
    private String twoDName;
    /** 一级部门编号 */
    private String oneDId;
    /** 一级部门名称 */
    private String oneName;
    /** 请假类型名称 */
    private String typeName;
    /** 代理人姓名 */
    private String operatorName;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getNoteTypeId() {
        return noteTypeId;
    }

    public void setNoteTypeId(Integer noteTypeId) {
        this.noteTypeId = noteTypeId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getNoteMemo() {
        return noteMemo;
    }

    public void setNoteMemo(String noteMemo) {
        this.noteMemo = noteMemo;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(String isVerify) {
        this.isVerify = isVerify;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTwoDId() {
        return twoDId;
    }

    public void setTwoDId(String twoDId) {
        this.twoDId = twoDId;
    }

    public String getTwoDName() {
        return twoDName;
    }

    public void setTwoDName(String twoDName) {
        this.twoDName = twoDName;
    }

    public String getOneDId() {
        return oneDId;
    }

    public void setOneDId(String oneDId) {
        this.oneDId = oneDId;
    }

    public String getOneName() {
        return oneName;
    }

    public void setOneName(String oneName) {
        this.oneName = oneName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
