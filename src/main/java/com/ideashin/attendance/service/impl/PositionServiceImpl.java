package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.PositionDao;
import com.ideashin.attendance.dao.impl.PositionDaoImpl;
import com.ideashin.attendance.entity.Position;
import com.ideashin.attendance.service.PositionService;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/14 23:00
 * @Blog: ideashin.com
 */
public class PositionServiceImpl implements PositionService {
    private PositionDao positionDao;

    public PositionServiceImpl() {
        positionDao = new PositionDaoImpl();
    }

    @Override
    public Boolean addOne(Position position) {
        return positionDao.insert(position);
    }

    @Override
    public Boolean editOne(Position position) {
        return positionDao.update(position);
    }

    @Override
    public List<Position> findAll() {
        return positionDao.selectAll();
    }

    @Override
    public String positionTree() {
        List<Position> list = positionDao.selectAll();
        String json = "[";
        for (Position d1 : list) {
            json = json + "{ \"id\": " + d1.getPositionID() + ", \"text\": \"" + d1.getPositionName() + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        return json;
    }

    @Override
    public Boolean removeOne(int positionID) {
        return positionDao.deleteOne(positionID);
    }
}
