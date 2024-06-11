package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ImplService.Utils;

import utilsBag.DBUtils;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pswChange extends JFrame {

	private JPanel contentPane;
	JTextField IDtext;
	JTextField nameText;
	private JTextField oldPswText;
	private JTextField newPswText;
	private JTextField newPswText2;
	private JLabel sasad_1;
	String ID=null;
	String Name=null;
	String oldPsw=null;
	String nP=null;
	String np2=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		skr.pifu();
		DBUtils.setUrl("jdbc:mysql://127.0.0.1/library?serverTimezone=UTC?serverTimezone=UTC");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pswChange frame = new pswChange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void changePsw() {
		// TODO 密码修改
//		int n = JOptionPane.showConfirmDialog(null, "确认修改用户密码"+"?", "修改密码",JOptionPane.YES_NO_OPTION);
		
			Name= nameText.getText();
			ID = IDtext.getText();
			oldPsw = oldPswText.getText();
			nP = newPswText.getText();
			np2 = newPswText2.getText();
			if(Name.equals("")||ID.equals("")||oldPsw.equals("")||nP.equals("")||np2.equals("")) {
				JOptionPane.showMessageDialog(this, "有信息尚未录入！","提示",JOptionPane.WARNING_MESSAGE);
			}else {
				if(oldPsw.equals(nP)) {
					JOptionPane.showMessageDialog(this, "旧密码不能与新密码相同！","提示",JOptionPane.WARNING_MESSAGE);
			} else {
				int n = JOptionPane.showConfirmDialog(null, "确认修改用户密码" + "?", "修改密码", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					if (nP.equals(np2)) {
						String sql = "update userinfo set user_password = ?  where user_Name = ? and user_id = ? and user_password =?";
						int insertCount = Utils.update(sql, nP, Name, ID, oldPsw);
						if (insertCount > 0) {
							JOptionPane.showMessageDialog(this, "密码修改成功√", "Success", JOptionPane.NO_OPTION);
							this.dispose();
						} else {
							JOptionPane.showMessageDialog(this, "验证错误 密码修改失败 X", "failed", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "两次输入的新密码不同", "error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
				

			}
			
			
	}
	
	
	public pswChange() {
		setForeground(new Color(143, 188, 143));
		setTitle("密码修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 613);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IDtext = new JTextField();
		IDtext.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDtext.setColumns(10);
		IDtext.setBounds(221, 132, 148, 35);
		contentPane.add(IDtext);
		
		nameText = new JTextField();
		nameText.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		nameText.setColumns(10);
		nameText.setBounds(221, 188, 148, 35);
		contentPane.add(nameText);
		
		oldPswText = new JTextField();
		oldPswText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		oldPswText.setColumns(10);
		oldPswText.setBounds(221, 244, 148, 35);
		contentPane.add(oldPswText);
		
		JLabel lblId = new JLabel("ID：");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		lblId.setBounds(169, 132, 54, 35);
		contentPane.add(lblId);
		
		JLabel accountlabel = new JLabel("用户名：");
		accountlabel.setForeground(Color.WHITE);
		accountlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		accountlabel.setBounds(128, 187, 106, 35);
		contentPane.add(accountlabel);
		
		JLabel ssx = new JLabel("密码：");
		ssx.setForeground(Color.WHITE);
		ssx.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		ssx.setBounds(151, 246, 72, 35);
		contentPane.add(ssx);
		
		JLabel sasad = new JLabel("新密码：");
		sasad.setForeground(Color.WHITE);
		sasad.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		sasad.setBounds(128, 300, 106, 35);
		contentPane.add(sasad);
		
		newPswText = new JTextField();
		newPswText.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		newPswText.setColumns(10);
		newPswText.setBounds(221, 300, 148, 35);
		contentPane.add(newPswText);
		
		newPswText2 = new JTextField();
		newPswText2.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		newPswText2.setColumns(10);
		newPswText2.setBounds(221, 355, 148, 35);
		contentPane.add(newPswText2);
		
		JLabel saqw = new JLabel("请再次输入新密码：");
		saqw.setForeground(Color.WHITE);
		saqw.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 20));
		saqw.setBounds(41, 356, 193, 35);
		contentPane.add(saqw);
		
		sasad_1 = new JLabel("密码修改");
		sasad_1.setForeground(Color.WHITE);
		sasad_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 40));
		sasad_1.setBounds(181, 32, 179, 69);
		contentPane.add(sasad_1);
		
		JButton changePswButton = new JButton("修改");
		changePswButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					changePsw();
			}

			
		});
		changePswButton.setToolTipText("resignB");
		changePswButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 45));
		changePswButton.setBounds(159, 450, 156, 100);
		contentPane.add(changePswButton);
	}
}
