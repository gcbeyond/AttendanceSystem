package com.ideashin.attendance.entity;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:11
 * @Blog: ideashin.com
 */
public class Employee {
    private int employeeID;
    private String employeeName;
    private String employeeGender;
    private int positionID;
    private int departmentID;
    private String cardNumber;
    private String employeState;
    private String employeeMemo;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmployeState() {
        return employeState;
    }

    public void setEmployeState(String employeState) {
        this.employeState = employeState;
    }

    public String getEmployeeMemo() {
        return employeeMemo;
    }

    public void setEmployeeMemo(String employeeMemo) {
        this.employeeMemo = employeeMemo;
    }
}
