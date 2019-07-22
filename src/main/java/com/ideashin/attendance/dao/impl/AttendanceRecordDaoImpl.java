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
        String sql = "INSERT INTO Att_AttendanceRecord VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                attendanceRecord.getEmployeeID(),
                attendanceRecord.getCardNumber(),
                new java.sql.Date(attendanceRecord.getAttendanceDate().getTime()),
                attendanceRecord.getAttendanceTime(),
                attendanceRecord.getAttendanceType(),
                attendanceRecord.getAttendanceMemo(),
                attendanceRecord.getAdminID(),
                attendanceRecord.getTempDepartmentID(),
                attendanceRecord.getNoteID()
        );
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
                "    ee.departmentID tempDepartmentID,\n" +
                "    (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = ee.DepartmentID) DepartmentName,\n" +
                "    ar.AttendanceID,\n" +
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
    public List selectSome(Integer deptSelect, Date attendanceDate, String attendanceTime, int offset, int rows) {
        String sql = "SELECT\n" +
                "    ee.CardNumber,\n" +
                "    ee.EmployeeID,\n" +
                "    ee.EmployeeName,\n" +
                "    ee.departmentID tempDepartmentID,\n" +
                "    (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = ee.DepartmentID) DepartmentName,\n" +
                "    ar.AttendanceID,\n" +
                "    ar.AttendanceDate,\n" +
                "    ar.AttendanceTime,\n" +
                "    ar.AttendanceType,\n" +
                "    ar.AttendanceMemo,\n" +
                "    ar.AdminID,\n" +
                "    ar.NoteId\n" +
                "FROM\n" +
                "    Att_Employee ee LEFT JOIN Att_AttendanceRecord ar " +
                "       ON ee.EmployeeID = ar.EmployeeID\n" +
                "    AND (ar.AttendanceDate = ? OR ? IS NULL OR ? = '') \n" +
                "    AND ar.AttendanceTime = ? \n" +
                " LEFT OUTER JOIN Att_Department dt  " +
                "       ON ee.DepartmentID = dt.DepartmentID  "+
                "WHERE\n" +
                "    (ee.DepartmentID = ? OR ? IS NULL OR ? = '') \n" +
                "LIMIT ?, ?\n";
        java.sql.Date attDate = null;
        if (attendanceDate != null && !"".equals(attendanceDate)) {
            attDate = new java.sql.Date(attendanceDate.getTime());
        }
        return DBHelper.execQuery(sql, AttendanceRecord.class,
                attDate,
                attDate,
                attDate,
                attendanceTime,
                deptSelect,
                deptSelect,
                deptSelect,
                offset,
                rows);
    }

    @Override
    public List selectStatsToday(Date attendanceDate, String attendanceTime) {
        String sql = "SELECT\n" +
                "\t\tee.EmployeeName,\n" +
                "\t\t(SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = ee.DepartmentID) secondDName,\n" +
                "\t\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "\t\tar.AttendanceDate,\n" +
                "\t\tar.AttendanceTime,\n" +
                "\t\t(SELECT TypeName FROM att_attendancetype WHERE TypeID = ar.AttendanceType) TypeName\n" +
                "FROM\n" +
                "\t\tAtt_Employee ee LEFT JOIN Att_AttendanceRecord ar\n" +
                "\t\t\t ON ee.EmployeeID = ar.EmployeeID\n" +
                "\t\tLEFT JOIN att_Department dt\n" +
                "\t\t\tON ee.DepartmentID = dt.DepartmentID " +
                " WHERE\n" +
                "\t\tar.AttendanceDate = ?\n" +
                "\t\tAND ar.AttendanceTime = ?";
        return DBHelper.execQuery(sql, AttendanceRecord.class,
                new java.sql.Date(attendanceDate.getTime()),
                attendanceTime
                );
    }



    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM Att_AttendanceRecord";
        return DBHelper.getCount(sql);
    }

    @Override
    public Boolean delete(Integer attendanceID) {
        String sql = "DELETE FROM Att_AttendanceRecord WHERE AttendanceID = ?";
        return DBHelper.execUpdate(sql, attendanceID);
    }
}
