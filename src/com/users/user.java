package com.users;

import java.sql.Date;

public class  user {
	private String userName;
	private String signDate;
	private String aboutMe;
	private String userId;
	private String upassword;
	private String userType;
	private String userRecord;
	private String uState;
	private String uOwned;
	private int brtimes;
	
	public user() {
		
	}
	
	public user(String userName, String signDate, String aboutMe, String userId, String password, String userType,
			String userRecord) {
		super();
		this.userName = userName;
		this.signDate = signDate;
		this.aboutMe = aboutMe;
		this.userId = userId;
		this.upassword = password;
		this.userType = userType;
		this.userRecord = userRecord;
	}
	
	
	
	


	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public int getBrtimes() {
		return brtimes;
	}

	public void setBrtimes(int brtimes) {
		this.brtimes = brtimes;
	}

	

	public String getUserType() {
		return userType;
	}
	public String getUserRecord() {
		return userRecord; 
	}

	public void setUserRecord(String userRecord) {
		this.userRecord = userRecord;
	}

	public void setUserType(String userType) {
//		this.userType = "普通用户";
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getuState() {
		return uState;
	}

	public void setuState(String uState) {
		this.uState = uState;
	}

	public String getuOwned() {
		return uOwned;
	}

	public void setuOwned(String uOwned) {
		this.uOwned = uOwned;
	}

	@Override
	public String toString() {
		return "user [userName=" + userName + ", signDate=" + signDate + ", aboutMe=" + aboutMe + ", userId=" + userId
				+ ", upassword=" + upassword + ", userType=" + userType + ", userRecord=" + userRecord + ", uState="
				+ uState + ", uOwned=" + uOwned + ", brtimes=" + brtimes + "]";
	}
	
	
	
	
	
}
