package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Department;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/13 16:07
 * @Blog: ideashin.com
 */
public interface DepartmentService {
    /**
     * 增加
     * @param department
     */
    Boolean addOne(Department department);

    /**
     * 更新
     * @param department
     */
    Boolean editOne(Department department);

    /**
     * 查询所有
     */
    List<Department> findAllDepartments();

    /**
     * 返回json下拉列表
     */
    String departmentTree();

    /**
     * 删除单条
     * @param departmentID
     * @return
     */
    Boolean removeOneDepartment(int departmentID);
}
