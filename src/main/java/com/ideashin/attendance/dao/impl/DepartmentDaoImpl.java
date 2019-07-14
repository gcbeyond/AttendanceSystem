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
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {
    }

    @Override
    public List selectAll() {
        String sql = "SELECT * FROM Att_Department";
        return DBHelper.execQuery(sql, Department.class);
    }
}
