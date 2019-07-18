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
    List selectAll(Integer offset, Integer rows);

    /**
     * 条件查询
     * @param noteTypeSearch
     * @param deptSelect
     * @param empSearch
     * @param dateSearch
     * @return
     */
    List selectSome(Integer noteTypeSearch, Integer deptSelect, String empSearch, Date dateSearch);

    /**
     * 删除单条
     * @param noteID
     * @return
     */
    Boolean deleteOne(Integer noteID);

    /**
     * 查询条数
     * @return
     */
    int getCount();
}
