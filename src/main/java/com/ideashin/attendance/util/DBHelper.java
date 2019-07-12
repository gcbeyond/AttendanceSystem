package com.ideashin.attendance.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBHelper {

	/**
	 * 查询所有列表
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public List execQuery(String sql, Class clazz, Object... params) {
		Connection conn = null;
		List list = null;
		QueryRunner runner = new QueryRunner();

		try {
			conn = JDBCUtils.getConnection();
			list = (List)runner.query(conn, sql, new BeanListHandler<>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn);
		}
		return list;
	}

	/**
	 * 查询单个数据
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public Object execQueryOne(String sql, Class clazz, Object... params) {
		Connection conn = null;
		Object obj = null;
		QueryRunner runner = new QueryRunner();

		try {
			conn = JDBCUtils.getConnection();
			obj = runner.query(conn, sql, new BeanHandler<>(clazz), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn);
		}
		return obj;
	}

	/**
	 * 增删改数据
	 * @param sql
	 * @param params
	 */
	public boolean execUpdate(String sql, Object... params) {
		boolean result = true;
		Connection conn = null;
		QueryRunner runner = new QueryRunner();

		try {
			conn = JDBCUtils.getConnection();
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}finally {
			JDBCUtils.close(conn);
		}
		return result;
	}

	/**
	 * 增删改数据:事务
	 * @param sql
	 * @param params
	 */
	public void execUpdateRou(Connection conn, String sql, Object... params) {
		QueryRunner runner = new QueryRunner();

		try {
			conn = JDBCUtils.getConnection();
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询是否存在id
	 * @param sql
	 * @return
	 */
	public int getCount(String sql) {
		int empCount = 0;
		Connection conn = null;
		QueryRunner runner = new QueryRunner();

		try {
			conn = JDBCUtils.getConnection();
			empCount = runner.query(conn,sql, new ScalarHandler<Long>()).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empCount;
	}
}
