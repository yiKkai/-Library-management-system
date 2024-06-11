package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.ImplService.Utils;
import com.users.user;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userManaging extends JFrame {

	 JPanel contentPane;
	 JTextField NameTextField;
	 JTextField IDtextField;
	 JTextField statetextField;
	 JTextArea textArea;
	 JButton unFreezeButton;
	 JButton freezeUerButton;
	 
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userManaging frame = new userManaging();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void unFreezeAccount() {
		//解冻
		int select = usersList.table.getSelectedRow();
		user beforeU = usersList.uList.get(select);
		
		int n = JOptionPane.showConfirmDialog(null, "确认解冻账户"+NameTextField.getText()+"?", "解冻",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1  
		if(n==0) {
			System.out.println("选择解冻");
			String sql="update userinfo set user_Sta = '非冻结状态'  where user_Name = ? and user_id = ?"; 
			int insertCount =Utils.update(sql, NameTextField.getText(),IDtextField.getText());
			if(insertCount > 0) {
				JOptionPane.showMessageDialog(this, "解冻成功","Success",JOptionPane.DEFAULT_OPTION);
				beforeU.setuState("非冻结状态");
				usersList.uList.set(select, beforeU);
				freezeUerButton.setEnabled(true);
				unFreezeButton.setEnabled(false);
				
				usersList.table.setValueAt(beforeU.getuState(), select, 5);
				

			}else {
				JOptionPane.showMessageDialog(this, "解冻失败","提示",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public void FreezeAccount() {
		//冻结
		int select = usersList.table.getSelectedRow();
		user beforeU = usersList.uList.get(select);
		
				
			int n = JOptionPane.showConfirmDialog(null, "确认冻结账户"+NameTextField.getText()+"?", "冻结",JOptionPane.YES_NO_OPTION);//返回的是按钮的index  i=0或者1  
			if(n==0) {
				System.out.println("选择冻结");
				String sql="update userinfo set user_Sta = '冻结状态'  where user_Name = ? and user_id = ?"; 
				int insertCount =Utils.update(sql, NameTextField.getText(),IDtextField.getText());
				if(insertCount > 0) {
					JOptionPane.showMessageDialog(this, "冻结成功","Success",JOptionPane.DEFAULT_OPTION);
					beforeU.setuState("冻结状态");
					usersList.uList.set(select, beforeU);
					freezeUerButton.setEnabled(false);
					unFreezeButton.setEnabled(true);
					usersList.table.setValueAt(beforeU.getuState(), select, 5);
				}else {
					JOptionPane.showMessageDialog(this, "冻结失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	
	public userManaging() {
		setTitle("用户管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1190, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameTextField = new JTextField();
		NameTextField.setEditable(false);
		NameTextField.setToolTipText("*必填");
		NameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		NameTextField.setColumns(10);
		NameTextField.setBounds(275, 134, 156, 46);
		contentPane.add(NameTextField);
		
		IDtextField = new JTextField();
		IDtextField.setEditable(false);
		IDtextField.setToolTipText("*必填");
		IDtextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		IDtextField.setColumns(10);
		IDtextField.setBounds(275, 202, 156, 46);
		contentPane.add(IDtextField);
		
		JLabel lblId = new JLabel("用户昵称：");
		lblId.setForeground(Color.DARK_GRAY);
		lblId.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId.setBounds(130, 132, 163, 46);
		contentPane.add(lblId);
		
		JLabel lblId_1 = new JLabel("用户ID：");
		lblId_1.setForeground(Color.DARK_GRAY);
		lblId_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId_1.setBounds(146, 200, 133, 46);
		contentPane.add(lblId_1);
		
		JLabel ssx_1_1_1_1 = new JLabel("用户简介：");
		ssx_1_1_1_1.setForeground(Color.DARK_GRAY);
		ssx_1_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1_1.setBounds(130, 286, 163, 41);
		contentPane.add(ssx_1_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 294, 305, 155);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("宋体", Font.PLAIN, 22));
		scrollPane.setViewportView(textArea);
		
		JLabel ssx_1_1_1_1_1 = new JLabel("当前状态：");
		ssx_1_1_1_1_1.setForeground(Color.DARK_GRAY);
		ssx_1_1_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1_1_1.setBounds(130, 60, 163, 41);
		contentPane.add(ssx_1_1_1_1_1);
		
		statetextField = new JTextField();
		statetextField.setEditable(false);
		statetextField.setToolTipText("*必填");
		statetextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		statetextField.setColumns(10);
		statetextField.setBounds(275, 59, 156, 46);
		contentPane.add(statetextField);
		
		JLabel ssx_1_1_1_1_2 = new JLabel("借阅记录：");
		ssx_1_1_1_1_2.setForeground(Color.DARK_GRAY);
		ssx_1_1_1_1_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1_1_2.setBounds(654, 134, 156, 41);
		contentPane.add(ssx_1_1_1_1_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(803, 142, 331, 250);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("宋体", Font.PLAIN, 22));
		scrollPane_1.setViewportView(textArea_1);
		
		unFreezeButton = new JButton("解冻账号");
		unFreezeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unFreezeAccount();
				
			}
		});
		unFreezeButton.setToolTipText("解冻");
		unFreezeButton.setForeground(new Color(0, 0, 128));
		unFreezeButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		unFreezeButton.setBounds(534, 539, 127, 65);
		unFreezeButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		contentPane.add(unFreezeButton);
		
		freezeUerButton = new JButton("冻结账号");
		freezeUerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FreezeAccount();
			}
		});
		freezeUerButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		
		freezeUerButton.setToolTipText("冻结");
		freezeUerButton.setForeground(Color.WHITE);
		freezeUerButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		freezeUerButton.setBackground(Color.BLUE);
		freezeUerButton.setBounds(749, 539, 127, 65);
		contentPane.add(freezeUerButton);
	}
}
