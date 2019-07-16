package com.ideashin.attendance.service.impl;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.dao.DepartmentDao;
import com.ideashin.attendance.dao.impl.DepartmentDaoImpl;
import com.ideashin.attendance.entity.Department;
import com.ideashin.attendance.service.DepartmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        if (department.getDeptSelect() == null || "".equals(department.getDeptSelect())) {
            return departmentDao.insertFirst(department);
        } else {
            return departmentDao.insertSecond(department);
        }
    }

    @Override
    public Boolean editOne(Department department) {
        return departmentDao.update(department);
    }

    @Override
    public List<Department> findAllFirst(int page, int rows) {
        int offset = (page - 1) * rows;
        return departmentDao.selectAllFirst(offset, rows);
    }

    @Override
    public List<Department> findAllSecondFromOne(int parentID) {
        return departmentDao.selectAllSecondFromFirst(parentID);
    }

    @Override
    public Boolean removeOne(int departmentID) {
        return departmentDao.deleteOne(departmentID);
    }

    @Override
    public String departmentFirstTree() {
        List<Department> list = departmentDao.selectAll();

        List< HashMap<String, Object>> mapL = new ArrayList();
        HashMap<String, Object> map =null;


        for (Department d1 : list) {
            if (d1.getParentID() == 0) {
                map = new HashMap<>();

                map.put("id", d1.getDepartmentID());
                map.put("text", d1.getDepartmentName());
                map.put("iconCls", "icon-left05");

                mapL.add(map);
            }
        }

        return JSON.toJSONString(mapL);
    }

    @Override
    public int getCount() {
        return departmentDao.getCount();
    }

    @Override
    public String departmentTree() {
        List<Department> list = departmentDao.selectAll();

        List< HashMap<String, Object>> map1L = new ArrayList();
        List< HashMap<String, Object>> map2L = null;
        HashMap<String, Object> map1 =null;
        HashMap<String, Object> map2 = null;


        for (Department d1 : list) {
            if (d1.getParentID() == 0) {
                map1 = new HashMap<>();
                map2L = new ArrayList();

                map1.put("id", d1.getDepartmentID());
                map1.put("text", d1.getDepartmentName());
                map1.put("iconCls", "icon-left05");

                for (Department d2 : list) {
                    if (d2.getParentID() == d1.getDepartmentID()){
                        map2 = new HashMap<>();

                        map2.put("id", d2.getDepartmentID());
                        map2.put("text", d2.getDepartmentName());
                        map2.put("iconCls", "icon-left03");

                        map2L.add(map2);
                    }
                }
                map1.put("children", map2L);
                map1L.add(map1);
            }
        }

       return JSON.toJSONString(map1L);
    }

}
