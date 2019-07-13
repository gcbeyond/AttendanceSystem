package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Note;
import com.ideashin.attendance.service.NoteService;
import com.ideashin.attendance.service.impl.NoteServiceImpl;

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
 * @Date: 2019/7/12 11:01
 * @Blog: ideashin.com
 */

public class NoteController extends HttpServlet {
    private NoteService noteService = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAllNotes":
                findAllNotes(req, resp);
            case "removeOneNote":
                removeOneNote(req, resp);
        }
    }

    /**
     * 查询所有的请假单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAllNotes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Note> list = noteService.findAllNotes();
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
    public void removeOneNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer noteID = Integer.valueOf(req.getParameter("noteID"));
        Boolean data = noteService.removeOneNote(noteID);
        System.out.println(noteID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        noteService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        noteService = new NoteServiceImpl();
    }
}
