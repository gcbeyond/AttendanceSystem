package com.ideashin.attendance.dao.impl;

import com.ideashin.attendance.dao.NoteDao;
import com.ideashin.attendance.entity.Note;
import com.ideashin.attendance.util.DBHelper;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/7/12 18:11
 * @Blog: ideashin.com
 */
public class NoteDaoImpl implements NoteDao {
    @Override
    public Boolean insert(Note note) {
        String sql = "INSERT INTO Att_Note" +
                "VALUES(?, ? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                null,
                note.getDepartmentID(),
                note.getEmployeeID(),
                note.getNoteTypeID(),
                note .getCause(),
                note.getFillInTime(),
                note.getDirectorSign(),
                note.getAdministrationSign(),
                note.getPresidentSign(),
                note.getStartDate(),
                note.getStartTime(),
                note.getEndDate(),
                note.getEndTime(),
                note.getAdminID(),
                note.getNoteMemo(),
                note.getOperatorID(),
                1
        );
    }

    @Override
    public Boolean update(Note note) {
        String sql = "UPDATE SET Att_Note" +
                "VALUES(?, ? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DBHelper.execUpdate(sql,
                note.getNoteID(),
                note.getDepartmentID(),
                note.getEmployeeID(),
                note.getNoteTypeID(),
                note .getCause(),
                note.getFillInTime(),
                note.getDirectorSign(),
                note.getAdministrationSign(),
                note.getPresidentSign(),
                note.getStartDate(),
                note.getStartTime(),
                note.getEndDate(),
                note.getEndTime(),
                note.getAdminID(),
                note.getNoteMemo(),
                note.getOperatorID(),
                1
        );
    }

    @Override
    public List selectAll() {
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
                "                              ON Att_Note.NoteTypeID = t.TypeID";

        return DBHelper.execQuery(sql, Note.class);
    }

    @Override
    public List selectSome(int noteTypeSearch, String deptSelect, String empSearch, Date dateSearch) {
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
                "                              ON Att_Note.NoteTypeID = t.TypeID\n"+
                "WHERE\n" +
                "   Att_Employee.EmployeeName like ?";

        //全为空 1
        //note不空 其他空  2
        //note不空 dept不空 datek空 3
        ///note不空 dept空 date不空 4
        //note 空 dept不空 data空 5
        //note 空 dept不空 data不空 6
        //note空 dapt空  data不空 7
        //全不空 8
        if (noteTypeSearch == 0 && deptSelect.equals("全部") && dateSearch == null) {  //1
            return DBHelper.execQuery(sql, Note.class,
                    empSearch
            );
        }else if (noteTypeSearch != 0 && deptSelect.equals("全部") && dateSearch == null) {  //2
            sql = sql + "  AND Att_Note.NoteTypeID = ? ";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    noteTypeSearch
            );
        }else if (noteTypeSearch != 0 && !deptSelect.equals("全部") && dateSearch == null) {    //3
            sql = sql + " AND Att_Note.NoteTypeID = ? AND Att_Note.DepartmentID = ?";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    noteTypeSearch,
                    deptSelect
            );
        }else if (noteTypeSearch != 0 && deptSelect.equals("全部") && dateSearch != null) { //4
            sql = sql + " AND Att_Note.NoteTypeID = ? AND ( Att_Note.EndDate > ? AND Att_Note.StartDate < ?)";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    noteTypeSearch,
                    new java.sql.Date(dateSearch.getTime()),
                    new java.sql.Date(dateSearch.getTime())
            );
        }else if (noteTypeSearch == 0 && !deptSelect.equals("全部") && dateSearch == null) { //5
            sql = sql + " AND Att_Employee.EmployeeName = ?";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    deptSelect
            );
        }else if (noteTypeSearch == 0 && !deptSelect.equals("全部") && dateSearch != null) {  //6
            sql = sql + " AND Att_Employee.EmployeeName = ? AND ( Att_Note.EndDate > ? AND Att_Note.StartDate < ?)";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    deptSelect,
                    new java.sql.Date(dateSearch.getTime()),
                    new java.sql.Date(dateSearch.getTime())
            );
        }else if (noteTypeSearch == 0 && deptSelect.equals("全部") && dateSearch != null) {   //7
            sql = sql + "AND ( Att_Note.EndDate > ? AND Att_Note.StartDate < ?)";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    new java.sql.Date(dateSearch.getTime()),
                    new java.sql.Date(dateSearch.getTime())
            );
        }else {
            sql = sql + " AND Att_Note.NoteTypeID = ? AND Att_Employee.EmployeeName = ? AND ( Att_Note.EndDate > ? AND Att_Note.StartDate < ?)";
            return DBHelper.execQuery(sql, Note.class,
                    empSearch,
                    noteTypeSearch,
                    deptSelect,
                    new java.sql.Date(dateSearch.getTime()),
                    new java.sql.Date(dateSearch.getTime())
            );
        }
    }

    @Override
    public Boolean deleteOne(int noteID) {
        String sql = "DELETE FROM Att_Note WHERE NoteID = ?";
        Boolean  result = DBHelper.execUpdate(sql, noteID);
        return result;
    }

}
