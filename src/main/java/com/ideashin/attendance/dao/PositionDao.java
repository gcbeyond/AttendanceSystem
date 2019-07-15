package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Position;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/14 22:33
 * @Blog: ideashin.com
 */
public interface PositionDao {

    /**
     * 增加
     * @param position
     */
    Boolean insert(Position position);

    /**
     * 更新
     * @param position
     */
    Boolean update(Position position);

    /**
     * 查询所有
     */
    List selectAll();

    /**
     * 删除单条
     * @param positionID
     * @return
     */
    Boolean deleteOne(int positionID);

}
