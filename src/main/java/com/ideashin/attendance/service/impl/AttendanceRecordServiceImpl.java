package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.AttendanceRecordDao;
import com.ideashin.attendance.dao.impl.AttendanceRecordDaoImpl;
import com.ideashin.attendance.entity.AttendanceRecord;
import com.ideashin.attendance.service.AttendanceRecordService;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/18 16:10
 * @Blog: ideashin.com
 */
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    public AttendanceRecordDao attendanceRecordDao;

    public AttendanceRecordServiceImpl() {
        attendanceRecordDao = new AttendanceRecordDaoImpl();
    }

    @Override
    public Boolean addOne(AttendanceRecord attendanceRecord) {
        return null;
    }

    @Override
    public Boolean editOne(AttendanceRecord attendanceRecord) {
        return null;
    }

    @Override
    public List findAll(int page, int rows) {
        int offset = (page - 1) * rows;
        return attendanceRecordDao.selectAll(offset, rows);
    }

    @Override
    public List findSome(Integer deptSelect, Date attendanceDate, String attendanceTime, int page, int rows) {
        return attendanceRecordDao.selectSome(deptSelect, attendanceDate, attendanceTime, page, rows);
    }

    @Override
    public int getCount() {
        return attendanceRecordDao.getCount();
    }

}
