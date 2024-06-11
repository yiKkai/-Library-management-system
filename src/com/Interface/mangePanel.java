package com.Interface;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;


import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


public class mangePanel extends JPanel {
	
	private mainPage mainUI;
	static JFrame frame =new JFrame("test");;
	public void setMain(mainPage m) {
		mainUI = m;
	}
	
	public static void main(String[] agrs)
    {
    	skr.pifu();
       
        mangePanel mp = new mangePanel();
        frame.getContentPane().add(mp);
        frame.setSize(1200,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
	
	public mangePanel () {
		setBackground(new Color(0, 153, 255));
		setLayout(null);
		
		JButton manageUser = new JButton("用户管理");
		manageUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersList ul = new usersList();
				ul.setVisible(true);
			}
		});
		manageUser.setToolTipText("");
		manageUser.setForeground(new Color(153, 0, 255));
		manageUser.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		manageUser.setBounds(231, 313, 190, 74);
		add(manageUser);
		
		JButton manageBook = new JButton("图书管理");
		manageBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookList bl = new bookList();
				bl.setVisible(true);
				
			}
		});
		manageBook.setToolTipText("");
		manageBook.setForeground(new Color(51, 51, 204));
		manageBook.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		manageBook.setBounds(231, 206, 190, 74);
		manageBook.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		add(manageBook);
		
		JButton manageUser_1 = new JButton("借阅记录");
		manageUser_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				borrowRecord();
				
			}
		});
		manageUser_1.setToolTipText("");
		manageUser_1.setForeground(new Color(100, 149, 237));
		manageUser_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		manageUser_1.setBounds(231, 418, 190, 74);
		add(manageUser_1);
		
		

	}
}
