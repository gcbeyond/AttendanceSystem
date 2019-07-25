package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Admin;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/16 14:25
 * @Blog: ideashin.com
 */
public interface AdminService {
    /**
     * 增加
     * @param adminAccount
     */
    Admin loginUser(String adminAccount);

    /**
     * 更新
     * @param admin
     */
    Boolean editOne(Admin admin);

    /**
     * 查询所有
     */
    List<Admin> findAll(int page, int rows);

    /**
     * 条件查询
     * @param adminName
     * @return
     */
    List findSome(String adminName);

    /**
     * 拉去账户权限列表
     * @param adminID
     * @return
     */
    List findOneAdmin(int adminID);

    /**
     * 删除单条
     * @param adminID
     * @return
     */
    Boolean removeOne(int adminID);

    /**
     * 查询条数
     * @return
     */
    int getCount();
}
