CREATE DATABASE KQSYS;
USE KQSYS;

-- 用户表:多对多关系
CREATE TABLE Att_Admin(
    AdminID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    AdminAccount NVARCHAR(50) NOT NULL COMMENT '用户帐号',
    AdminPwd NVARCHAR(50) NOT NULL COMMENT '密码',
    AdminState CHAR(1) NOT NULL COMMENT '是否启用此帐号：0禁用，1启用',
    AdminRight CHAR(1) NOT NULL COMMENT '是否管理员：1管理用，2考勤员',
    AdminName NVARCHAR(50) NOT NULL COMMENT '用户名称'
);

-- 用户权限表
CREATE TABLE Att_AdminPopedom(
    PopedomID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    DepartmentID INT NOT NULL COMMENT '外键（部门） 部门编号',
    AdminID Int NOT NULL COMMENT '外键（用户） 用户编号'
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
create table Att_position(
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
    EmployeState CHAR(1) NOT NULL COMMENT '员工状态（1： 正常； 0：停用；）',
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
    AttendanceType INT NOT NULL COMMENT '外键（考勤类 型表） 考勤类型',
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
CREATE TABLE Att_Notes(
    NoteID INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    DepartmentID INT NOT NULL COMMENT '外键（部门） 部门编号',
    EmployeeID INT NOT NULL COMMENT '外键（员工） 申请人',
    NoteTypeID INT NOT NULL COMMENT '外键（考勤状态） 单据类型',
    Cause NVARCHAR(1000) NOT NULL COMMENT '事由',
    FillInTime DATETIME NOT NULL COMMENT '填表日期',
    DirectorSign NVARCHAR(200) COMMENT '空 主管经理签名意 见',
    AdministrationSign NVARCHAR(200) COMMENT '空 行政经理签名意见',
    PresidentSign NVARCHAR(200) COMMENT '空  总裁签名意见',
    StartDate DATETIME NOT NULL COMMENT '起始日期',
    StartTime NVARCHAR(50) COMMENT '空  起始时间',
    EndDate DATETIME NOT NULL  COMMENT '结束日期',
    EndTime NVARCHAR(50) COMMENT '空  结束时间',
    Vehicle NVARCHAR(50) COMMENT '空  交通工具',
    ProjectName NVARCHAR(200) COMMENT '空  项目名称',
    AdminID INT NOT NULL COMMENT '外键（用户） 录入人',
    NoteMemo NVARCHAR(500) COMMENT '空  备注',
    OperatorID INT NOT NULL COMMENT '外键（员工） 代理人编号',
    IsVerify CHAR(1) NOT NULL COMMENT '是否审核， 0：否； 1：是 默认 1，审批才可倒休'
);



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