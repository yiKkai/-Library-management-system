package com.ImplService;

import com.DAO.BooksDAO;
import com.Interface.booksEditor;
import com.library.Book;
import com.users.borrowRecording;
import com.users.user;

import utilsBag.DBUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;


public class UsersDAOImpl {
	
	static String id;
	static String name;
	static String about;
	static String record;
	static String signDate;
	static String state;
	
	static String url ="jdbc:mysql://127.0.0.1/library?serverTimezone=UTC";
	
	
//	public static void editBooksByID(String id) {
//		//修改图书信息
//		date=(booksEditor.getDatepick().getText()).toString();
//		name= booksEditor.getNametextField().getText();
//		author = booksEditor.getAuthortextField().getText();
//		publisher = booksEditor.getPlacetextField().getText();
//		type = booksEditor.getTypetextField().getText();
//		about= booksEditor.getAboutTextArea().getText();
//		as= booksEditor.getAmounttextField().getText();
//		
//		if(!(as.equals(""))) {
//			amount = Integer.parseInt(as);
//		}else {
//			amount = 0;
//		}
//		
//		String sql = "update books_info set book_name=?,type=?,`author`=? , `date`=? ,`amount`=? ,"
//				+ "`about`=? ,`publisher`=?  where  book_id = ?";
//		Connection conn = null;
//		DBUtils.setUrl("jdbc:mysql://127.0.0.1/bookcollector");
//		try {
//			QueryRunner runner = new QueryRunner();
//			conn = DBUtils.getConnection();
//			runner.update(conn, sql,name,type,author,date,amount,about,publisher,id);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DbUtils.closeQuietly(conn);
//		}
//		
//	}
	
	
	
