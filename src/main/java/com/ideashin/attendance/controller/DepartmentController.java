package com.ideashin.attendance.controller;

import com.ideashin.attendance.service.DepartmentService;
import com.ideashin.attendance.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Shin
 * @Date: 2019/7/15 1:26
 * @Blog: ideashin.com
 */
public class DepartmentController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "departmentTree":
                departmentTree(req, resp);
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
        DepartmentService departmentService = new DepartmentServiceImpl();
        String json = departmentService.departmentTree();

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
