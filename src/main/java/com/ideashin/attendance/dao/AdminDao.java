package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Admin;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/16 11:21
 * @Blog: ideashin.com
 */
public interface AdminDao {

    /**
     * 增加
     * @param adminAccount
     * @return
     */
    Admin loginUser(String adminAccount);

    /**
     * 更新
     * @param admin
     */
    Boolean update(Admin admin);

    /**
     * 查询所有
     */
    List selectAll(int offset, int rows);

    /**
     * 条件查询
     * @param adminName
     * @return
     */
    List selectSome(String adminName);

    /**
     * 拉去账户权限列表
     * @param adminID
     * @return
     */
    List selectOneAdmin(int adminID);

    /**
     * 删除单条
     * @param adminID
     * @return
     */
    Boolean deleteOne(int adminID);

    /**
     * 查询条数
     * @return
     */
    int getCount();
}
