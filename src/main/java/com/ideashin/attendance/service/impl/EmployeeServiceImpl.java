package com.ideashin.attendance.service.impl;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.dao.EmployeeDao;
import com.ideashin.attendance.dao.impl.EmployeeDaoImpl;
import com.ideashin.attendance.entity.Employee;
import com.ideashin.attendance.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:59
 * @Blog: ideashin.com
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public Boolean addOne(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Override
    public Boolean editOne(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public List<Employee> findAll(int page, int rows) {
        int offset = (page - 1) * rows;
        return employeeDao.selectAll(offset, rows);
    }

    @Override
    public List findSome(String empSearch, String deptSelect) {
        empSearch = "%" + empSearch + "%";
        return employeeDao.selectSome(empSearch, deptSelect);
    }

    @Override
    public List findEmpFromDept(int departmentID) {
        return employeeDao.selectEmpFromDept(departmentID);

    }

    @Override
    public Boolean removeOne(int employeeID) {
        return employeeDao.deleteOne(employeeID);
    }

    @Override
    public int getCount() {
        return employeeDao.getCount();
    }
}
