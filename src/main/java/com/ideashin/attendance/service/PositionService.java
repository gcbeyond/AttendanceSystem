package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Position;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/14 22:57
 * @Blog: ideashin.com
 */
public interface PositionService {

    /**
     * 增加
     * @param position
     */
    Boolean addOne(Position position);

    /**
     * 更新
     * @param position
     */
    Boolean editOne(Position position);

    /**
     * 查询所有
     */
    List<Position> findAllPositions();

    /**
     * 删除单条
     * @param positionID
     * @return
     */
    Boolean removeOnePosition(int positionID);
}
