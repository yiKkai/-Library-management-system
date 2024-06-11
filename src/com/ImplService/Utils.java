package com.ImplService;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import utilsBag.DBUtils;

public class Utils {

	// 通用的增删改操作
	public static int update(String sql, Object... args) {// sql中占位符的个数与可变形参的长度相同！
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1.获取数据库的连接
			conn = DBUtils.getConnection();
			// 2.预编译sql语句，返回PreparedStatement的实例
			ps = conn.prepareStatement(sql);
			// 3.填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);// 小心参数声明错误！！
			}
			// 4.执行
			/*
			 * ps.execute(): 如果执行的是查询操作，有返回结果，则此方法返回true; 如果执行的是增、删、改操作，没有返回结果，则此方法返回false.
			 */
			// 方式一：
//					return ps.execute();
			// 方式二：
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.资源的关闭
			DBUtils.closeResource(conn, ps, rs);

		}
		return 0;
	}

	public static <T> T getInstance(Class<T> clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();

			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			rs = ps.executeQuery();
			// 获取结果集的元数据 :ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过ResultSetMetaData获取结果集中的列数
			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {
				T t = clazz.newInstance();
				// 处理结果集一行数据中的每一个列
				for (int i = 0; i < columnCount; i++) {
					// 获取列值
					Object columValue = rs.getObject(i + 1);

					// 获取每个列的列名
					// String columnName = rsmd.getColumnName(i + 1);
					String columnLabel = rsmd.getColumnLabel(i + 1);

					// 给t对象指定的columnName属性，赋值为columValue：通过反射
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columValue);
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResource(conn, ps, rs);

		}

		return null;
	}
	
	public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object... args){
		
		QueryRunner runner = new QueryRunner();
		try {
			conn = DBUtils.getConnection();
			MapListHandler handler = new MapListHandler();
			List<Map<String, Object>> list = runner.query(conn, sql, handler, 23);
			return (List<T>) list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn, null, null);
		}
		return null;
	}
	
	//用于查询特殊值的通用的方法
		public <E> E getValue(Connection conn,String sql,Object...args){
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql);
				for(int i = 0;i < args.length;i++){
					ps.setObject(i + 1, args[i]);
					
				}
				
				rs = ps.executeQuery();
				if(rs.next()){
					return (E) rs.getObject(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtils.closeResource(conn, ps, rs);
				
			}
			return null;
			
		}
		
		public void clearAllText() {
			
		}

}
