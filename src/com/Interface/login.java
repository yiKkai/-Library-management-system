package com.Interface;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.ImplService.UsersDAOImpl;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.users.user;

import utilsBag.*;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;


public class login extends JFrame{

	private JFrame frmLibrary;
	private JPasswordField passwordField;
	private JTextField userField;
	static login window;
	String userText=null;
	String pswText=null;
	user uer=null;
	
	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public String getPswText() {
		return pswText;
	}

	public void setPswText(String pswText) {
		this.pswText = pswText;
	}


	private JTextField textField;
	private JTextField textField_1;
	private JButton chpButton;
	
	boolean isEmpty(String s1,String s2) {
		if(s1.equals("")||s2.equals("")) {
			return false;
		}
			return true;
	}
	
	public <T> T getInstance(Class<T> clazz,String sql, Object... args) {
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
			DBUtils.close(conn, ps, rs);

		}

		return null;
	}
	
	public void runPage() {
		mainPage mp = new mainPage();
		mp.setVisible(true);
		mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.dispose();
	}
	
	
	public void loginFun() {
		userText= userField.getText();
		pswText= passwordField.getText();
		boolean point = isEmpty(userText, pswText);
		textField.setText(window.getUserText());
		textField_1.setText(window.getPswText());
		
		if(point) {
			
			DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
//			String sql="select `user_Name`,`user_type`,`user_id` from userinfo where `user_password` =? and `user_Name`=? or `user_id`=?";
//			String sql="select * from userinfo";
			//错误示范: *不可用
//			String sql="select `user_Name` userName,`user_type` userType, `user_id` userId,`user_password` upassword,`user_about` aboutMe,`sign_date` signDate,`record` userRecord, `user_Sta` uState from userinfo where user_password = ? and (user_Name= ? or user_id= ?)";
		
			uer = UsersDAOImpl.getSearchedUserByNameOrID(userText, userText, pswText); 
			
			if(uer != null) {
				String st=uer.getuState();
				System.out.println(st);
				if(st.equalsIgnoreCase("冻结状态")) {
					JOptionPane.showMessageDialog(this, "该用户已被冻结，无法登录  若需解除该状态请联系管理员","用户已冻结！",JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "登录成功","提示",JOptionPane.NO_OPTION);
					System.out.println(uer.toString());
					runPage();	
					userField.setText("");
					passwordField.setText("");
					this.dispose();
				}
			}else {
				JOptionPane.showMessageDialog(this, "该账号不存在或账号密码错误","提示",JOptionPane.ERROR_MESSAGE);
			}
		
		}else {
			JOptionPane.showMessageDialog(this, "密码或账号尚未填写！","提示",JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new login();
					window.frmLibrary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibrary = new JFrame();
		frmLibrary.setTitle("library");
		frmLibrary.getContentPane().setBackground(new Color(0, 153, 255));
		frmLibrary.setBounds(100, 100, 1458, 943);
		frmLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibrary.getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		passwordField.setBounds(645, 454, 148, 35);
		frmLibrary.getContentPane().add(passwordField);
		
		userField = new JTextField();
		userField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		userField.setBounds(645, 396, 148, 35);
		frmLibrary.getContentPane().add(userField);
		userField.setColumns(10);
		
		JLabel account = new JLabel("id/用户名：");
		account.setForeground(new Color(255, 255, 255));
		account.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 25));
		account.setBounds(517, 398, 132, 35);
		frmLibrary.getContentPane().add(account);
		
		JLabel psword = new JLabel("密码：");
		psword.setForeground(Color.WHITE);
		psword.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 25));
		psword.setBounds(573, 456, 76, 35);
		frmLibrary.getContentPane().add(psword);
		
		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFun();
			}
		});
		loginButton.setToolTipText("login");
		loginButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 35));
		loginButton.setBounds(833, 377, 124, 129);
		loginButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		frmLibrary.getContentPane().add(loginButton);
		
		JButton signButton = new JButton("注册账号");
		signButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register rs = new register();
				rs.setVisible(true);
				rs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		signButton.setForeground(Color.GRAY);
		signButton.setToolTipText("点击注册");
		signButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		signButton.setBounds(960, 575, 148, 58);
		signButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
		frmLibrary.getContentPane().add(signButton);
		
		textField = new JTextField();
		textField.setBounds(352, 377, 87, 35);
		frmLibrary.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(352, 438, 87, 35);
		frmLibrary.getContentPane().add(textField_1);
		
		chpButton = new JButton("修改密码");
		chpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pswChange chGframe = new pswChange();
				chGframe.setVisible(true);
				chGframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		chpButton.setToolTipText("change");
		chpButton.setForeground(Color.WHITE);
		chpButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		chpButton.setBounds(960, 656, 148, 58);
		chpButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		frmLibrary.getContentPane().add(chpButton);
		
		JLabel lblNewLabel = new JLabel("珠科图书系统");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 48));
		lblNewLabel.setBounds(485, 194, 330, 146);
		frmLibrary.getContentPane().add(lblNewLabel);
		
		JButton emptyButton = new JButton("重置");
		emptyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
			}
		});
		
		emptyButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		emptyButton.setToolTipText("change");
		emptyButton.setForeground(Color.WHITE);
		emptyButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		emptyButton.setBounds(658, 539, 112, 65);
		frmLibrary.getContentPane().add(emptyButton);
		
		getContentPane().setLayout(new FlowLayout());
	}
}
