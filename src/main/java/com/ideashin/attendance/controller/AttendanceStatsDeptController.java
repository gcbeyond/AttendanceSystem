package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.AttendanceRecord;
import com.ideashin.attendance.service.AttendanceRecordService;
import com.ideashin.attendance.service.impl.AttendanceRecordServiceImpl;

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
 * @Date: 2019/7/22 9:13
 * @Blog: ideashin.com
 */
public class AttendanceStatsDeptController extends HttpServlet {
    private AttendanceRecordService attendanceRecordService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAttendanceStatsDept":
                findAttendanceStatsDept(req, resp);
                break;
            default:
        }
    }

    /**
     *  部门综合查询搜索
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAttendanceStatsDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AttendanceRecord> list = null;
        String searchRadio = req.getParameter("searchRadio");

        if ("1".equals(searchRadio)) {
            String year = req.getParameter("year");
            String month = req.getParameter("month");
            list = attendanceRecordService.findAttendanceStatsDept(year, month);
        } else if ("2".equals(searchRadio)) {
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date1"));
                date2 = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date2"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list = attendanceRecordService.findAttendanceStatsDept(date1, date2);
        } else {
            Integer departmentID = Integer.valueOf(req.getParameter("departmentID"));
            list = attendanceRecordService.findAttendanceStatsDept(departmentID);
        }

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("total", list.size());
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
