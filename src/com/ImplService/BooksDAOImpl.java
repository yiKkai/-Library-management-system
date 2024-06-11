package com.ImplService;

import com.DAO.BooksDAO;
import com.Interface.booksEditor;
import com.library.Book;

import utilsBag.DBUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;


public class BooksDAOImpl {
	
	static String id;
	static String date;
	static String name;
	static String author;
	static String publisher;
	static String type;
	static String about;
	static int amount;
	static String as;
	
	
	public static void delBookbyID(String ID) {
		//删除一本图书
		
		String sql = "DELETE FROM books_info WHERE book_id =?";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn, sql,ID);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	public static void editBooksByID(String id) {
		//修改图书信息
		date=(booksEditor.getDatepick().getText()).toString();
		name= booksEditor.getNametextField().getText();
		author = booksEditor.getAuthortextField().getText();
		publisher = booksEditor.getPlacetextField().getText();
		type = booksEditor.getTypetextField().getText();
		about= booksEditor.getAboutTextArea().getText();
		as= booksEditor.getAmounttextField().getText();
		
		if(!(as.equals(""))) {
			amount = Integer.parseInt(as);
		}else {
			amount = 0;
		}
		
		String sql = "update books_info set book_name=?,type=?,`author`=? , `date`=? ,`amount`=? ,"
				+ "`about`=? ,`publisher`=?  where  book_id = ?";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn, sql,name,type,author,date,amount,about,publisher,id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	public static void insertBook() {
		// 新增一本图书
		id= booksEditor.getIDtextField().getText();
		date=(booksEditor.getDatepick().getText()).toString();
		name= booksEditor.getNametextField().getText();
		author = booksEditor.getAuthortextField().getText();
		publisher = booksEditor.getPlacetextField().getText();
		type = booksEditor.getTypetextField().getText();
		about= booksEditor.getAboutTextArea().getText();
		as= booksEditor.getAmounttextField().getText(); //String类型的书本数量
		
		if(!(as.equals(""))) {
			amount = Integer.parseInt(as);
		}else {
			amount = 0;
		}
		
		if(date.equalsIgnoreCase("")) {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dd = new Date(System.currentTimeMillis());
			date = formatter.format(dd).toString();
		}
		
		String sql ="insert into books_info(`book_id`,`book_name`,`author`,`date`,`amount`,`about`,`type`,`publisher`,`getbor_Times`) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn, sql,id,name,author,date,amount,about,type,publisher,0);
//			System.out.println("添加了" + insertCount + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		
		
	}
	
	public static List getBookList() {
		String sql ="select `book_id` bookid,`book_name` bookname,`author` author,`date` date,`amount` amount,`about` about,`type` booktype,`publisher` editPlace from books_info ";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanListHandler<Book>  handler = new BeanListHandler<>(Book.class);
			List<Book> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	
	public static List getSearchedBookListByName(String name) { //模糊查询获得结果集
		String sql ="SELECT `book_id` bookid,`book_name` bookname,`author` author,`date` DATE,`amount` amount,`about` about,`type` booktype,`publisher` editPlace FROM books_info WHERE book_name LIKE '%"+name+"%'";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			BeanListHandler<Book>  handler = new BeanListHandler<>(Book.class);
			List<Book> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	
	
	public static List<Object> getTypeList() { //获取数据库中所有书的种类
		String sql ="SELECT  DISTINCT type booktype FROM `books_info`";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
//			System.out.println("impl层开始了");
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			ColumnListHandler  handler = new ColumnListHandler();
			List<Object> list = runner.query(conn, sql, handler);
			list.forEach(System.out::println);
//			System.out.println("impl层结束");
			return list;
//			System.out.println("添加了" + insertCount + "条记录");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
		
	}
	
	public static void getBookBorrowed(String bookID) { //借书
		String sql ="UPDATE `books_info` SET amount=amount-1 WHERE `book_id`=?";
		String sql1 ="UPDATE `books_info` SET `getbor_Times`=`getbor_Times`+1 WHERE `book_id`=?";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn,sql,bookID);
			runner.update(conn,sql1,bookID);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	public static void getBookBacked(String book) { //还书
		String sql ="UPDATE `books_info` SET amount=amount+1 WHERE `book_name`=?";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			runner.update(conn,sql,book);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	
	
	public static boolean whetherBkIDisExist(String id) {
		String sql ="SELECT book_id FROM `books_info` where book_id=?";
		Connection conn = null;
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
		try {
//			System.out.println("impl层开始了");
			QueryRunner runner = new QueryRunner();
			conn = DBUtils.getConnection();
			ColumnListHandler  handler = new ColumnListHandler();
			List<Object> ID = runner.query(conn, sql, handler,id);
//			String s = ID.toString();
			if(ID.isEmpty()) {
				return true;
			}else {
				return false;
			}

		}catch (Exception e) {
//			e.printStackTrace();
			
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return null != null;
			
	}


			//6.6
			public static void clearAllgetborrowTime(){ //清空所有读者借阅次数
				String sql ="UPDATE `books_info` SET `getbor_Times`=0";
				Connection conn = null;
				DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
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
	
			// 6/6
			public static List getHotbookList(){ //获取所有书籍的借阅次数
				String sql ="SELECT `getbor_Times` FROM `books_info`";
				String sql1="SELECT `book_name` FROM `books_info`";
				Connection conn = null;
				DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
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
			
			
			// 6/7
						public static List getHotbookList1(){ //获取所有书籍的借阅次数
							String sql ="SELECT `getbor_Times` getBorrowedTime,`book_name` bookname FROM `books_info` ORDER BY `getbor_Times` DESC";
							
							Connection conn = null;
							DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
							try {
								QueryRunner runner = new QueryRunner();
								conn = DBUtils.getConnection();
								BeanListHandler<Book>  handler = new BeanListHandler<>(Book.class);
								List<Book> list = runner.query(conn, sql, handler);
								
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
