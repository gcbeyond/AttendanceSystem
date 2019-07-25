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
    public Boolean addAttendanceRecord(AttendanceRecord attendanceRecord) {
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
                "\t\t(SELECT TypeName FROM att_AttendanceType WHERE TypeID = ar.AttendanceType) TypeName\n" +
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

    @Override
    public List findAttendanceStatsDept(String year, String month) {
        String sql = "SELECT\n" +
                "    emp.EmployeeID, \n" +
                "    emp.CardNumber, \n" +
                "    emp.EmployeeName, \n" +
                "    emp.CardNumber, \n" +
                "\t\t(SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = emp.DepartmentID) secondDName,\n" +
                "\t\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "    count(case when att.AttendanceType = 1 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status1,\n" +
                "    count(case when att.AttendanceType = 3 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status3,\n" +
                "    count(case when att.AttendanceType = 4 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status4,\n" +
                "    count(case when att.AttendanceType = 9 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status9,\n" +
                "    count(case when att.AttendanceType = 10 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status10,\n" +
                "    count(case when att.AttendanceType = 11 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status11,\n" +
                "    count(case when att.AttendanceType = 12 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status12,\n" +
                "    count(case when att.AttendanceType = 13 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status13\n" +
                "FROM att_employee emp\n" +
                "         INNER JOIN Att_AttendanceRecord att\n" +
                "        ON emp.EmployeeID = att.EmployeeID\n" +"         " +
                "       LEFT JOIN Att_Department dt \n" +
                "       ON emp.DepartmentID = dt.DepartmentID \n" +
                "WHERE\n" +
                "        (YEAR(att.AttendanceDate) = ? OR ? IS NULL OR ? = '全部')\n" +
                "    AND (MONTH(att.AttendanceDate) = ? OR ? IS NULL OR ? = '全部')\n" +
                "GROUP BY\n" +
                "    emp.EmployeeID , emp.CardNumber";
        return DBHelper.execQuery(sql, AttendanceRecord.class,
                year, year, year,
                month, month, month
        );
    }

    @Override
    public List findAttendanceStatsDept(Date date1, Date date2) {
        String sql = "SELECT \n" +
                "    emp.EmployeeID, \n" +
                "    emp.CardNumber, \n" +
                "    emp.EmployeeName, \n" +
                "    emp.CardNumber, \n" +
                "\t\t(SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = emp.DepartmentID) secondDName,\n" +
                "\t\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "    count(case when att.AttendanceType = 1 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status1,\n" +
                "    count(case when att.AttendanceType = 3 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status3,\n" +
                "    count(case when att.AttendanceType = 4 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status4,\n" +
                "    count(case when att.AttendanceType = 9 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status9,\n" +
                "    count(case when att.AttendanceType = 10 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status10,\n" +
                "    count(case when att.AttendanceType = 11 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status11,\n" +
                "    count(case when att.AttendanceType = 12 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status12,\n" +
                "    count(case when att.AttendanceType = 13 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status13\n" +
                "FROM att_employee emp\n" +
                "        INNER JOIN Att_AttendanceRecord att\n" +
                "        ON emp.EmployeeID = att.EmployeeID\n" +"         " +
                "       LEFT JOIN Att_Department dt \n" +
                "       ON emp.DepartmentID = dt.DepartmentID \n" +
                "WHERE\n" +
                "    att.AttendanceDate BETWEEN ? AND ? \n" +
                "GROUP BY\n" +
                "    emp.EmployeeID , emp.CardNumber";
        return DBHelper.execQuery(sql, AttendanceRecord.class,
                new java.sql.Date(date1.getTime()),
                new java.sql.Date(date2.getTime())
        );
    }

    @Override
    public List findAttendanceStatsDept(Integer departmentID) {
        String sql = "SELECT\n" +
                "    emp.EmployeeID, \n" +
                "    emp.CardNumber, \n" +
                "    emp.EmployeeName, \n" +
                "    emp.CardNumber, \n" +
                "\t\t(SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = emp.DepartmentID) secondDName,\n" +
                "\t\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "    count(case when att.AttendanceType = 1 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status1,\n" +
                "    count(case when att.AttendanceType = 3 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status3,\n" +
                "    count(case when att.AttendanceType = 4 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status4,\n" +
                "    count(case when att.AttendanceType = 9 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status9,\n" +
                "    count(case when att.AttendanceType = 10 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status10,\n" +
                "    count(case when att.AttendanceType = 11 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status11,\n" +
                "    count(case when att.AttendanceType = 12 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status12,\n" +
                "    count(case when att.AttendanceType = 13 then\n" +
                "                   att.AttendanceType\n" +
                "        end) status13\n" +
                "FROM att_employee emp\n" +
                "         INNER JOIN Att_AttendanceRecord att\n" +
                "        ON emp.EmployeeID = att.EmployeeID\n" +"         " +
                "       LEFT JOIN Att_Department dt \n" +
                "       ON emp.DepartmentID = dt.DepartmentID \n" +
                "WHERE\n" +
                "   emp.DepartmentID = ? \n" +
                "GROUP BY\n" +
                "    emp.EmployeeID , emp.CardNumber";
        return DBHelper.execQuery(sql, AttendanceRecord.class,
               departmentID
        );
    }

    @Override
    public List findAttendanceStatsAll(String year, String month, String day) {
        String sql = "SELECT emp.CardNumber,\n" +
                "       emp.EmployeeName,\n" +
                "       (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = emp.DepartmentID) secondDName,\n" +
                "       (SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dep.ParentID)           FirstDName,\n" +
                "       min(CASE WHEN att.AttendanceDate =  att.AttendanceDate THEN att.AttendanceDate END)  AttendanceDate, \n" +
                "       min(CASE WHEN att.AttendanceTime = '上午' THEN type.TypeName END) attendanceAM,\n" +
                "       min(CASE WHEN att.AttendanceTime = '下午' THEN type.TypeName END) attendancePM\n" +
                "FROM Att_Employee emp\n" +
                "         LEFT OUTER JOIN Att_Department dep\n" +
                "                         ON emp.DepartmentID = dep.DepartmentID\n" +
                "         LEFT OUTER JOIN Att_Department depOne\n" +
                "                         ON emp.DepartmentID = depOne.DepartmentID\n" +
                "         INNER JOIN Att_AttendanceRecord att\n" +
                "                    ON att.EmployeeID = emp.EmployeeID\n" +
                "         INNER JOIN Att_AttendanceType type\n" +
                "                    ON Att.AttendanceType = type.TypeID\n" +
                "WHERE\n" +
                "        (YEAR(att.AttendanceDate) = ? OR ? IS NULL OR ? = '全部')\n" +
                "    AND (MONTH(att.AttendanceDate) = ? OR ? IS NULL OR ? = '全部')\n" +
                "    AND (DAY(att.AttendanceDate) = ? OR ? IS NULL OR ? = '全部')\n" +
                "GROUP BY emp.CardNumber, emp.EmployeeID;\n";
        return DBHelper.execQuery(sql, AttendanceRecord.class,
                year, year, year,
                month, month, month,
                day, day, day
        );
    }

    @Override
    public List findAttendanceStatsAll(Date date1, Date date2, String checkType, Integer departmentID, String employeeName) {
        String sql = "SELECT emp.CardNumber,\n" +
                "       emp.EmployeeName,\n" +
                "       (SELECT DepartmentName FROM Att_Department dt WHERE dt.DepartmentID = emp.DepartmentID) secondDName,\n" +
                "       (SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dep.ParentID)           FirstDName,\n" +
                "       min(CASE WHEN att.AttendanceDate =  att.AttendanceDate THEN att.AttendanceDate END)  AttendanceDate, \n" +
                "       min(CASE WHEN att.AttendanceTime = '上午' THEN type.TypeName END) attendanceAM,\n" +
                "       min(CASE WHEN att.AttendanceTime = '下午' THEN type.TypeName END) attendancePM\n" +
                "FROM Att_Employee emp\n" +
                "         LEFT OUTER JOIN Att_Department dep\n" +
                "                         ON emp.DepartmentID = dep.DepartmentID\n" +
                "         LEFT OUTER JOIN Att_Department depOne\n" +
                "                         ON emp.DepartmentID = depOne.DepartmentID\n" +
                "         INNER JOIN Att_AttendanceRecord att\n" +
                "                    ON att.EmployeeID = emp.EmployeeID\n" +
                "         INNER JOIN Att_AttendanceType type\n" +
                "                    ON Att.AttendanceType = type.TypeID\n" +
                "WHERE\n" +
                "   ((att.AttendanceDate BETWEEN ? AND ? ) OR ? IS NULL OR ? = '')\n";

        sql =  sql +
                "    AND ((att.AttendanceType IN ("+ checkType +")) OR ? IS NULL OR ? = '')\n" +
                "    AND (emp.DepartmentID = ? OR ? IS NULL OR ? = '')\n" +
                "    AND emp.EmployeeName LIKE ?\n" +
                "GROUP BY emp.CardNumber, emp.EmployeeID\n";

        return DBHelper.execQuery(sql, AttendanceRecord.class,
                new java.sql.Date(date1.getTime()),
                new java.sql.Date(date2.getTime()),
                new java.sql.Date(date1.getTime()),
                new java.sql.Date(date1.getTime()),
                checkType, checkType,
                departmentID, departmentID, departmentID,
                employeeName
        );
    }
}
