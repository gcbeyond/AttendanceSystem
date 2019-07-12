package com.ideashin.attendance.controller;

import com.ideashin.attendance.service.NoteService;
import com.ideashin.attendance.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Shin
 * @Date: 2019/7/12 11:01
 * @Blog: ideashin.com
 */
public class NoteController extends HttpServlet {
    private NoteService noteService = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
