package com.ideashin.attendance.dao;

import java.sql.*;
import java.util.List;


import com.ideashin.attendance.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BaseDao<T> {
	
	// 数据库访问操作
	/**
	 * 返回单个对象
	 * @param <T>
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            如果没有参数就设为 Object[] params={}
	 * @return
	 */
	public <T> T get(String sql, Class<T> clazz, Object[] params) {
		T obj = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(conn, sql, new BeanHandler<T>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * 返回单个对象 事务操作
	 * @param <T>
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            如果没有参数就设为 Object[] params={}
	 * @return
	 */
	public <T> T get(Connection conn,String sql, Class<T> clazz, Object[] params) throws SQLException {
		     T obj = null;
			QueryRunner qRunner = new QueryRunner();
			obj = qRunner.query(conn, sql, new BeanHandler<T>(clazz), params);		
		    return obj;
	}

	/**
	 * 返回多个对象
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 *            如果没有参数就设为 Object[] params={}
	 * @return
	 */
	public <T> List<T> query(String sql, Class<T> clazz, Object[] params) {
		List beans = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			System.out.println(conn+"----------------");
			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, sql, new BeanListHandler<T>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	/**
	 * 返回增删改是否成功
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql, Object[] params) {
		Connection conn = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(conn, sql, params);
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
    /**
     * 需要进行事务操作时，在同一事务管理下操作
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
	public boolean update(Connection conn, String sql, Object... params) throws SQLException {
		boolean flag = false;
		QueryRunner qRunner = new QueryRunner();
		int i = qRunner.update(conn, sql, params);
		if (i > 0) {
			flag = true;
		}
		return flag;
	}

	/***
	 * 批量操作，需要用到事务
	 * 
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean batchUpdate(Connection conn, String sql, Object[][] params) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		int result = 0;
		boolean flag = false;
		result = qRunner.batch(conn, sql, params).length;
		if (result > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 返回统计单值,
	 * @param sql
	 * @param params
	 * @return
	 */
	public long getCount(String sql,Object...params){
		long count =0L;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			QueryRunner qRunner = new QueryRunner();
			count  = (Long) qRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 返回主键,通常是执行insert语句时返回当前的主键值
	 * @param sql
	 * @param params
	 * @return
	 */
	 public Long getCurrentKey(String sql,Object... params){
	    	Connection conn = null;
			Long key = 0l;
			try {
				conn = JDBCUtils.getConnection();
				QueryRunner qRunner = new QueryRunner();
				 key = (Long) qRunner.insert(conn,sql, new ScalarHandler(1), params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return key;
	    }
}