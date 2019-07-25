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
     * 增加职位
     * @param position
     */
    Boolean insert(Position position);

    /**
     * 更新职位
     * @param position
     */
    Boolean update(Position position);

    /**
     * 分页查询所有职位列表
     */
    List selectAll(int offset, int rows);

    /**
     * 删除单条
     * @param positionID
     * @return
     */
    Boolean deleteOne(int positionID);


    /**
     * 查询职位条数
     * @return
     */
    int getCount();
}
