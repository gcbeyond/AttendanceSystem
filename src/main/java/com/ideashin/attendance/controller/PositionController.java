package com.ideashin.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.ideashin.attendance.entity.Position;
import com.ideashin.attendance.service.PositionService;
import com.ideashin.attendance.service.impl.PositionServiceImpl;

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
 * @Date: 2019/7/14 23:13
 * @Blog: ideashin.com
 */
public class PositionController extends HttpServlet {
    private PositionService positionService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getAttribute("opt").toString();

        switch (opt) {
            case "findAllPositions":
                findAllPositions(req, resp);
                break;
            case "removeOnePosition":
                removeOnePosition(req, resp);
                break;
            case "addOnePosition":
                addOnePosition(req, resp);
                break;
            case "editOnePosition":
                editOnePosition(req, resp);
                break;
             case "positionTree":
                 positionTree(req, resp);
                break;
             default:
        }
    }

    /**
     * 查找所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findAllPositions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Position> list = positionService.findAll();
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
    public void removeOnePosition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer positionID = Integer.valueOf(req.getParameter("positionID"));
        Boolean data = positionService.removeOne(positionID);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 添加单条
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addOnePosition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String positionName = req.getParameter("positionName");

        Position position = new Position();
        position.setPositionName(positionName);

        Boolean data = positionService.addOne(position);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 修改单条
     * @param req
     * @param resp
     * @throws IOException
     */
    public void editOnePosition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer positionID = Integer.valueOf(req.getParameter("positionID"));
        String positionName = req.getParameter("positionName");

        Position position = new Position();
        position.setPositionID(positionID);
        position.setPositionName(positionName);

        Boolean data = positionService.editOne(position);

        PrintWriter out = resp.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

    /**
     * 职位下拉框
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void positionTree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionService positionService = new PositionServiceImpl();
        String json = positionService.positionTree();

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
    @Override
    public void destroy() {
        super.destroy();
        positionService = null;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        positionService = new PositionServiceImpl();
    }
}
