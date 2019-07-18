CREATE DATABASE KQSYS;
USE KQSYS;

-- 用户表:多对多关系
CREATE TABLE Att_Admin(
                          AdminID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                          AdminAccount NVARCHAR(50) NOT NULL COMMENT '用户帐号',
                          AdminPwd NVARCHAR(50) NOT NULL COMMENT '密码',
                          AdminState CHAR(1) NOT NULL COMMENT '是否启用此帐号：0禁用，1启用',
                          AdminRight CHAR(1) NOT NULL COMMENT '是否管理员：1管理员，2考勤员',
                          AdminName NVARCHAR(50) NOT NULL COMMENT '用户名称'
);

-- 用户权限表
CREATE TABLE Att_AdminPopedom(
                                 PopedomID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                                 DepartmentID INT NOT NULL COMMENT '外键（部门） 部门编号',
                                 AdminID INT NOT NULL COMMENT '外键（用户） 用户编号'
);

-- 部门表
CREATE TABLE Att_Department(
                               DepartmentID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                               DepartmentName NVARCHAR(100) NOT NULL COMMENT '部门名称',
                               StartTimeAM CHAR(5) NOT NULL COMMENT '上午上班时间',
                               EndTimeAM CHAR(5) NOT NULL COMMENT '上午下班时间',
                               StartTimePM CHAR(5) NOT NULL COMMENT '下午上班时间',
                               EndTimePM CHAR(5) NOT NULL COMMENT '下午下班时间',
                               ParentID INT NOT NULL COMMENT '父级部门编号， 一级部门为 0'
);

-- 职务表
create table Att_Position(
                             PositionID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                             PositionName VARCHAR(50) NOT NULL COMMENT '职务名称'
);

-- 员工表
CREATE TABLE Att_Employee(
                             EmployeeID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                             EmployeeName NVARCHAR(100) NOT NULL COMMENT '员工名称',
                             EmployeeGender CHAR(1) NOT NULL COMMENT '员工性别（0： 女；1：男）',
                             PositionID INT NOT NULL COMMENT '外键（职务） 职务编号',
                             DepartmentID INT NOT NULL COMMENT '外键（部门） 部门编号',
                             CardNumber NVARCHAR(50) NOT NULL COMMENT '员工卡号',
                             EmployeeState CHAR(1) NOT NULL COMMENT '员工状态（1： 正常； 0：停用；）',
                             EmployeeMemo NVARCHAR(200) COMMENT '空  备注'
);

-- 考勤记录表
CREATE TABLE Att_AttendanceRecord(
                                     AttendanceID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                                     EmployeeID INT NOT NULL COMMENT '外键（员工） 员工编号',
                                     CardNumber NVARCHAR(50) NOT NULL COMMENT '员工卡号',
                                     AttendanceDate DATETIME NOT NULL COMMENT '考勤日期',
                                     AttendanceTime CHAR(5) COMMENT '空 考勤时间',
                                     AttendanceFlag CHAR(1) NOT NULL COMMENT '考勤时段（1 代表上午，2 代表下午）',
                                     AttendanceType INT NOT NULL COMMENT '外键（考勤类型表） 考勤类型',
                                     AttendanceMemo NVARCHAR(200) COMMENT '空 备注',
                                     AdminID INT NOT NULL COMMENT '外键（用户） 考勤员编号',
                                     TempDepartmentId INT NOT NULL COMMENT '外键（部门） 部门编号',
                                     NoteId INT NOT NULL COMMENT '外键（单据） 单据编号'

);

-- 考勤状态表:这个表没有程序
CREATE TABLE Att_AttendanceType(
                                   TypeId INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                                   TypeName NVARCHAR(20) NOT NULL COMMENT '状态名称',
                                   TypeCategory CHAR(1) NOT NULL COMMENT '是否为请假 类型（1 代 表是，0 代 表不是）'
    -- 固定
    -- 1 出勤 0
    -- 2 公休 0
    -- 3 迟到 0
    -- 4 旷工 0
    -- 5 外出 0
    -- 6 出差 0
    -- 7 加班 0
    -- 8 倒休 0
    -- 9 事假 1
    -- 10 病假 1
    -- 11 婚假 1
    -- 12 丧假 1
    -- 13 产假 1
);

