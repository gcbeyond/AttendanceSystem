package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Note;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/12 16:04
 * @Blog: ideashin.com
 */
public interface NoteDao {

    /**
     * 增加
     * @param note
     */
    Boolean insert(Note note);

    /**
     * 更新
     * @param note
     */
    Boolean update(Note note);

    /**
     * 查询所有
     */
    List selectAll();

    /**
     * 条件查询
     */
    List selectSome(int adminID, int noteTypeID, int employeeID, String employeeName, Date startDate, Date endDate);

    /**
     * 删除单条
     * @param noteID
     * @return
     */
    Boolean deleteOne(int noteID);
}
