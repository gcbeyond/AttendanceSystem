package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.EmployeeDao;
import com.ideashin.attendance.entity.Employee;
import com.ideashin.attendance.util.DBHelper;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:17
 * @Blog: ideashin.com
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Boolean insert(Employee employee) {
        String sql = "INSERT INTO Att_Employee VALUES(?, ?, ?," +
                "(SELECT PositionID FROM Att_Position WHERE Att_Position.PositionName = ?)," +
                "(SELECT DepartmentID FROM Att_Department WHERE Att_Department.DepartmentName = ?)," +
                " ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                employee.getEmployeeName(),
                employee.getEmployeeGender(),
                employee.getPositionName(),
                employee.getDepartmentName(),
                employee.getCardNumber(),
                employee.getEmployeeState(),
                employee.getEmployeeMemo()
                );
    }

    @Override
    public Boolean update(Employee employee) { ;
        String sql = "UPDATE Att_Employee SET " +
                "           EmployeeName = ?, EmployeeGender = ?, " +
                "           PositionID = (SELECT  Att_Position.PositionID FROM Att_Position WHERE Att_Position.PositionName = ?)," +
                "           DepartmentID = (SELECT  Att_Department.DepartmentID FROM Att_Department WHERE Att_Department.DepartmentName = ?), " +
                "           CardNumber = ?, EmployeeState = ?,EmployeeMemo = ? " +
                "       WHERE EmployeeID = ?";
        return DBHelper.execUpdate(sql,
                employee.getEmployeeName(),
                employee.getEmployeeGender(),
                employee.getPositionName(),
                employee.getDepartmentName(),
                employee.getCardNumber(),
                employee.getEmployeeState(),
                employee.getEmployeeMemo(),
                employee.getEmployeeID()
        );
    }

    @Override
    public List selectAll(int offset, int rows) {
        String sql = "SELECT\n" +
                "\tAtt_Employee.EmployeeID,\n" +
                "\tAtt_Employee.EmployeeName,\n" +
                "\tAtt_Employee.EmployeeGender,\n" +
                "\t(SELECT PositionName FROM Att_Position WHERE Att_Position.PositionID = Att_Employee.PositionID) PositionName,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE Att_Department.DepartmentID = Att_Employee.DepartmentID) DepartmentName,\n" +
                "\tAtt_Employee.CardNumber,\n" +
                "\tAtt_Employee.EmployeeState,\n" +
                "\tAtt_Employee.EmployeeMemo\n" +
                "\tFrom Att_Employee " +
                "\tLIMIT ?, ?";

        return DBHelper.execQuery(sql, Employee.class, offset, rows);
    }

    @Override
    public List selectEmpFromDept(int departmentID) {
        String sql = "SELECT\n" +
                "\tAtt_Employee.EmployeeID,\n" +
                "\tAtt_Employee.EmployeeName,\n" +
                "\tAtt_Employee.EmployeeGender,\n" +
                "\t(SELECT PositionName FROM Att_Position WHERE Att_Position.PositionID = Att_Employee.PositionID) PositionName,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE Att_Department.DepartmentID = Att_Employee.DepartmentID) DepartmentName,\n" +
                "\tAtt_Employee.CardNumber,\n" +
                "\tAtt_Employee.EmployeeState,\n" +
                "\tAtt_Employee.EmployeeMemo\n" +
                "\tFrom Att_Employee " +
                "\tWHERE DepartmentID = ?";
        return DBHelper.execQuery(sql, Employee.class, departmentID);
    }

    @Override
    public List selectSome(String empSearch, String deptSelect) {
        String sql = "SELECT\n" +
                "\tAtt_Employee.EmployeeID,\n" +
                "\tAtt_Employee.EmployeeName,\n" +
                "\tAtt_Employee.EmployeeGender,\n" +
                "\tAtt_Employee.PositionID,\n" +
                "\t(SELECT PositionName FROM Att_Position WHERE Att_Position.PositionID = Att_Employee.PositionID) PositionName,\n" +
                "\tAtt_Employee.DepartmentID,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE Att_Department.DepartmentID = Att_Employee.DepartmentID) DepartmentName,\n" +
                "\tAtt_Employee.CardNumber,\n" +
                "\tAtt_Employee.EmployeeState,\n" +
                "\tAtt_Employee.EmployeeMemo\n" +
                "\tFrom Att_Employee\n" +
                "\tWHERE EmployeeName like ?\n" +
                "\tAND (DepartmentID = (SELECT DepartmentID FROM Att_Department WHERE DepartmentName = ?) OR ? = '全部')";

        return DBHelper.execQuery(sql, Employee.class, empSearch, deptSelect, deptSelect);
    }

    @Override
    public Boolean deleteOne(int employeeID) {
        String sql = "DELETE FROM Att_Employee WHERE EmployeeID = ?";
        return DBHelper.execUpdate(sql, employeeID);
    }


    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM Att_Employee";
        return DBHelper.getCount(sql);
    }

}
