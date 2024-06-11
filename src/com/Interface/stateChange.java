package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.ImplService.Utils;
import com.users.user;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class stateChange extends JFrame {
	static stateChange frame;
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField nameField;
	private JTextField curpswField;
	String curpsw = login.window.getPswText();
	String curcount= login.window.getUserText();
	String testpwd;
	
	

	
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new stateChange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void deleteU(String s1,String s2) {
		user uer = login.window.uer;
		if(s1.equals(s2)) {
			int n = JOptionPane.showConfirmDialog(null, "确认注销账户"+uer.getUserName()+"?", "删除账户",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1  
			if(n==0) {
				System.out.println("选择注销");
				String sql="delete from userinfo where user_Name = ? and user_id = ?"; 
				int insertCount =Utils.update(sql, uer.getUserName(),uer.getUserId());
				if(insertCount > 0) {
					JOptionPane.showMessageDialog(this, "该用户已注销","提示",JOptionPane.DEFAULT_OPTION);
					this.dispose();
//					mainPage.frame.dispose();
					
				}else {
					JOptionPane.showMessageDialog(this, "注销失败","failed",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "密码未输入或与用户密码不匹配X","failed",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void freezeU(String s1,String s2) {
		user uer = login.window.uer;
		if(s1.equals(s2)) {
			int n = JOptionPane.showConfirmDialog(null, "确认冻结账户"+uer.getUserName()+"?", "冻结",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1  
			if(n==0) {
				System.out.println("选择冻结");
				String sql="update userinfo set user_Sta = '冻结状态'  where user_Name = ? and user_id = ?"; 
				int insertCount =Utils.update(sql, uer.getUserName(),uer.getUserId());
				if(insertCount > 0) {
					JOptionPane.showMessageDialog(this, "冻结成功","Success",JOptionPane.DEFAULT_OPTION);
					
				}else {
					JOptionPane.showMessageDialog(this, "冻结失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "密码未输入或与用户密码不匹配X","提示",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	/**
	 * Create the frame.
	 */
	public stateChange() {
		
		
		user uer = login.window.uer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JButton cancellationButton = new JButton("注销账号");
		cancellationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testpwd =  curpswField.getText();
				deleteU(curpsw, testpwd);
			}
		});
		cancellationButton.setBounds(431, 356, 127, 39);
		cancellationButton.setToolTipText("注销");
		cancellationButton.setForeground(new Color(128, 0, 0));
		cancellationButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		cancellationButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		contentPane.add(cancellationButton);
		
		JButton freezeUerButton = new JButton("冻结账号");
		freezeUerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testpwd =  curpswField.getText();
				freezeU(curpsw, testpwd);
			}
		});
		freezeUerButton.setBounds(586, 356, 127, 39);
		freezeUerButton.setBackground(new Color(0, 0, 255));
		freezeUerButton.setToolTipText("冻结");
		freezeUerButton.setForeground(Color.WHITE);
		freezeUerButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		freezeUerButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		contentPane.add(freezeUerButton);
		
		JLabel UserIDlabel = new JLabel("当前用户ID：");
		UserIDlabel.setForeground(Color.WHITE);
		UserIDlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		UserIDlabel.setBounds(241, 95, 156, 53);
		contentPane.add(UserIDlabel);
		
		JLabel UserIDlabel_1 = new JLabel("当前用户名：");
		UserIDlabel_1.setForeground(Color.WHITE);
		UserIDlabel_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		UserIDlabel_1.setBounds(241, 178, 146, 57);
		contentPane.add(UserIDlabel_1);
		
		JLabel UserIDlabel_1_1 = new JLabel("请输入用户密码：");
		UserIDlabel_1_1.setForeground(Color.WHITE);
		UserIDlabel_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		UserIDlabel_1_1.setBounds(195, 263, 203, 57);
		contentPane.add(UserIDlabel_1_1);
		
		IDField = new JTextField();
		IDField.setEditable(false);
		IDField.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		IDField.setColumns(10);
		IDField.setBounds(385, 97, 156, 44);
		IDField.setText(uer.getUserId());
		contentPane.add(IDField);
		
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		nameField.setColumns(10);
		nameField.setBounds(385, 182, 156, 44);
		nameField.setText(uer.getUserName());
		contentPane.add(nameField);
		
		curpswField = new JTextField();
		curpswField.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		curpswField.setColumns(10);
		curpswField.setBounds(385, 267, 156, 44);
		contentPane.add(curpswField);
	}
}
