package com.library;

import java.sql.Date;

public class Book {
	private String bookname;
	private String booktype;
	private String date; //入馆时间
	private int amount;
	private String author;
	private String about; //内容摘要
	private String bookid;
	private String editPlace; //出版社
	private int getBorrowedTime;
//	private image 配套图片
	
	
	public Book() {
		
	}
	
	
	public Book(String bookname, String booktype, String date, int amount, String author, String about, String bookid,
			String editPlace) {
		super();
		this.bookname = bookname;
		this.booktype = booktype;
		this.date = date;
		this.amount = amount;
		this.author = author;
		this.about = about;
		this.bookid = bookid;
		this.editPlace = editPlace;
	}

	
	

	public int getGetBorrowedTime() {
		return getBorrowedTime;
	}


	public void setGetBorrowedTime(int getBorrowedTime) {
		this.getBorrowedTime = getBorrowedTime;
	}


	public String getDate() {
		return date;
	}
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBooktype() {
		return booktype;
	}
	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEditPlace() {
		return editPlace;
	}
	public void setEditPlace(String editPlace) {
		this.editPlace = editPlace;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getPlace() {
		return editPlace;
	}
	public void setPlace(String place) {
		this.editPlace = place;
	}


	@Override
	public String toString() {
		return "Book: [bookname=" + bookname + ", booktype=" + booktype + ", date=" + date + ", amount=" + amount
				+ ", author=" + author + ", about=" + about + ", bookid=" + bookid + ", editPlace=" + editPlace
				+ ", getBorrowedTime=" + getBorrowedTime + "]";
	}
	
	
	
	
	
	
}
