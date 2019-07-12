package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Note;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/12 18:45
 * @Blog: ideashin.com
 */
public interface NoteService {

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
     * 查询单个
     */
    Note selectOne();

}
