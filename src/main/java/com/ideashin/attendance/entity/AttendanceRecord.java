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

    /** 部门综合查询需要额外添加的字段 */
    private String status1;
    private String status3;
    private String status4;
    private String status9;
    private String status10;
    private String status11;
    private String status12;
    private String status13;

    /** 综合查询需要额外添加的字段 */
    private String attendanceAM;
    private String attendancePM;

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

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus3() {
        return status3;
    }

    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    public String getStatus4() {
        return status4;
    }

    public void setStatus4(String status4) {
        this.status4 = status4;
    }

    public String getStatus9() {
        return status9;
    }

    public void setStatus9(String status9) {
        this.status9 = status9;
    }

    public String getStatus10() {
        return status10;
    }

    public void setStatus10(String status10) {
        this.status10 = status10;
    }

    public String getStatus11() {
        return status11;
    }

    public void setStatus11(String status11) {
        this.status11 = status11;
    }

    public String getStatus12() {
        return status12;
    }

    public void setStatus12(String status12) {
        this.status12 = status12;
    }

    public String getStatus13() {
        return status13;
    }

    public void setStatus13(String status13) {
        this.status13 = status13;
    }

    public String getAttendanceAM() {
        return attendanceAM;
    }

    public void setAttendanceAM(String attendanceAM) {
        this.attendanceAM = attendanceAM;
    }

    public String getAttendancePM() {
        return attendancePM;
    }

    public void setAttendancePM(String attendancePM) {
        this.attendancePM = attendancePM;
    }
}
