package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.AttendanceRecord;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/18 16:06
 * @Blog: ideashin.com
 */
public interface AttendanceRecordService {
    /**
     * 增加
     * @param attendanceRecord
     */
    Boolean addOne(AttendanceRecord attendanceRecord);

    /**
     * 更新
     * @param attendanceRecord
     */
    Boolean editOne(AttendanceRecord attendanceRecord);

    /**
     * 查询所有
     * @param page
     * @param rows
     * @return
     */
    List findAll(int page, int rows);

    /**
     * 条件查询
     * @param deptSelect
     * @param attendanceDate
     * @param attendanceTime
     * @return
     */
    List findSome(Integer deptSelect, Date attendanceDate, String attendanceTime);

    /**
     * 查询条数
     * @return
     */
    int getCount();

}
