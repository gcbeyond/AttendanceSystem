package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.DepartmentDao;
import com.ideashin.attendance.entity.Department;
import com.ideashin.attendance.util.DBHelper;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/13 10:30
 * @Blog: ideashin.com
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public Boolean insertFirst(Department department) {
        String sql = "INSERT INTO Att_Department VALUES(?, ?, ?, ?, ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                department.getDepartmentName(),
                department.getStartTimeAM(),
                department.getEndTimeAM(),
                department.getStartTimePM(),
                department.getEndTimePM(),
                0
        );
    }

    @Override
    public Boolean insertSecond(Department department) {
        String sql = "INSERT INTO Att_Department VALUES(?, ?, ?, ?, ?, ? ," +
                "(SELECT d.departmentID FROM Att_Department d WHERE d.departmentName = ? AND parentID = 0))";
        return DBHelper.execUpdate(sql,
                null,
                department.getDepartmentName(),
                department.getStartTimeAM(),
                department.getEndTimeAM(),
                department.getStartTimePM(),
                department.getEndTimePM(),
                department.getDeptSelect()
        );
    }

    @Override
    public Boolean update(Department department) {
        String sql = "UPDATE Att_Department SET " +
                "   DepartmentName = ?," +
                "   StartTimeAM = ?," +
                "   EndTimeAM = ?," +
                "   StartTimePM = ?," +
                "   EndTimePM = ?," +
                "WHERE " +
                "   DepartmentID = ?";
        return DBHelper.execUpdate(sql,
                department.getDepartmentName(),
                department.getStartTimeAM(),
                department.getEndTimeAM(),
                department.getStartTimePM(),
                department.getEndTimePM(),
                department.getDepartmentID()
        );
    }

    @Override
    public List selectAll() {
        String sql = "SELECT * FROM Att_Department";
        return DBHelper.execQuery(sql, Department.class);
    }

    @Override
    public List selectAllFirst(int offset, int rows) {
        String sql = "SELECT * FROM Att_Department WHERE ParentID = 0 " +
                "\tLIMIT ?, ?";
        return DBHelper.execQuery(sql, Department.class, offset, rows);
    }

    @Override
    public List selectAllSecondFromFirst(int parentID) {
        String sql = "SELECT * FROM Att_Department WHERE ParentID = ?";
        return DBHelper.execQuery(sql, Department.class, parentID);
    }

    @Override
    public Boolean deleteOne(int departmentID) {
        String sql = "DELETE FROM Att_Department WHERE departmentID = ?";
        return DBHelper.execUpdate(sql, departmentID);
    }

    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM Att_Department";
        return DBHelper.getCount(sql);
    }
}
