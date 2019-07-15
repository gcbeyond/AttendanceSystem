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
     * 查询所有一级部门
     */
    List<Department> findAllFirst();

    /**
     * 查询所有二级部门
     */
    List<Department> findAllSecondFromOne();

    /**
     * 删除单条
     * @param departmentID
     * @return
     */
    Boolean removeOne(int departmentID);

    /**
     * 返回json下拉列表
     */
    String departmentTree();

}
