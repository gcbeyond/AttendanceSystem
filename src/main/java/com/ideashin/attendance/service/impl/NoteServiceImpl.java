package com.ideashin.attendance.service.impl;

import com.ideashin.attendance.dao.NoteDao;
import com.ideashin.attendance.dao.impl.NoteDaoImpl;
import com.ideashin.attendance.entity.Note;
import com.ideashin.attendance.service.NoteService;

import java.util.Date;
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
    public List<Note> findAll(Integer page, Integer rows) {
        int offset = (page - 1) * rows;
        return noteDao.selectAll(offset, rows);
    }

    @Override
    public List findSome(Integer noteTypeSearch, Integer deptSelect, String empSearch, Date dateSearch) {
        empSearch = "%" + empSearch + "%";
        return noteDao.selectSome(noteTypeSearch, deptSelect, empSearch, dateSearch);
    }

    @Override
    public Boolean removeOne(Integer noteID) {
        return noteDao.deleteOne(noteID);
    }

    @Override
    public List findNoteToAttendance(Integer employeeID, Date attendanceDate) {
        return noteDao.selectNoteToAttendance(employeeID, attendanceDate);
    }

    @Override
    public int getCount() {
        return noteDao.getCount();
    }
}
