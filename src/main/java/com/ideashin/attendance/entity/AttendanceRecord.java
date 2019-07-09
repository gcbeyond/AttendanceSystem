package com.ideashin.attendance.entity;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:15
 * @Blog: ideashin.com
 */
public class AttendanceRecord {
    private int attendanceID;
    private int employeeID;
    private String cardNumber;
    private String attendanceDate;
    private String attendanceTime;
    private String attendanceFlag;
    private int attendanceType;
    private String attendanceMemo;
    private int adminID;
    private int tempDepartmentId;
    private int noteId;

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

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceFlag() {
        return attendanceFlag;
    }

    public void setAttendanceFlag(String attendanceFlag) {
        this.attendanceFlag = attendanceFlag;
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

    public int getTempDepartmentId() {
        return tempDepartmentId;
    }

    public void setTempDepartmentId(int tempDepartmentId) {
        this.tempDepartmentId = tempDepartmentId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
