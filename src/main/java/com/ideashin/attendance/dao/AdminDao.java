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
     * @param admin
     */
    Boolean insert(Admin admin);

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
     * @param empSearch
     * @return
     */
    List selectSome(String empSearch);

    /**
     * 删除单条
     * @param adminID
     * @return
     */
    Boolean deleteOne(int adminID);

}
