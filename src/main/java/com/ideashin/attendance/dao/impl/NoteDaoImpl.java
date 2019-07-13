package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.NoteDao;
import com.ideashin.attendance.entity.Note;
import com.ideashin.attendance.util.DBHelper;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/12 18:11
 * @Blog: ideashin.com
 */
public class NoteDaoImpl implements NoteDao {
    @Override
    public void insert(Note note) {

    }

    @Override
    public void update(Note note) {

    }

    @Override
    public List<Note> selectAll() {
        String sql = "SELECT\n" +
                "    Att_Note.NoteID,\n" +
                "    Att_Note.FillInTime,\n" +
                "    Att_Employee.CardNumber,\n" +
                "    Att_Note.EmployeeID,\n" +
                "    Att_Employee.EmployeeName,\n" +
                "    d.DepartmentID  twoDID,\n" +
                "    d.DepartmentName twoDName,\n" +
                "    d.ParentID oneDID,\n" +
                "    (SELECT DepartmentName FROM Att_Department d1 WHERE d.ParentID = d1.DepartmentID) oneDName,\n" +
                "    Att_Note.NoteTypeID,\n" +
                "    t.TypeName,\n" +
                "    Att_Note.StartDate,\n" +
                "    Att_Note.StartTime,\n" +
                "    Att_Note.EndDate,\n" +
                "    Att_Note.EndTime,\n" +
                "    Att_note.OperatorID,\n" +
                "    Att_note.Cause,\n" +
                "    Att_note.DirectorSign,\n" +
                "    Att_note.AdministrationSign,\n" +
                "    Att_note.PresidentSign,\n" +
                "    (SELECT e1.CardNumber FROM Att_Employee e1 WHERE \tAtt_Note.OperatorID = e1.EmployeeID) operatorCardNum,\n" +
                "    (SELECT e1.EmployeeName FROM Att_Employee e1 WHERE \tAtt_Note.OperatorID = e1.EmployeeID) operatorName\n" +
                "FROM Att_Note inner join Att_Employee\n" +
                "                         ON Att_Note.EmployeeID = Att_Employee.EmployeeID\n" +
                "              LEFT OUTER JOIN Att_Department d\n" +
                "                              ON Att_Employee.DepartmentID = d.DepartmentID\n" +
                "              LEFT OUTER JOIN Att_AttendanceType t\n" +
                "                              ON Att_Note.NoteTypeID = t.TypeId";

        return DBHelper.execQuery(sql, Note.class);
    }

    @Override
    public Note selectOne() {
        return null;
    }

    @Override
    public Boolean deleteOne(int noteID) {
        String sql = "DELETE FROM Att_Note WHERE NoteID = ?";
        Boolean  result = DBHelper.execUpdate(sql, noteID);
        return result;
    }

    //测试代码-暂存
    public static void main(String[] args) {
//        List<Note> list = new NoteDaoImpl().selectAll();
//        for (Note note : list) {
//            System.out.println( note.toString() );
//        }
        System.out.println(new NoteDaoImpl().deleteOne(12));
    }
}
