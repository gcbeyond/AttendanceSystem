package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.PositionDao;
import com.ideashin.attendance.entity.Position;
import com.ideashin.attendance.util.DBHelper;
import com.sun.corba.se.impl.logging.POASystemException;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/14 22:34
 * @Blog: ideashin.com
 */
public class PositionDaoImpl implements PositionDao {

    @Override
    public Boolean insert(Position position) {
        String sql = "INSERT INTO Att_Position VALUES(?, ?)";
        return DBHelper.execUpdate(sql, null, position.getPositionName());
    }

    @Override
    public Boolean update(Position position) {
        String sql = "UPDATE Att_Position SET PositionName = ? WHERE PositionID = ?";
        return DBHelper.execUpdate(sql, position.getPositionName(), position.getPositionID());
    }

    @Override
    public List selectAll() {
        String sql = "SELECT * FROM Att_Position";
        return DBHelper.execQuery(sql, Position.class);
    }

    @Override
    public Boolean deleteOne(int positionID) {
        String sql = "DELETE FROM Att_Position WHERE PositionID = ?";
        return DBHelper.execUpdate(sql, positionID);
    }
}
