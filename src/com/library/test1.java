package com.library;

//import java.sql.*;
//import java.util.Scanner;
//
//import DBUtil.DBUtils;
//
//public class test1 {
//	static Connection conn = null;
//	static PreparedStatement ps = null;
//	static ResultSet rs = null;
//	
//	// 通用的增删改操作
//			public static int update(String sql, Object... args) {// sql中占位符的个数与可变形参的长度相同！
//				try {
//					// 1.获取数据库的连接
//					conn = DBUtils.getConnection();
//					// 2.预编译sql语句，返回PreparedStatement的实例
//					ps = conn.prepareStatement(sql);
//					// 3.填充占位符
//					for (int i = 0; i < args.length; i++) {
//						ps.setObject(i + 1, args[i]);// 小心参数声明错误！！
//					}
//					// 4.执行
//					/*
//					 * ps.execute():
//					 * 如果执行的是查询操作，有返回结果，则此方法返回true;
//					 * 如果执行的是增、删、改操作，没有返回结果，则此方法返回false.
//					 */
//					//方式一：
////					return ps.execute();
//					//方式二：
//					return ps.executeUpdate();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					// 5.资源的关闭
//					DBUtils.close(conn, ps, rs);
//
//				}
//				return 0;
//			}
//			
//			
//	
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		// TODO Auto-generated method stub
//		System.out.println("名字：");
//		String name = sc.nextLine();
//		System.out.println("类型：");
//		String type=sc.nextLine();
//		System.out.println("id:");
//		String id = sc.nextLine();
//		
//		System.out.println("密码");
//		String pwd=sc.nextLine();
//		System.out.println("关于:");
//		String abt=sc.nextLine();
//		String sql ="insert into userinfo(user_Name,user_type,user_id,user_password,user_about) values(?,?,?,?,?)";
//		int insertCount = update(sql,name,type,id,pwd,abt);
//		if(insertCount > 0){
//			System.out.println("添加成功");
//		}else{
//			System.out.println("添加失败");
//		}
//	}
//
//}

import java.sql.*;
import java.util.*;

import utilsBag.DBUtils;


/*
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1/bookcollector
user=root
password=001124
*/



public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null; //查询结果集
		try {
			//注册驱动
			Class.forName(driver);
			
			// 获取连接
			con = DriverManager.getConnection(url,user,password);
			
			//获取数据库对象
			stm = con.createStatement();
			
			//执行SQL语句	
			String sql = "insert into books_info(book_id,book_name,type,author,date) "
					+ "value('004','jdbc引入','JAVA书','JAVA编程者','2015-10-20')";
			
			String sql1 = "delete from books_info where book_id='004' ";
			
			int count = stm.executeUpdate(sql);
//			stm.executeQuery(参数) 查询用的 返回一个结果查询集
			System.out.println(count ==1 ? "更新信息表成功":"更新信息表失败");
			//sql.equals(null)
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("有点问题,没运行成功");
			e.printStackTrace();
		}finally {
			DBUtils.close(con, stm, rs);
		}
	}

}
