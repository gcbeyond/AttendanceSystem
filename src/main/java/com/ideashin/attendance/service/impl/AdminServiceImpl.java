package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.AdminDao;
import com.ideashin.attendance.dao.impl.AdminDaoImpl;
import com.ideashin.attendance.entity.Admin;
import com.ideashin.attendance.service.AdminService;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/16 14:27
 * @Blog: ideashin.com
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    public AdminServiceImpl() {
        adminDao = new AdminDaoImpl();
    }

    @Override
    public Boolean addOne(Admin admin) {
        return null;
    }

    @Override
    public Boolean editOne(Admin admin) {
        return null;
    }

    @Override
    public List<Admin> findAll(int page, int rows) {
        int offset = (page - 1) * rows;
        return adminDao.selectAll(0, 10);
    }

    @Override
    public List findSome(String adminName) {
        adminName = "%" + adminName + "%";
        return adminDao.selectSome(adminName);
    }

    @Override
    public List findOneAdmin(int adminID) {
        return adminDao.selectOneAdmin(adminID);
    }

    @Override
    public Boolean removeOne(int adminID) {
        return null;
    }

    @Override
    public int getCount() {
        return adminDao.getCount();
    }
}
