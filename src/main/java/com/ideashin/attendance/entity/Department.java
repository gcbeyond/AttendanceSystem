package com.ideashin.attendance.entity;

/**
 * @Author: Shin
 * @Date: 2019/7/8 21:07
 * @Blog: ideashin.com
 */
public class Department {
    private int departmentID;
    private String departmentName;
    private String startTimeAM;
    private String endTimeAM;
    private String startTimePM;
    private String endTimePM;
    private int parentID;

    /** 用于暂存一级部门名称 */
    private String deptSelect;

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStartTimeAM() {
        return startTimeAM;
    }

    public void setStartTimeAM(String startTimeAM) {
        this.startTimeAM = startTimeAM;
    }

    public String getEndTimeAM() {
        return endTimeAM;
    }

    public void setEndTimeAM(String endTimeAM) {
        this.endTimeAM = endTimeAM;
    }

    public String getStartTimePM() {
        return startTimePM;
    }

    public void setStartTimePM(String startTimePM) {
        this.startTimePM = startTimePM;
    }

    public String getEndTimePM() {
        return endTimePM;
    }

    public void setEndTimePM(String endTimePM) {
        this.endTimePM = endTimePM;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getDeptSelect() {
        return deptSelect;
    }

    public void setDeptSelect(String deptSelect) {
        this.deptSelect = deptSelect;
    }
}
