package com.users;

import java.sql.Date;

public class reader extends user {
	
	
	



	public reader(String userName, Date signDate, String aboutMe, String userId, String password, String userType,
			String userRecord, boolean isback, String record) {
		super();
		this.isback = isback;
		this.record = record;
	}



	private boolean isback;
//	private Date BorrowDate;	/*借阅时间*/
//	private Date BackDate	;	/*归还时间*/
//	private Date appointedDate;	/*超期天数*/
//	private float FineMoney;	/*罚款金额*/
//	
	private String record;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
