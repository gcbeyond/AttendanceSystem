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
    void addOne(Note note);

    /**
     * 更新
     * @param note
     */
    void editOne(Note note);

    /**
     * 查询所有
     */
    List<Note> findAllNotes();

    /**
     * 查询单条
     */
    Note findOne();

    /**
     * 删除单条
     * @param noteID
     * @return
     */
    Boolean removeOneNote(int noteID);
}
