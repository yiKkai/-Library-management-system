package com.DAO;

import java.sql.*;

import com.library.Book;

public interface BooksDAO {
	
	static void insertBook() {
		// TODO Auto-generated method stub
		
	} //增加一本书
	
	void deleteBook(); //删除一本书
	
	void changeBkInfo(); //修改该书的相关信息
	
	Book checkoneBook(int id); //查找一本书
	
	
//	Date checkUpdateTime(); //查找最近更新书库的时间
	
	Date checkBookUpdateTime(int id); //根据编号查找该书信息最近更新的时间
	
	
	
	
	
	
}