-- 请假单据表
CREATE TABLE Att_Note(
                         NoteID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
                         DepartmentID INT NOT NULL COMMENT '外键（部门） 部门编号',
                         EmployeeID INT NOT NULL COMMENT '外键（员工） 申请人',
                         NoteTypeID INT NOT NULL COMMENT '外键（考勤状态） 单据类型',
                         Cause NVARCHAR(1000) NOT NULL COMMENT '事由',
                         FillInTime DATETIME NOT NULL COMMENT '填表时间',
                         DirectorSign NVARCHAR(200) COMMENT '空 主管经理签名意 见',
                         AdministrationSign NVARCHAR(200) COMMENT '空 行政经理签名意见',
                         PresidentSign NVARCHAR(200) COMMENT '空  总裁签名意见',
                         StartDate DATETIME NOT NULL COMMENT '起始日期',
                         StartTime NVARCHAR(50) COMMENT '空  起始时间',
                         EndDate DATETIME NOT NULL  COMMENT '结束日期',
                         EndTime NVARCHAR(50) COMMENT '空  结束时间',
                         AdminID INT NOT NULL COMMENT '外键（用户） 录入人',
                         NoteMemo NVARCHAR(500) COMMENT '空  备注',
                         OperatorID INT COMMENT '外键（员工） 代理人编号',
                         IsVerify CHAR(1) NOT NULL COMMENT '是否审核， 0：否； 1：是 默认 1，审批才可倒休'
);

-- 添加考勤状态
INSERT INTO Att_AttendanceType VALUES (NULL, '出勤', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '公休', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '迟到', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '旷工', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '外出', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '出差', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '加班', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '倒休', 0);
INSERT INTO Att_AttendanceType VALUES (NULL, '事假', 1);
INSERT INTO Att_AttendanceType VALUES (NULL, '病假', 1);
INSERT INTO Att_AttendanceType VALUES (NULL, '婚假', 1);
INSERT INTO Att_AttendanceType VALUES (NULL, '丧假', 1);
INSERT INTO Att_AttendanceType VALUES (NULL, '产假', 1);

-- 添加部门
INSERT INTO Att_Department VALUES (null, '销售部', '9:00', '11:30', '15:30', '17:30', 0);
INSERT INTO Att_Department VALUES (null, '海外销售部', '9:00', '11:30', '15:30', '17:30', 1);
INSERT INTO Att_Department VALUES (null, '国内销售部', '9:00', '11:30', '15:30', '17:30', 1);
INSERT INTO Att_Department VALUES (null, '技术部', '9:00', '11:30', '15:30', '17:30', 0);
INSERT INTO Att_Department VALUES (null, '前端部', '9:00', '11:30', '15:30', '17:30', 4);
INSERT INTO Att_Department VALUES (null, '后端部', '9:00', '11:30', '15:30', '17:30', 4);
INSERT INTO Att_Department VALUES (null, '中间件部', '9:00', '11:30', '15:30', '17:30', 4);

-- 添加职务
INSERT INTO Att_Position VALUES (NULL, 'Java工程师');
INSERT INTO Att_Position VALUES (NULL, '前端工程师');
INSERT INTO Att_Position VALUES (NULL, '架构师');
INSERT INTO Att_Position VALUES (NULL, '销售');
INSERT INTO Att_Position VALUES (NULL, '项目经理');

-- 添加员工
INSERT INTO Att_Employee VALUES (NULL, '小明', '1', 1, 6,53413, 1, '小伙很强');
INSERT INTO Att_Employee VALUES (NULL, '小天', '0', 2, 2,53414, 1, '红星');
INSERT INTO Att_Employee VALUES (NULL, '小黑', '1', 3, 3,53415, 1, '不错');
INSERT INTO Att_Employee VALUES (NULL, '小夜', '0', 4, 7,53416, 1, '挺好');
INSERT INTO Att_Employee VALUES (NULL, '小白', '1', 5, 5,53417, 1, '一般');

