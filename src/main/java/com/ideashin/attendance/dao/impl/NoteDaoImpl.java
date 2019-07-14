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
    public List selectSome(int adminID, int noteTypeID, int employeeID, String employeeName, Date startDate, Date endDate) {
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
                "        Att_Note.AdminID = ?\n" +
                "  AND Att_Employee.EmployeeName like ?'\n" +
                "  AND ( ? > SYSDATE()\n" +
                "    AND ? < SYSDATE() )";
        if (noteTypeID == 0 && employeeID == 0) {
            return DBHelper.execQuery(sql, Note.class,
                    adminID,
                    employeeID,
                    new java.sql.Date(startDate.getTime()),
                    new java.sql.Date(endDate.getTime()));
        } else if (noteTypeID != 0 && employeeID == 0) {
            sql = sql + " AND Att_Note.NoteTypeID = ?";
            return DBHelper.execQuery(sql, Note.class,
                    adminID,
                    employeeID,
                    new java.sql.Date(startDate.getTime()),
                    new java.sql.Date(endDate.getTime()),
                    noteTypeID);
        } else if (noteTypeID == 0 && employeeID != 0) {
            sql = sql + " AND Att_EmployeeID = ?";
            return DBHelper.execQuery(sql, Note.class,
                    adminID,
                    employeeID,
                    new java.sql.Date(startDate.getTime()),
                    new java.sql.Date(endDate.getTime()),
                    employeeID);
        } else {
            sql = sql + " AND Att_Note.NoteTypeID = ? AND Att_EmployeeID = ?";
            return DBHelper.execQuery(sql, Note.class,
                    adminID,
                    employeeID,
                    new java.sql.Date(startDate.getTime()),
                    new java.sql.Date(endDate.getTime()),
                    noteTypeID,
                    employeeID);
        }
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
