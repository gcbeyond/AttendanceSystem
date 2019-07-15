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
    public Boolean insert(Department department) {
        String sql = "INSERT INTO Att_Department VALUES(?, ?, ?, ?, ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                department.getDepartmentName(),
                department.getStartTimeAM(),
                department.getEndTimeAM(),
                department.getStartTimePM(),
                department.getEndTimePM(),
                department.getParentID()
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
                "   ParentID = ?" +
                "WHERE " +
                "   DepartmentID = ?";
        return DBHelper.execUpdate(sql,
                department.getDepartmentName(),
                department.getStartTimeAM(),
                department.getEndTimeAM(),
                department.getStartTimePM(),
                department.getEndTimePM(),
                department.getParentID(),
                department.getDepartmentID()
        );
    }

    @Override
    public List selectAll() {
        String sql = "SELECT * FROM Att_Department";
        return DBHelper.execQuery(sql, Department.class);
    }

    @Override
    public List selectAllFirst() {
        String sql = "SELECT * FROM Att_Department WHERE ParentID = 0";
        return DBHelper.execQuery(sql, Department.class);
    }

    @Override
    public List selectAllSecondFromFirst(int parentID) {
        String sql = "SELECT * FROM Att_Department WHERE ParentID = ?";
        return DBHelper.execQuery(sql, Department.class, parentID);
    }
}
