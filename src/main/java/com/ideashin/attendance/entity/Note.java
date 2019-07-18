package com.ideashin.attendance.entity;

import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:25
 * @Blog: ideashin.com
 */
public class Note {
    private Integer noteID;
    private Integer departmentID;
    private Integer employeeID;
    private Integer noteTypeID;
    private String cause;
    private Date fillInTime;
    private String directorSign;
    private String administrationSign;
    private String presidentSign;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private Integer adminID;
    private String noteMemo;
    private Integer operatorID;
    private String isVerify;

    /**
     * 查询中需要使用的额外字段
     */
    /** 员工卡号 */
    private String cardNumber;
    /** 申请人姓名 */
    private String employeeName;
    /** 二级部门编号,等效于数据库中的departmentId */
    private String SecondDID;
    /** 二级部门名称 */
    private String SecondDName;
    /** 一级部门编号 */
    private String FirstDID;
    /** 一级部门名称 */
    private String FirstDName;
    /** 请假类型名称 */
    private String typeName;
    /** 代理人卡号 */
    private String operatorCardNum;
    /** 代理人姓名 */
    private String operatorName;

    public Integer getNoteID() {
        return noteID;
    }

    public void setNoteID(Integer noteID) {
        this.noteID = noteID;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getNoteTypeID() {
        return noteTypeID;
    }

    public void setNoteTypeID(Integer noteTypeID) {
        this.noteTypeID = noteTypeID;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getNoteMemo() {
        return noteMemo;
    }

    public void setNoteMemo(String noteMemo) {
        this.noteMemo = noteMemo;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
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

    public String getSecondDID() {
        return SecondDID;
    }

    public void setSecondDID(String secondDID) {
        SecondDID = secondDID;
    }

    public String getSecondDName() {
        return SecondDName;
    }

    public void setSecondDName(String secondDName) {
        SecondDName = secondDName;
    }

    public String getFirstDID() {
        return FirstDID;
    }

    public void setFirstDID(String firstDID) {
        FirstDID = firstDID;
    }

    public String getFirstDName() {
        return FirstDName;
    }

    public void setFirstDName(String firstDName) {
        FirstDName = firstDName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOperatorCardNum() {
        return operatorCardNum;
    }

    public void setOperatorCardNum(String operatorCardNum) {
        this.operatorCardNum = operatorCardNum;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
