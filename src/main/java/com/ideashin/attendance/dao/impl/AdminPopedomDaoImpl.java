package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.AdminPopedomDao;
import com.ideashin.attendance.entity.AdminPopedom;
import com.ideashin.attendance.util.DBHelper;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/21 13:56
 * @Blog: ideashin.com
 */
public class AdminPopedomDaoImpl implements AdminPopedomDao {
    @Override
    public Boolean insert(AdminPopedom adminPopedom) {
        String sql = "INSERT INTO Att_AdminPopedom VALUES(?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                adminPopedom.getDepartmentID(),
                adminPopedom.getAdminID()
        );
    }

    @Override
    public Boolean delete(Integer popedomID) {
        String sql = "DELETE FROM Att_AdminPopedom WHERE PopedomID = ?";
        return DBHelper.execUpdate(sql, popedomID);
    }
}
