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
    void insert(Department department);

    /**
     * 更新
     * @param department
     */
    void update(Department department);

    /**
     * 查询所有
     */
    List<Department> selectAll();

}
