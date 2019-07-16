package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.AdminDao;
import com.ideashin.attendance.entity.Admin;
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
//        String
        return null;
    }

    @Override
    public List selectSome(String empSearch) {
        return null;
    }

    @Override
    public Boolean deleteOne(int adminID) {
        String sql = "DELETE FROM Att_Admin WHERE AdminID = ?";
        return DBHelper.execUpdate(sql, adminID);
    }
}
