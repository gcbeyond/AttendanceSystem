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
 * @Date: 2019/7/22 9:14
 * @Blog: ideashin.com
 */
public class AttendanceStatsAllController extends HttpServlet {
    private AttendanceRecordService attendanceRecordService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAttendanceStatsAll":
                findAttendanceStatsAll(req, resp);
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
    public void findAttendanceStatsAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AttendanceRecord> list = null;
        String searchRadio = req.getParameter("searchRadio");

        if ("1".equals(searchRadio)) {
            String year = req.getParameter("year");
            String month = req.getParameter("month");
            String day = req.getParameter("day");

            list = attendanceRecordService.findAttendanceStatsAll(year, month, day);
        } else {
            String date1S = req.getParameter("date1");
            String date2S = req.getParameter("date2");
            Date date1 = null;
            Date date2 = null;
            if (!"undefined".equals(date1S) && date1S != null) {
                try {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1S);
                    date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2S);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            String checkType = req.getParameter("checkType");
            if (checkType != null && !"".equals(checkType)) {
                checkType = checkType.substring(0, checkType.length() - 1);

            }

            String dept = req.getParameter("departmentID");
            Integer departmentID = null;
            if (!"全部".equals(dept) && dept != null) {
                departmentID = Integer.valueOf(req.getParameter("departmentID"));
            }

            String employeeName = req.getParameter("employeeName");

            System.out.println(date1);
            System.out.println(date2);
            System.out.println(checkType);
            System.out.println(departmentID);
            System.out.println(employeeName);
            list = attendanceRecordService.findAttendanceStatsAll(date1, date2, checkType, departmentID, employeeName);
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
