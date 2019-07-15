package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Department;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/13 10:30
 * @Blog: ideashin.com
 */
public interface DepartmentDao {

    /**
     * 增加
     * @param department
     */
    Boolean insert(Department department);

    /**
     * 更新
     * @param department
     */
    Boolean update(Department department);

    /**
     * 查询所有
     */
    List selectAll();

    /**
     * 查询所有的一级部门
     * @return
     */
    List selectAllFirst();

    /**
     * 查询属于某个一级部门的所有二级部门
     * @return
     */
    List selectAllSecondFromFirst(int parentID);
}
