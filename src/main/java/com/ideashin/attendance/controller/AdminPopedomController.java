package com.ideashin.attendance.controller;

import com.ideashin.attendance.entity.AdminPopedom;
import com.ideashin.attendance.service.AdminPopedomService;
import com.ideashin.attendance.service.impl.AdminPopedomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/7/21 14:46
 * @Blog: ideashin.com
 */
public class AdminPopedomController extends HttpServlet {
    private AdminPopedomService adminPopedomService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "addAdminPopedom":
                addAdminPopedom(req, resp);
                break;
            case "removeAdminPopedom":
                removeAdminPopedom(req, resp);
                break;
            default:
        }
    }

    protected void addAdminPopedom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean data = false;
        Integer deptSelect = null;

        if (!"".equals(req.getParameter("departmentID")) && req.getParameter("departmentID") != null) {
            deptSelect = Integer.valueOf(req.getParameter("departmentID"));
            Integer adminID = Integer.valueOf(req.getParameter("adminID"));

            AdminPopedom adminPopedom = new AdminPopedom();
            adminPopedom.setDepartmentID(deptSelect);
            adminPopedom.setAdminID(adminID);

            data = adminPopedomService.addOne(adminPopedom);
        }

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    protected void removeAdminPopedom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer popedomID = Integer.valueOf(req.getParameter("popedomID"));

        boolean data = adminPopedomService.removeOne(popedomID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }



    @Override
    public void destroy() {
        super.destroy();
        adminPopedomService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        adminPopedomService = new AdminPopedomServiceImpl();
    }
}
