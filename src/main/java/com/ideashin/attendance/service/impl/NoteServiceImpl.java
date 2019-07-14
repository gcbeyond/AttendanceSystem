package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.NoteDao;
import com.ideashin.attendance.dao.impl.NoteDaoImpl;
import com.ideashin.attendance.entity.Note;
import com.ideashin.attendance.service.NoteService;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/12 18:46
 * @Blog: ideashin.com
 */
public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;
    public NoteServiceImpl(){
        noteDao = new NoteDaoImpl();
    }

    @Override
    public Boolean addOne(Note note) {
        return noteDao.insert(note);
    }

    @Override
    public Boolean editOne(Note note) {
        return noteDao.update(note);
    }

    @Override
    public List<Note> findAllNotes() {
        return noteDao.selectAll();
    }

    @Override
    public Note findOne() {
        return null;
    }

    @Override
    public Boolean removeOneNote(int noteID) {
        return noteDao.deleteOne(noteID);
    }
}
