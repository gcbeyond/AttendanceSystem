package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.AttendanceRecord;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/18 15:36
 * @Blog: ideashin.com
 */
public interface AttendanceRecordDao {

    /**
     * 今日考勤：增加
     * @param attendanceRecord
     */
    Boolean addAttendanceRecord(AttendanceRecord attendanceRecord);

    /**
     * 今日考勤：查询所有
     */
    List selectAll(int offset, int rows);

    /**
     * 今日考勤：条件查询
     * @param deptSelect
     * @param attendanceDate
     * @param attendanceTime
     * @return
     */
    List selectSome(Integer deptSelect, Date attendanceDate, String attendanceTime, int offset, int rows);

    /**
     * 今日报表：统计查询
     * @param attendanceDate
     * @param attendanceTime
     * @return
     */
    List selectStatsToday(Date attendanceDate, String attendanceTime);

    /**
     * 查询条数
     * @return
     */
    int getCount();

    /**
     * 今日考勤：删除单个
     * @param attendanceID
     * @return
     */
    Boolean delete(Integer attendanceID);

    /**
     * 部门综合查询：年月搜索
     * @param year
     * @param month
     * @return
     */
    List findAttendanceStatsDept(String year, String month);

    /**
     * 部门综合查询：日期搜索
     * @param date1
     * @param date2
     * @return
     */
    List findAttendanceStatsDept(Date date1, Date date2);

    /**
     * 部门综合查询：部门搜索
     * @param departmentID
     * @return
     */
    List findAttendanceStatsDept(Integer departmentID);

    /**
     * 综合查询：时间搜索
     * @param year
     * @param month
     * @param day
     * @return
     */
    List findAttendanceStatsAll(String year, String month, String day);

    /**
     * 综合查询：时间间隔，请假类型，部门，用户名模糊
     * @param date1
     * @param date2
     * @param checkType
     * @param departmentID
     * @param employeeName
     * @return
     */
    List findAttendanceStatsAll(Date date1, Date date2, String checkType, Integer departmentID, String employeeName);
}
