package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Admin;
import com.ideashin.attendance.entity.AdminPopedom;
import com.ideashin.attendance.service.AdminPopedomService;
import com.ideashin.attendance.service.AdminService;
import com.ideashin.attendance.service.impl.AdminPopedomServiceImpl;
import com.ideashin.attendance.service.impl.AdminServiceImpl;

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
 * @Date: 2019/7/21 14:46
 * @Blog: ideashin.com
 */
public class AdminPopedomController extends HttpServlet {
    private AdminService adminService;
    private AdminPopedomService adminPopedomService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAllAdmins":
                findAllAdmins(req, resp);
                break;
            case "findSomeAdmins":
                findSomeAdmins(req, resp);
                break;
            case "findOneAdmin":
                findOneAdmin(req, resp);
                break;
            case "addAdminPopedom":
                addAdminPopedom(req, resp);
                break;
            case "removeAdminPopedom":
                removeAdminPopedom(req, resp);
                break;
            default:
        }
    }

    /**
     * 查询所有的用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void findAllAdmins(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.valueOf(req.getParameter("page"));
        int rows = Integer.valueOf(req.getParameter("rows"));
        int total = adminService.getCount();

        List<Admin> list = adminService.findAll(page, rows);
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
     * @throws ServletException
     * @throws IOException
     */
    private void findSomeAdmins(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminName = req.getParameter("adminName");
        List<Admin> list = adminService.findSome(adminName);
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
     * 跳到分配权限
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void findOneAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer adminID = Integer.valueOf(req.getParameter("adminID"));

        List<Admin> list = adminService.findOneAdmin(adminID);
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
     * 添加权限
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addAdminPopedom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean data = false;
        Integer deptSelect = null;

        if (!"".equals(req.getParameter("departmentID")) && req.getParameter("departmentID") != null) {
            deptSelect = Integer.valueOf(req.getParameter("departmentID"));
            Integer adminID = Integer.valueOf(req.getParameter("adminID"));

            AdminPopedom adminPopedom = new AdminPopedom();
            adminPopedom.setDepartmentID(deptSelect);
            adminPopedom.setAdminID(adminID);

            data = adminPopedomService.addOnePopedom(adminPopedom);
        }

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 分配权限
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeAdminPopedom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer popedomID = Integer.valueOf(req.getParameter("popedomID"));

        boolean data = adminPopedomService.removeOnePopedom(popedomID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }



    @Override
    public void destroy() {
        super.destroy();
        adminPopedomService = null;
        adminService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        adminPopedomService = new AdminPopedomServiceImpl();
        adminService = new AdminServiceImpl();
    }
}
