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
     * 增加一级部门
     * @param department
     */
    Boolean insertFirst(Department department);

    /**
     * 增加二级部门
     * @param department
     * @return
     */
    Boolean insertSecond(Department department);

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
    List selectAllFirst(int offset, int rows);

    /**
     * 查询属于某个一级部门的所有二级部门
     * @return
     */
    List selectAllSecondFromFirst(int parentID);

    /**
     * 删除单条
     * @param departmentID
     * @return
     */
    Boolean deleteOne(int departmentID);

    /**
     * 查询条数
     * @return
     */
    int getCount();
}
