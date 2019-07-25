package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Admin;
import com.ideashin.attendance.entity.Employee;
import com.ideashin.attendance.service.AdminService;
import com.ideashin.attendance.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author: Shin
 * @Date: 2019/7/25 14:13
 * @Blog: ideashin.com
 */
public class LoginController extends HttpServlet {
    private AdminService adminService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "loginUser":
                loginUser(req, resp);
                break;
            default:
        }
    }


    public void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminAccount = req.getParameter("adminAccount");
        String adminPwd = req.getParameter("adminPwd");

        PrintWriter out = resp.getWriter();

        Admin admin = adminService.loginUser(adminAccount);
        if (admin == null) {
            out.print(false);
        } else if (admin.getAdminPwd().equals(adminPwd)) {
            String jsonString = JSON.toJSONString(admin);
            out.print(jsonString);
        } else {
            out.print(false);
        }

        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        adminService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        adminService = new AdminServiceImpl();
    }
}
