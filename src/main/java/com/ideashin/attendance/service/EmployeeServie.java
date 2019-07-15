package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Employee;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:56
 * @Blog: ideashin.com
 */
public interface EmployeeServie {

    /**
     * 增加
     * @param employee
     */
    Boolean addOne(Employee employee);

    /**
     * 更新
     * @param employee
     */
    Boolean editOne(Employee employee);

    /**
     * 查询所有
     */
    List<Employee> findAll();

    /**
     * 条件查询
     * @param empSearch
     * @param deptSelect
     * @return
     */
    List findSomeEmployees(String empSearch, String deptSelect);

    /**
     * 删除单条
     * @param employeeID
     * @return
     */
    Boolean removeOne(int employeeID);
}
