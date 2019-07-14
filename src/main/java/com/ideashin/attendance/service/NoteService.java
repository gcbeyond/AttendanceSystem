package com.ideashin.attendance.service;

import com.ideashin.attendance.entity.Note;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
    Boolean addOne(Note note);

    /**
     * 更新
     * @param note
     */
    Boolean editOne(Note note);

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
