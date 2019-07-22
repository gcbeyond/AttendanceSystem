package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.AdminDao;
import com.ideashin.attendance.entity.Admin;
import com.ideashin.attendance.filter.OptFilter;
import com.ideashin.attendance.util.DBHelper;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/16 11:22
 * @Blog: ideashin.com
 */
public class AdminDaoImpl implements AdminDao {

    @Override
    public Boolean insert(Admin admin) {
        return null;
    }

    @Override
    public Boolean update(Admin admin) {
        return null;
    }

    @Override
    public List selectAll(int offset, int rows) {
        String sql = "SELECT\n" +
                "\tan.AdminID,\n" +
                "\tan.AdminAccount,\n" +
                "\tan.AdminPwd,\n" +
                "\tan.AdminName,\n" +
                "\tap.DepartmentID SecondDID,\n" +
                "\tdt.DepartmentName SecondDName,\n" +
                "\tdt.ParentID FirstDID,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "\tap.PopedomID,\n" +
                "\tan.AdminRight\n" +
                "FROM \n" +
                "\tAtt_Admin an LEFT JOIN Att_Adminpopedom ap\n" +
                "\t\tON an.AdminID = ap.AdminID\n" +
                "\tLEFT JOIN att_Department dt\n" +
                "\t\tON ap.DepartmentID = dt.DepartmentID\n" +
                "LIMIT ?, ?";
        return DBHelper.execQuery(sql, Admin.class, offset, rows);
    }

    @Override
    public List selectSome(String adminName) {
        String sql = "SELECT\n" +
                "\tan.AdminID,\n" +
                "\tan.AdminAccount,\n" +
                "\tan.AdminPwd,\n" +
                "\tan.AdminName,\n" +
                "\tap.DepartmentID SecondDID,\n" +
                "\tdt.DepartmentName SecondDName,\n" +
                "\tdt.ParentID FirstDID,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "\tap.PopedomID,\n" +
                "\tan.AdminRight\n" +
                "FROM \n" +
                "\tAtt_Admin an LEFT JOIN Att_Adminpopedom ap\n" +
                "\t\tON an.AdminID = ap.AdminID\n" +
                "\tLEFT JOIN att_Department dt\n" +
                "\t\tON ap.DepartmentID = dt.DepartmentID\n" +
                "WHERE an.AdminName LIKE ?";
        return DBHelper.execQuery(sql, Admin.class, adminName);
    }

    @Override
    public List selectOneAdmin(int adminID) {
        String sql = "SELECT\n" +
                "\tan.AdminID,\n" +
                "\tan.AdminAccount,\n" +
                "\tan.AdminPwd,\n" +
                "\tan.AdminName,\n" +
                "\tap.DepartmentID SecondDID,\n" +
                "\tdt.DepartmentName SecondDName,\n" +
                "\tdt.ParentID FirstDID,\n" +
                "\t(SELECT DepartmentName FROM Att_Department WHERE DepartmentID = dt.ParentID) FirstDName,\n" +
                "\tap.PopedomID,\n" +
                "\tan.AdminRight\n" +
                "FROM \n" +
                "\tAtt_Admin an INNER JOIN Att_Adminpopedom ap\n" +
                "\t\tON an.AdminID = ap.AdminID\n" +
                "\tLEFT JOIN att_Department dt\n" +
                "\t\tON ap.DepartmentID = dt.DepartmentID\n" +
                "WHERE an.AdminID = ?";
        return DBHelper.execQuery(sql, Admin.class, adminID);
    }

    @Override
    public Boolean deleteOne(int adminID) {
        String sql = "DELETE FROM Att_Admin WHERE AdminID = ?";
        return DBHelper.execUpdate(sql, adminID);
    }

    @Override
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM Att_Admin";
        return DBHelper.getCount(sql);
    }
}
