package utilsBag;

import java.sql.*;
import java.util.*;

import org.apache.commons.dbutils.DbUtils;

public class DBUtils {
	static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
	static String driver = "com.mysql.jdbc.Driver";
	static String url = bundle.getString("url");
	static String user = bundle.getString("user");
	static String password = bundle.getString("password");

	public static void setUrl(String url) {
		DBUtils.url = url;
	}

	private DBUtils() {

	}

	// 在类加载时只执行一次的静态代码块
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 获取数据库连接对象 retrun 连接对象
	 */
	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, user, password);

	}

	public static void close(Connection con, Statement stm, ResultSet rs) {
		if (stm != null) {
			try {
				// 处理结果集
				// 释放资源
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
		DbUtils.closeQuietly(conn);
		DbUtils.closeQuietly(ps);
		DbUtils.closeQuietly(rs);
	}

}
