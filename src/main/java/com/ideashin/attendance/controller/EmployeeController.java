package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Employee;
import com.ideashin.attendance.service.EmployeeService;
import com.ideashin.attendance.service.impl.EmployeeServiceImpl;

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
 * @Date: 2019/7/15 1:06
 * @Blog: ideashin.com
 */
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAllEmployees":
                findAllEmployees(req, resp);
                break;
            case "addOneEmployee":
                addOneEmployee(req, resp);
                break;
            case "editOneEmployee":
                editOneEmployee(req, resp);
                break;
            case "findSomeEmployees":
                findSomeEmployees(req, resp);
                break;
            case "removeOneEmployee":
                removeOneEmployee(req, resp);
                break;
            case "findEmpFromDept":
                findEmpFromDept(req, resp);
                break;
            default:
        }
    }

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.valueOf(req.getParameter("page"));
        int rows = Integer.valueOf(req.getParameter("rows"));
        int total = employeeService.getCount();

        List<Employee> list = employeeService.findAll(page, rows);
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
     * 条件查询
     * @param req
     * @param resp
     * @throws IOException
     */
    public void findSomeEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String empSearch = req.getParameter("empSearch");
        String deptSelect = req.getParameter("deptSelect");

        List<Employee> list = employeeService.findSome(empSearch, deptSelect);
        System.out.println("=======" + list);
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
     * 添加单条
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addOneEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String employeeName = req.getParameter("employeeName");
        String employeeGender = req.getParameter("employeeGender");
        String departmentName = req.getParameter("departmentName");
        String positionName = req.getParameter("positionName");
        String cardNumber = req.getParameter("cardNumber");
        String employeeState = req.getParameter("employeeState");
        String employeeMemo = req.getParameter("employeeMemo");


        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmployeeGender(employeeGender);
        employee.setDepartmentName(departmentName);
        employee.setPositionName(positionName);
        employee.setCardNumber(cardNumber);
        employee.setEmployeeState(employeeState);
        employee.setEmployeeMemo(employeeMemo);

        Boolean data = employeeService.addOne(employee);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 修改单条
     * @param req
     * @param resp
     * @throws IOException
     */
    public void editOneEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer employeeID = Integer.valueOf(req.getParameter("employeeID"));
        String employeeName = req.getParameter("employeeName");
        String employeeGender = req.getParameter("employeeGender");
        String departmentName = req.getParameter("departmentName");
        String positionName = req.getParameter("positionName");
        String cardNumber = req.getParameter("cardNumber");
        String employeeState = req.getParameter("employeeState");
        String employeeMemo = req.getParameter("employeeMemo");

        Employee employee = new Employee();
        employee.setEmployeeID(employeeID);
        employee.setEmployeeName(employeeName);
        employee.setEmployeeGender(employeeGender);
        employee.setDepartmentName(departmentName);
        employee.setPositionName(positionName);
        employee.setCardNumber(cardNumber);
        employee.setEmployeeState(employeeState);
        employee.setEmployeeMemo(employeeMemo);

        Boolean data = employeeService.editOne(employee);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 加载员工下拉框
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findEmpFromDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer departmentID = Integer.valueOf(req.getParameter("departmentID"));
        List<Employee> list = employeeService.findEmpFromDept(departmentID);
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
     * 删除单条
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void removeOneEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer employeeID  = Integer.valueOf(req.getParameter("employeeID"));
        Boolean data = employeeService.removeOne(employeeID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        employeeService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeServiceImpl();
    }
}
