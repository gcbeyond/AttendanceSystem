package com.ideashin.attendance.entity;

import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:15
 * @Blog: ideashin.com
 */
public class AttendanceRecord {
    private int attendanceID;
    private int employeeID;
    private String cardNumber;
    private Date attendanceDate;
    private String attendanceTime;
    private int attendanceType;
    private String attendanceMemo;
    private int adminID;
    private int tempDepartmentID;
    private int noteID;

    private String employeeName;
    private String departmentName;
    private String secondDName;
    private String firstDName;
    private String TypeName;

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public int getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(int attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getAttendanceMemo() {
        return attendanceMemo;
    }

    public void setAttendanceMemo(String attendanceMemo) {
        this.attendanceMemo = attendanceMemo;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getTempDepartmentID() {
        return tempDepartmentID;
    }

    public void setTempDepartmentID(int tempDepartmentID) {
        this.tempDepartmentID = tempDepartmentID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public String getSecondDName() {
        return secondDName;
    }

    public void setSecondDName(String secondDName) {
        this.secondDName = secondDName;
    }


    public String getFirstDName() {
        return firstDName;
    }

    public void setFirstDName(String firstDName) {
        this.firstDName = firstDName;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