	public static List getUserList() {
		String sql ="SELECT `user_Name` userName ,`user_id` userId,`user_about` aboutMe,`record` userRecord,`sign_date` signDate ,`user_Sta` uState FROM userinfo WHERE `user_type` = \"读者\"";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanListHandler<user>  handler = new BeanListHandler<>(user.class);
			List<user> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	
	public static user getSearchedUserByNameOrID(String name,String ID,String password) { //读取用户
		String sql ="select `user_Name` userName,`user_type` userType, `user_id` userId,`user_password` upassword,`user_about` aboutMe,`sign_date` signDate,`record` userRecord,`owned_bk` uOwned, `user_Sta` uState,borrow_time brtimes from userinfo where user_password = ? and (user_Name= ? or user_id= ?)";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanHandler<user>  handler = new BeanHandler<>(user.class);
			user  uer = runner.query(conn, sql, handler,password,name,ID);
			System.out.println(uer);
			return uer;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	public static List<Object> getStaList() { //获取数据库中所有用户状态集合
		String sql ="SELECT  DISTINCT user_Sta FROM `userinfo`";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			ColumnListHandler  handler = new ColumnListHandler();
			List<Object> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	public static void borrowingBook(String record,String uID,String bkName,String borrower,String date) {
		String sql ="UPDATE `userinfo` SET `record`= CONCAT(`record`,\"\\n\",?) WHERE `user_id`= ?";
		String sql1="UPDATE `userinfo` SET `owned_bk`= CONCAT(`owned_bk`,\"\\n\", ?),`borrow_time`=`borrow_time`+1 WHERE `user_id`=?";
//		String sql2="UPDATE `userinfo` SET `getbor_Times` =`getbor_Times`+1 WHERE `user_id`=?";
		String borrChangeSql = "INSERT INTO borrowRecord(`borrower`,`borrowed_book`,`borrow_date`,`isReturn`,whole_record) VALUES(?,?,?,?,?)";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn,sql,record,uID);
			runner.update(conn,sql1,bkName,uID);
//			runner.update(conn,sql2,bkName,uID);
			runner.update(conn,borrChangeSql,borrower,bkName,date,"待归还",record);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	public static List getRecordByIDAname(String IDaName) {
		String sql ="SELECT `borrower` borrowerName, `borrowed_book` borrowedBookName, `borrow_date` borrowDate,  `isReturn` whetherBack FROM `borrowrecord` WHERE `borrower` =?";
		
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanListHandler<borrowRecording>  handler = new BeanListHandler<>(borrowRecording.class);
			List<borrowRecording> list = runner.query(conn, sql, handler,IDaName);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
	
	
	public static List getHistoryRecord() {
		String sql ="SELECT whole_record FROM borrowrecord";
		
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			ColumnListHandler handler = new ColumnListHandler();
			List list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
	
	//打钱，还书
	public static void backBorrowingBook(String book,String borrower,String id) {
		String sql ="UPDATE `borrowrecord` SET `isReturn`= ? WHERE (`borrower`= ? and `borrowed_book`=?)";
		String sqlB ="UPDATE `userinfo`\r\n" + 
				"SET `owned_bk`=REPLACE(`owned_bk`,?,'')\r\n" + 
				"WHERE `user_id`=?";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn,sql,"已归还",borrower,book);
			runner.update(conn,sqlB,book,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	//获取该读者所持有的书籍
//	public static List getOwnedBookListByID(String id) {
//		String sql = "SELECT `owned_bk` FROM `userinfo` WHERE `user_id` =?";
//		Connection conn = null;
//		DBUtils.setUrl(url);
//		try {
//			QueryRunner runner = new QueryRunner();
//			conn = DBUtils.getConnection();
//			ColumnListHandler  handler = new ColumnListHandler();
//			List<Object> list = runner.query(conn,handler,sql,id);
//			list.forEach(System.out::println);
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DbUtils.closeQuietly(conn);
//		}
//		return null;
//		
//	}
	
	
	public static List getSearcheduserListByName(String name) { //模糊查询获得结果集
		String sql ="SELECT `user_Name` userName,`user_type` userType, `user_id` userId,`user_password` upassword,`user_about` aboutMe,`sign_date` signDate,`record` userRecord, `user_Sta` uState FROM `userinfo` "
				+ "WHERE user_Name LIKE '%"+name+"%' && user_type = \"读者\" ";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanListHandler<user>  handler = new BeanListHandler<>(user.class);
			List<user> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	// 6/6改
	public static List getAllborrowDate(){ //获取所有借阅日期
		String sql ="SELECT COUNT(borrow_date) FROM `borrowrecord` GROUP BY `borrow_date` ORDER BY `borrow_date` ASC";
		String sql1="SELECT DISTINCT `borrow_date` FROM `borrowrecord` ORDER BY `borrow_date` ASC";
		Connection conn = null;
		DBUtils.setUrl(url);
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			ColumnListHandler  handler = new ColumnListHandler();
			List listCount = runner.query(conn, sql, handler);
			List list = runner.query(conn, sql1, handler);
			List all = new ArrayList();
			all.add(list);
			all.add(listCount);
			all.forEach(System.out::println);
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	// 6/6
		public static List getAllborrowTime(){ //获取所有读者借阅次数
			String sql ="SELECT borrow_time FROM `userinfo`  WHERE `user_type` = \"读者\"";
			String sql1="SELECT `user_Name` FROM `userinfo`  WHERE `user_type` = \"读者\"";
			Connection conn = null;
			DBUtils.setUrl(url);
			try {
				QueryRunner runner = new QueryRunner();
				conn = DBUtils.getConnection();
				ColumnListHandler  handler = new ColumnListHandler();
				List listCount = runner.query(conn, sql, handler);
				List list = runner.query(conn, sql1, handler);
				List all = new ArrayList();
				all.add(list);
				all.add(listCount);
				all.forEach(System.out::println);
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtils.closeQuietly(conn);
			}
			return null;
			
		}
	
		//6.6
		public static void clearAllborrowTime(){ //清空所有读者借阅次数
			String sql ="UPDATE `userinfo` SET `borrow_time`=0";
			
			Connection conn = null;
			DBUtils.setUrl(url);
			try {
				QueryRunner runner = new QueryRunner();
				conn = DBUtils.getConnection();
				runner.update(conn,sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtils.closeQuietly(conn);
			}
		}
		
		// 6/7
				public static List getAllborrowTime1(){ //获取所有读者借阅次数
					String sql ="SELECT `borrow_time` brtimes,`user_Name` userName \r\n" + 
							"FROM `userinfo`  WHERE `user_type` = \"读者\" ORDER BY `borrow_time` DESC";
					Connection conn = null;
					DBUtils.setUrl(url);
					try {
						QueryRunner runner = new QueryRunner();
						conn = DBUtils.getConnection();
						BeanListHandler<user> handler = new BeanListHandler(user.class);
						List list = runner.query(conn, sql, handler);
						list.forEach(System.out::println);
						return list;
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DbUtils.closeQuietly(conn);
					}
					return null;
					
				}
	
				// 6/11改
				public static List getAllborrowDate1(){ //获取所有借阅日期
					String sql ="SELECT COUNT(borrow_date),`borrow_date` FROM `borrowrecord` GROUP BY `borrow_date` "
							+ "ORDER BY `borrow_date` ASC ";
					
					Connection conn = null;
					DBUtils.setUrl(url);
					try {
						QueryRunner runner = new QueryRunner();
						conn = DBUtils.getConnection();
						BeanListHandler  handler = new BeanListHandler(Object.class);
						List list = (List) runner.query(conn, sql, handler);
//						List list = runner.query(conn, sql1, handler);
//						List all = new ArrayList();
//						all.add(list);
//						all.add(listCount);
						list.forEach(System.out::println);
						return list;
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						DbUtils.closeQuietly(conn);
					}
					return null;
					
				}
	

}
