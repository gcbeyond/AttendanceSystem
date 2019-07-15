package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Employee;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:14
 * @Blog: ideashin.com
 */
public interface EmployeeDao {
    /**
     * 增加
     * @param employee
     */
    Boolean insert(Employee employee);

    /**
     * 更新
     * @param employee
     */
    Boolean update(Employee employee);

    /**
     * 查询所有
     */
    List selectAll();

    /**
     * 条件查询
     * @param empSearch
     * @param deptSelect
     * @return
     */
    List selectSome(String empSearch, String deptSelect);

    /**
     * 删除单条
     * @param employeeID
     * @return
     */
    Boolean deleteOne(int employeeID);

}
