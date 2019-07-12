package com.ideashin.attendance.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

	/**通过数据源获得连接对象
	 * 多用在WEB项目中
	 */
	/** 加载c3p0-config.xml配置文件，获得连接池 */
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/** 解决并发问题 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();



	public static Connection getConnection() throws SQLException{
		/** 首先从ThreadLocal中获得连接对象 */
		Connection conn = tl.get();
		if(conn == null||conn.isClosed()){
			conn = dataSource.getConnection();
			tl.set(conn);
		}
		return conn;
	}


	/**
	 * 开始事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException{

		Connection conn = getConnection();
		conn.setAutoCommit(false);

		conn.rollback();
	}

	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException{
		Connection conn = getConnection();
		conn.rollback();
	}

	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void commit() throws SQLException{
		Connection conn = getConnection();
		conn.commit();
		closeConnection();
	}

	/**
	 * 关闭Connection,并移除线程中的连接
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException{
		close(getConnection());
		tl.remove();
	}

	public static void close(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stm){
		try {
			if(stm != null){
				stm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}