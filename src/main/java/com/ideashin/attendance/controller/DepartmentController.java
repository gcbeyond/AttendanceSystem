package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Department;
import com.ideashin.attendance.service.DepartmentService;
import com.ideashin.attendance.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/15 1:26
 * @Blog: ideashin.com
 */
public class DepartmentController extends HttpServlet {
    private  DepartmentService departmentService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "departmentTree":
                departmentTree(req, resp);
                break;
            case "departmentFirstTree":
                departmentFirstTree(req, resp);
                break;
            case "findAllFirstDepartment":
                findAllFirstDepartment(req, resp);
                break;
            case "findAllSecondFromOneDepartment":
                findAllSecondFromOneDepartment(req, resp);
                break;
            case "removeOneDepartment":
                removeOneDepartment(req, resp);
                break;
             case "addOneDepartment":
                 addOneDepartment(req, resp);
                break;
             case "editOneDepartment":
                 editOneDepartment(req, resp);
                break;
            default:
        }
    }

    /**
     * 部门树形下拉框
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void departmentTree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = departmentService.departmentTree();

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    /**
     * 一级部门下拉框
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void departmentFirstTree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = departmentService.departmentFirstTree();

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    /**
     * 查找所有的一级部门
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAllFirstDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.valueOf(req.getParameter("page"));
        int rows = Integer.valueOf(req.getParameter("rows"));
        int total = departmentService.getCount();

        List<Department> list = departmentService.findAllFirst(page, rows);
        HashMap<String, Object> map = new HashMap<>(2);

        map.put("total", total);
        map.put("rows", list);

        String jsonString = JSON.toJSONString(map);
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
        out.close();
    }

    /**
     * 查找某一一级部门下的所有二级部门
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAllSecondFromOneDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int parentID = Integer.valueOf(req.getParameter("parentID"));

        List<Department> list = departmentService.findAllSecondFromOne(parentID);
        HashMap<String, Object> map = new HashMap<>(2);

        map.put("total", list.size());
        map.put("rows", list);

        String jsonString = JSON.toJSONString(map);
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
        out.close();
    }

    /**
     * 删除单个
     * @param req
     * @param resp
     * @throws IOException
     */
    public void removeOneDepartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer departmentID  = Integer.valueOf(req.getParameter("departmentID"));
        Boolean data = departmentService.removeOne(departmentID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 添加单个
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addOneDepartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String departmentName = req.getParameter("departmentName");
        String startTimeAM = req.getParameter("startTimeAM");
        String endTimeAM = req.getParameter("endTimeAM");
        String startTimePM = req.getParameter("startTimePM");
        String endTimePM = req.getParameter("endTimePM");
        String deptSelect  = req.getParameter("deptSelect");

        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setStartTimeAM(startTimeAM);
        department.setEndTimeAM(endTimeAM);
        department.setStartTimePM(startTimePM);
        department.setEndTimePM(endTimePM);
        department.setDeptSelect(deptSelect);

        Boolean data = departmentService.addOne(department);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 修改单个
     * @param req
     * @param resp
     * @throws IOException
     */
    public void editOneDepartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer departmentID = Integer.valueOf(req.getParameter("departmentID"));
        String departmentName = req.getParameter("departmentName");
        String startTimeAM = req.getParameter("startTimeAM");
        String endTimeAM = req.getParameter("endTimeAM");
        String startTimePM = req.getParameter("startTimePM");
        String endTimePM = req.getParameter("endTimePM");

        Department department = new Department();
        department.setDepartmentID(departmentID);
        department.setDepartmentName(departmentName);
        department.setStartTimeAM(startTimeAM);
        department.setEndTimeAM(endTimeAM);
        department.setStartTimePM(startTimePM);
        department.setEndTimePM(endTimePM);

        Boolean data = departmentService.editOne(department);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        departmentService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        departmentService = new DepartmentServiceImpl();
    }
}
