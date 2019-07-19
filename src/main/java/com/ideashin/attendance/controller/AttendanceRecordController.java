package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.AttendanceRecord;
import com.ideashin.attendance.service.AttendanceRecordService;
import com.ideashin.attendance.service.EmployeeService;
import com.ideashin.attendance.service.impl.AttendanceRecordServiceImpl;
import com.ideashin.attendance.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/18 16:29
 * @Blog: ideashin.com
 */
public class AttendanceRecordController extends HttpServlet {
    private AttendanceRecordService attendanceRecordService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAllAttendanceRecords":
                findAllAttendanceRecords(req, resp);
                break;
            case "findSomeAttendanceRecords":
                findSomeAttendanceRecords(req, resp);
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
    public void findAllAttendanceRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.valueOf(req.getParameter("page"));
        int rows = Integer.valueOf(req.getParameter("rows"));
        EmployeeService employeeService = new EmployeeServiceImpl();
        int total = employeeService.getCount();

        List<AttendanceRecord> list = attendanceRecordService.findAll(page, rows);
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
    public void findSomeAttendanceRecords(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer deptSelect = null;
        if (!"全部".equals(req.getParameter("deptSelect"))) {
            deptSelect = Integer.valueOf(req.getParameter("deptSelect"));
        }
        String attendanceDateS =  req.getParameter("attendanceDate");
        Date attendanceDate = null;
        if (attendanceDateS != null && !attendanceDateS.equals("")) {
            try {
                attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(attendanceDateS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String attendanceTime = req.getParameter("attendanceTime");
        int page = Integer.valueOf(req.getParameter("page"));
        int rows = Integer.valueOf(req.getParameter("rows"));
        EmployeeService employeeService = new EmployeeServiceImpl();
        int total = employeeService.getCount();

        List<AttendanceRecord> list = attendanceRecordService.findSome(deptSelect, attendanceDate, attendanceTime, page, rows);
        HashMap<String, Object> map = new HashMap<>(2);

        map.put("total", total);
        map.put("rows", list);

        String jsonString = JSON.toJSONString(map);
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        attendanceRecordService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        attendanceRecordService = new AttendanceRecordServiceImpl();
    }
}
