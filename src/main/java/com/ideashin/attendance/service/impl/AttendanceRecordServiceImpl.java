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
    public Boolean addAttendanceRecord(AttendanceRecord attendanceRecord) {
        return attendanceRecordDao.addAttendanceRecord(attendanceRecord);
    }

    @Override
    public List findAllAttendanceRecords(int page, int rows) {
        int offset = (page - 1) * rows;
        return attendanceRecordDao.selectAll(offset, rows);
    }

    @Override
    public List findSomeAttendanceRecords(Integer deptSelect, Date attendanceDate, String attendanceTime, int page, int rows) {
        return attendanceRecordDao.selectSome(deptSelect, attendanceDate, attendanceTime, page, rows);
    }

    @Override
    public List selectStatsToday(Date attendanceDate, String attendanceTime) {
        return attendanceRecordDao.selectStatsToday(attendanceDate, attendanceTime);
    }

    @Override
    public int getCount() {
        return attendanceRecordDao.getCount();
    }

    @Override
    public Boolean removeOne(Integer attendanceID) {
        return attendanceRecordDao.delete(attendanceID);
    }

    @Override
    public List findAttendanceStatsDept(String year, String month) {
        return attendanceRecordDao.findAttendanceStatsDept(year, month);
    }

    @Override
    public List findAttendanceStatsDept(Date date1, Date date2) {
        return attendanceRecordDao.findAttendanceStatsDept(date1, date2);
    }

    @Override
    public List findAttendanceStatsDept(Integer departmentID) {
        return attendanceRecordDao.findAttendanceStatsDept(departmentID);
    }

    @Override
    public List findAttendanceStatsAll(String year, String month, String day) {
        return attendanceRecordDao.findAttendanceStatsAll(year, month, day);
    }

    @Override
    public List findAttendanceStatsAll(Date date1, Date date2, String checkType, Integer departmentID, String employeeName) {
        employeeName = "%" + employeeName + "%";
        return attendanceRecordDao.findAttendanceStatsAll(date1, date2, checkType, departmentID, employeeName);
    }

}
