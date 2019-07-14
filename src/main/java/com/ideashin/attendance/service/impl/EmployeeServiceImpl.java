package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.EmployeeDao;
import com.ideashin.attendance.dao.impl.EmployeeDaoImpl;
import com.ideashin.attendance.entity.Employee;
import com.ideashin.attendance.service.EmployeeServie;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 0:59
 * @Blog: ideashin.com
 */
public class EmployeeServiceImpl implements EmployeeServie {
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
    public List<Employee> findAll() {
        return employeeDao.selectAll();
    }

    @Override
    public Boolean removeOne(int employeeID) {
        return employeeDao.deleteOne(employeeID);
    }
}
