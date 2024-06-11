package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.ImplService.Utils;
import com.users.user;

import utilsBag.*;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField IDtextField;
	private JTextField nameText;
	private JTextField pswText;
	private JLabel accountlabel;
	private JLabel psw;
	private JLabel userIdentity;
	private JButton loginButton;
	private JLabel userAbout;
	private JTextArea aboutTextArea;
	private JLabel psw2;
	private JTextField pswText2;
	
	
	//用户类变量
	String iden="读者";
	String Name;
	Date signDate;
	String about;
	String ID;
	String passwd;
	String passwd2;
	
	//连接数据库
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JButton emptyButton;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
		
		
	public boolean isEmpty(String id,String name,String passwd,String passwd2) {
		if(id.equals("")||name.equals("")||passwd.equals("")||passwd2.equals("")) {
			return false;
		}
		return true;
	}
	
	
	public void recordInfo() {
		ID = IDtextField.getText();
		Name = nameText.getText();
		about = aboutTextArea.getText();
		passwd = pswText.getText();
		passwd2 = pswText2.getText();
		
		boolean point = isEmpty(ID, Name, passwd,passwd2);
		if(point) {
			if(passwd2.equals(passwd)) {
				DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC");
				String uID = null;
				String uName=null;
				user uer = null;
				String sqlt="select `user_Name` userName, `user_id` userId from userinfo where user_Name= ? or user_id= ?";
				
					uer = Utils.getInstance(user.class, sqlt,Name,ID);
//				
				
				if(uer == null) {
					String sql ="insert into userinfo(user_Name,user_type,user_id,user_password,user_about,user_Sta,sign_date,record,owned_bk,borrow_time) "
							+ "values(?,?,?,?,?,?,?,?,?,?)";
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date curdate = new Date(System.currentTimeMillis());
					String date = formatter.format(curdate).toString();
					int insertCount = Utils.update(sql,Name,iden,ID,passwd,about,"非冻结状态",date,"","",0);
					if(insertCount > 0){
						JOptionPane.showMessageDialog(this, "注册成功√","Success",JOptionPane.NO_OPTION);
						IDtextField.setText("");
						nameText.setText("");
						pswText.setText("");
						pswText2.setText("");
						aboutTextArea.setText("");
					}else{		
						JOptionPane.showMessageDialog(this, "注册失败","failed",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					//user !=null
					if(uer.getUserId().equals(ID)&& !uer.getUserName().equals(Name)) {
						JOptionPane.showMessageDialog(this, "注册失败，ID为"+ID+"的账号已存在！","提示",JOptionPane.WARNING_MESSAGE);
					}else if(uer.getUserName().equals(Name)&& !uer.getUserId().equals(ID)) {
						JOptionPane.showMessageDialog(this, "注册失败，该用户名已被占用！","提示",JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(this, "注册失败，该用户已存在！","提示",JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this, "两次输入的密码不同，请确认新密码","提示",JOptionPane.ERROR_MESSAGE);
			}
					
			
		}else {
			JOptionPane.showMessageDialog(this, "有信息尚未填写,请确认有无疏漏信息！","提示",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	/**
	 * Create the frame.
	 */
	public register() {
		setTitle("注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 806);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IDtextField = new JTextField();
		IDtextField.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		IDtextField.setColumns(10);
		IDtextField.setBounds(195, 79, 148, 35);
		contentPane.add(IDtextField);
		
		nameText = new JTextField();
		nameText.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		nameText.setColumns(10);
		nameText.setBounds(195, 135, 148, 35);
		contentPane.add(nameText);
		
		pswText = new JTextField();
		pswText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		pswText.setColumns(10);
		pswText.setBounds(195, 191, 148, 35);
		contentPane.add(pswText);
		
		JLabel IDlabel = new JLabel("ID：");
		IDlabel.setForeground(Color.WHITE);
		IDlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		IDlabel.setBounds(143, 79, 54, 35);
		contentPane.add(IDlabel);
		
		accountlabel = new JLabel("用户名：");
		accountlabel.setForeground(Color.WHITE);
		accountlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 22));
		accountlabel.setBounds(105, 134, 96, 35);
		contentPane.add(accountlabel);
		
		psw = new JLabel("密码：");
		psw.setForeground(Color.WHITE);
		psw.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 22));
		psw.setBounds(129, 192, 72, 35);
		contentPane.add(psw);
		
		userIdentity = new JLabel("用户身份：");
		userIdentity.setForeground(Color.WHITE);
		userIdentity.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 22));
		userIdentity.setBounds(86, 347, 111, 35);
		contentPane.add(userIdentity);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(179, 33, 146, 13);
		contentPane.add(progressBar);
		
		
		String[] s = { "读者", "管理员" };
		JComboBox choser = new JComboBox(s);
		choser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iden = (String) choser.getSelectedItem();
				System.out.println("身份选择）:"+iden);
			}
		});
		choser.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 18));
		choser.setBounds(195, 346, 111, 35);
		contentPane.add(choser);
		
		
		loginButton = new JButton("注册");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
				recordInfo();
			}
		});
		loginButton.setToolTipText("resignB");
		loginButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		loginButton.setBounds(302, 596, 146, 87);
		contentPane.add(loginButton);
		
		userAbout = new JLabel("个人简介：");
		userAbout.setForeground(Color.WHITE);
		userAbout.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 22));
		userAbout.setBounds(86, 404, 111, 35);
		contentPane.add(userAbout);
		
		aboutTextArea = new JTextArea();
		aboutTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		aboutTextArea.setBounds(193, 408, 344, 141);
		contentPane.add(aboutTextArea);
		
		psw2 = new JLabel("请再次输入密码：");
		psw2.setForeground(Color.WHITE);
		psw2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 21));
		psw2.setBounds(28, 248, 173, 35);
		contentPane.add(psw2);
		
		pswText2 = new JTextField();
		pswText2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		pswText2.setColumns(10);
		pswText2.setBounds(195, 247, 148, 35);
		contentPane.add(pswText2);
		
		emptyButton = new JButton("重置");
		emptyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutTextArea.setText("");
				pswText2.setText("");
				pswText.setText("");
				nameText.setText("");
				IDtextField.setText(""); 
			}
		});
		emptyButton.setToolTipText("change");
		emptyButton.setForeground(Color.WHITE);
		emptyButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		emptyButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		emptyButton.setBounds(143, 596, 112, 65);
		contentPane.add(emptyButton);
		
		
	}
}