-- 添加用户
INSERT INTO Att_Admin VALUES (NULL, 'admin1', '1234', '1', '1', '强哥');
INSERT INTO Att_Admin VALUES (NULL, 'admin2', '1234', '1', '2', '明哥');
INSERT INTO Att_Admin VALUES (NULL, 'admin3', '1234', '1', '2', '黑哥');

-- 请假单
INSERT INTO Att_Note VALUES (NULL,'6', '1', '13', '老婆快生了', '2019-01-11', '没意见', '同意', '可以', '2019-01-15', '上午', '2019-12-19', '下午', '2', '无', NULL, '1');
INSERT INTO Att_Note VALUES (NULL,'2', '2', '9', '家中有事', '2019-01-09', '没意见', '同意', '可以', '2019-02-15', '下午', '2019-12-19', '上午', '2', '无', '4', '1');
INSERT INTO Att_Note VALUES (NULL,'3', '3', '10', '感冒了', '2019-01-11', '没意见', '同意', '可以', '2019-02-15', '下午', '2019-12-19', '上午', '3', '无', NULL, '0');
INSERT INTO Att_Note VALUES (NULL,'7', '4', '11', '马上结婚了', '2019-01-09', '没意见', '同意', '可以', '2019-02-15', '上午', '2019-12-19', '下午', '2', '无', NULL, '1');
INSERT INTO Att_Note VALUES (NULL,'5', '5', '10', '生病了', '2019-01-01', '没意见', '同意', '可以', '2019-03-15', '上午', '2019-12-19', '下午', '3', '无', '1', '1');



SELECT Att_Note.NoteID,
       Att_Note.FillInTime,
       Att_Employee.CardNumber,
       Att_Note.EmployeeID,
       Att_Employee.EmployeeName,
       d.DepartmentID                                                                          twoDID,
       d.DepartmentName                                                                        twoDName,
       d.ParentID                                                                              oneDID,
       (SELECT DepartmentName FROM Att_Department d1 WHERE d.ParentID = d1.DepartmentID)       oneDName,
       t.TypeID,
       t.TypeName,
       Att_Note.StartDate,
       Att_Note.StartTime,
       Att_Note.EndDate,
       Att_Note.EndTime,
       Att_note.OperatorID,
       (SELECT e1.EmployeeName FROM Att_Employee e1 WHERE Att_Note.OperatorID = e1.EmployeeID) operatorName
FROM Att_Note
         inner join Att_Employee
                    ON Att_Note.EmployeeID = Att_Employee.EmployeeID
         LEFT OUTER JOIN Att_Department d
                         ON Att_Employee.DepartmentID = d.DepartmentID
         LEFT OUTER JOIN Att_AttendanceType t
                         ON Att_Note.NoteTypeID = t.TypeId
WHERE (Att_Note.AdminID = ? OR ? IS NULL OR ? = '')
  AND (Att_Note.NoteTypeID = ? OR ? IS NULL OR ? = '')
  AND (d.DepartmentName = ? OR ? = '全部')
  AND (Att_Employee.EmployeeName LIKE ?)

#         Att_Note.AdminID = 2
#   AND Att_Note.NoteTypeID = 13
#   AND Att_Employee.EmployeeName like '%明%'
  AND (Att_Note.EndDate > SYSDATE()
    AND Att_Note.StartDate < SYSDATE());



SELECT
    ee.CardNumber,
    ee.EmployeeID,
    ee.EmployeeName,
    ee.DepartmentId,
    (SELECT DepartmentName FROM att_department dt WHERE dt.DepartmentId = ee.DepartmentID) DepartmentName,
    ar.AttendanceDate,
    ar.AttendanceTime,
    ar.AttendanceType,
    ar.AttendanceMemo,
    ar.AdminID,
    ar.NoteId

FROM
    att_employee ee LEFT JOIN att_attendancerecord ar ON ee.EmployeeID = ar.EmployeeID



