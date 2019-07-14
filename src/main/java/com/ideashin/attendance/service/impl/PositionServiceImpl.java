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
    public List<Position> findAllPositions() {
        return positionDao.selectAll();
    }

    @Override
    public Boolean removeOnePosition(int positionID) {
        return positionDao.deleteOne(positionID);
    }
}
