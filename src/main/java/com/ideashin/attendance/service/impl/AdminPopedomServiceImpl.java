package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.AdminPopedomDao;
import com.ideashin.attendance.dao.impl.AdminPopedomDaoImpl;
import com.ideashin.attendance.entity.AdminPopedom;
import com.ideashin.attendance.service.AdminPopedomService;

/**
 * @Author: Shin
 * @Date: 2019/7/21 15:55
 * @Blog: ideashin.com
 */
public class AdminPopedomServiceImpl implements AdminPopedomService {
    private AdminPopedomDao adminPopedomDao;

    public AdminPopedomServiceImpl() {
        adminPopedomDao = new AdminPopedomDaoImpl();
    }

    @Override
    public Boolean addOne(AdminPopedom adminPopedom) {
        return adminPopedomDao.insert(adminPopedom);
    }

    @Override
    public Boolean removeOne(Integer popedomID) {
        return adminPopedomDao.delete(popedomID);
    }

}
