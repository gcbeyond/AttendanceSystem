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
     * 增加
     * @param attendanceRecord
     */
    Boolean insert(AttendanceRecord attendanceRecord);

    /**
     * 更新
     * @param attendanceRecord
     */
    Boolean update(AttendanceRecord attendanceRecord);

    /**
     * 查询所有
     */
    List selectAll(int offset, int rows);

    /**
     * 条件查询
     * @param deptSelect
     * @param attendanceDate
     * @param attendanceTime
     * @return
     */
    List selectSome(Integer deptSelect, Date attendanceDate, String attendanceTime);

    /**
     * 查询条数
     * @return
     */
    int getCount();

}
