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
 * @Date: 2019/7/22 15:38
 * @Blog: ideashin.com
 */
public class AttendanceStatsTodayController extends HttpServlet {
    private AttendanceRecordService attendanceRecordService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findStatsToday":
                findStatsToday(req, resp);
                break;
            default:
        }
    }

    /**
     * 今日报表统计
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findStatsToday(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Date attendanceDate = null;
        try {
            attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("attendanceDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String attendanceTime = req.getParameter("attendanceTime");

        List<AttendanceRecord> list = attendanceRecordService.selectStatsToday(attendanceDate, attendanceTime);
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
