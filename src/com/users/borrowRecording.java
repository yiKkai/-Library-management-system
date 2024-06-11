package com.users;

public class borrowRecording {
	private String borrowerName;
	private String borrowDate;
	private String borrowedBookName;
	private String borrowedBookID;
	private String record;
	private String whetherBack;
	
//	`borrower` borrowerName, `borrowed_book` borrowedBookName, `borrow_date` borrowDate,  `isReturn` whetherBack
	public borrowRecording() {
		super();
	}

	
	
	public String getBorrowedBookID() {
		return borrowedBookID;
	}



	public void setBorrowedBookID(String borrowedBookID) {
		this.borrowedBookID = borrowedBookID;
	}



	public String getRecord() {
		return record;
	}



	public void setRecord(String record) {
		this.record = record;
	}



	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBorrowedBookName() {
		return borrowedBookName;
	}

	public void setBorrowedBookName(String borrowedBookName) {
		this.borrowedBookName = borrowedBookName;
	}

	public String getWhetherBack() {
		return whetherBack;
	}

	public void setWhetherBack(String whetherBack) {
		this.whetherBack = whetherBack;
	}



	@Override
	public String toString() {
		return "borrowRecording [borrowerName=" + borrowerName + ", borrowDate=" + borrowDate + ", borrowedBookName="
				+ borrowedBookName + ", borrowedBookID=" + borrowedBookID + ", record=" + record + ", whetherBack="
				+ whetherBack + "]";
	}


	
	
	
	
	
	
}
