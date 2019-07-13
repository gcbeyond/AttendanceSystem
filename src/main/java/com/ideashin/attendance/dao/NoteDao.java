package com.ideashin.attendance.dao;

import com.ideashin.attendance.entity.Note;

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
    void insert(Note note);

    /**
     * 更新
     * @param note
     */
    void update(Note note);

    /**
     * 查询所有
     */
    List<Note> selectAll();

    /**
     * 查询单条
     */
    Note selectOne();

    /**
     * 删除单条
     * @param noteID
     * @return
     */
    Boolean deleteOne(int noteID);
}
