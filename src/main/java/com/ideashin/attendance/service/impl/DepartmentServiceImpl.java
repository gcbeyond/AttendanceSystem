package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.DepartmentDao;
import com.ideashin.attendance.dao.impl.DepartmentDaoImpl;
import com.ideashin.attendance.entity.Department;
import com.ideashin.attendance.service.DepartmentService;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/13 16:08
 * @Blog: ideashin.com
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;
    public DepartmentServiceImpl() {
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    public Boolean addOne(Department department) {
        return null;
    }

    @Override
    public Boolean editOne(Department department) {
        return null;
    }

    @Override
    public List<Department> findAllDepartments() {
        return null;
    }

    @Override
    public String departmentTree() {
        List<Department> list = departmentDao.selectAll();
        String json = "[";
        for (Department d1 : list) {
            if (d1.getParentID() == 0) {
                json = json + "{ \"id\": " + d1.getDepartmentID() + ", \"text\": \"" + d1.getDepartmentName() + "\",\"iconCls\":\"icon-left05\",\"children\": [";
                for (Department d2 : list) {
                    if (d2.getParentID() == d1.getDepartmentID()){
                        json = json + "{\"id\": " + d2.getDepartmentID() + ", \"text\": \"" + d2.getDepartmentName() + "\", \"iconCls\":\"icon-left03\"},";
                    }
                }
                json = json.substring(0, json.length() - 1);
                json = json + "]},";
            }
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        return json;
    }

    @Override
    public Boolean removeOneDepartment(int departmentID) {
        return null;
    }
}
