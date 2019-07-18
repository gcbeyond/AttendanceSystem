package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.AttendanceRecordDao;
import com.ideashin.attendance.entity.AttendanceRecord;
import com.ideashin.attendance.util.DBHelper;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/18 15:50
 * @Blog: ideashin.com
 */
public class AttendanceRecordDaoImpl implements AttendanceRecordDao {

    @Override
    public Boolean insert(AttendanceRecord attendanceRecord) {
        return null;
    }

    @Override
    public Boolean update(AttendanceRecord attendanceRecord) {
        return null;
    }

    @Override
    public List selectAll(int offset, int rows) {
        String sql = "SELECT\n" +
                "    ee.CardNumber,\n" +
                "    ee.EmployeeID,\n" +
                "    ee.EmployeeName,\n" +
                "    ee.DepartmentId,\n" +
                "    (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = ee.DepartmentID) DepartmentName,\n" +
                "    ar.AttendanceDate,\n" +
                "    ar.AttendanceTime,\n" +
                "    ar.AttendanceType,\n" +
                "    ar.AttendanceMemo,\n" +
                "    ar.AdminID,\n" +
                "    ar.NoteId\n" +
                "FROM\n" +
                "    Att_Employee ee LEFT JOIN Att_AttendanceRecord ar ON ee.EmployeeID = ar.EmployeeID\n" +
                "LIMIT ?, ?\n";
        return DBHelper.execQuery(sql, AttendanceRecord.class, offset, rows);
    }

    @Override
    public List selectSome(Integer deptSelect, Date attendanceDate, String attendanceTime) {
        String sql = "SELECT\n" +
                "    ee.CardNumber,\n" +
                "    ee.EmployeeID,\n" +
                "    ee.EmployeeName,\n" +
                "    ee.DepartmentId,\n" +
                "    (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = ee.DepartmentID) DepartmentName,\n" +
                "    ar.AttendanceDate,\n" +
                "    ar.AttendanceTime,\n" +
                "    ar.AttendanceType,\n" +
                "    ar.AttendanceMemo,\n" +
                "    ar.AdminID,\n" +
                "    ar.NoteId\n" +
                "FROM\n" +
                "    Att_Employee ee LEFT JOIN Att_AttendanceRecord ar " +
                "       ON ee.EmployeeID = ar.EmployeeID\n" +
                "    AND ar.AttendanceDate = ? \n" +
                "    AND ar.AttendanceTime = ? \n" +
                " LEFT OUTER JOIN Att_Department dt  " +
                "       ON ee.DepartmentID = dt.DepartmentID  "+
                "WHERE\n" +
                "    (ee.DepartmentID = ? OR ? IS NULL OR ? = '') \n";
//                "    AND ar.AttendanceDate = ? \n" +
//                "    AND ar.AttendanceTime = ? ";
            return DBHelper.execQuery(sql, AttendanceRecord.class,

                    new java.sql.Date(attendanceDate.getTime()),
                    attendanceTime,
                    deptSelect,
                    deptSelect,
                    deptSelect);

    }


    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM Att_AttendanceRecord";
        return DBHelper.getCount(sql);
    }

}
