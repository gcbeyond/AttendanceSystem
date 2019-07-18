package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Employee;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:56
 * @Blog: ideashin.com
 */
public interface EmployeeService {

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
    List findAll(int page, int rows);

    /**
     * 条件查询
     * @param empSearch
     * @param deptSelect
     * @return
     */
    List findSome(String empSearch, String deptSelect);

    /**
     * 员工下拉列表
     * @param departmentID
     * @return
     */
    List findEmpFromDept(int departmentID);

    /**
     * 删除单条
     * @param employeeID
     * @return
     */
    Boolean removeOne(int employeeID);

    /**
     * 查询条数
     * @return
     */
    int getCount();
}
