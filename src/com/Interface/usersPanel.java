package com.Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.ImplService.UsersDAOImpl;
import com.library.Book;
import com.users.borrowRecording;
import com.users.user;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class usersPanel extends JPanel {
	
	user uer = login.window.uer;
	static usersPanel ue;
	static List re;
	
	private mainPage mainUI;
	public void setMain(mainPage m) {
		mainUI = m;
	}
	
	public static void main(String[] agrs)
    {
    	skr.pifu();
        JFrame frame=new JFrame("test");
        ue = new usersPanel();
        frame.getContentPane().add(ue);
        frame.setSize(1200,1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
	
	public void showBorrowing() {
		backBooksTable back = new backBooksTable();
		System.out.println("图书归还列表展示：");
		back.setVisible(true);
		back.tableModel.setRowCount(0);
		String IDaName = uer.getUserName()+"(ID:"+uer.getUserId()+")";
		re = UsersDAOImpl.getRecordByIDAname(IDaName);
		
		if(re.isEmpty()) {
			String[] rowValues = {"暂无借阅书籍"}; 
			back.tableModel.addRow(rowValues);
		}else {
			for (int i = 0; i < re.size(); i++) {
			borrowRecording br;
			br = (borrowRecording) re.get(i);
			//"书名","借阅者","借阅日期","是否归还"
			String[] rowValues = {br.getBorrowedBookName(),br.getBorrowerName(),br.getBorrowDate(),br.getWhetherBack() };
			back.tableModel.addRow(rowValues);

			}
		}
		
	}
	
	
	public usersPanel() {
		setBackground(new Color(30, 144, 255));
		setLayout(null);
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
		DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
		DefaultMutableTreeNode bookManage = new DefaultMutableTreeNode("图书管理");
		DefaultMutableTreeNode borrowManage = new DefaultMutableTreeNode("借阅管理");

		root.add(bookManage);
		root.add(userManage);
		root.add(borrowManage);
		DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
		Color color = new Color(0, 204, 255);
		myRenderer.setBackgroundNonSelectionColor(color);
		myRenderer.setBackgroundSelectionColor(new Color(140, 140, 140));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(468, 141, 590, 279);
		add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("宋体", Font.PLAIN, 22));
		textArea.setText(uer.getUserRecord());
		scrollPane_1.setViewportView(textArea);
		
		JButton borrowBookbt = new JButton("图书借阅");
		borrowBookbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booksBorrowing bv = new booksBorrowing();
				bv.setVisible(true);
				
			}
		});
		borrowBookbt.setToolTipText("");
		borrowBookbt.setForeground(new Color(51, 51, 204));
		borrowBookbt.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		borrowBookbt.setBounds(235, 446, 190, 74);
		add(borrowBookbt);
		
		JButton returnBookbt = new JButton("图书归还");
		returnBookbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				backBook();
				showBorrowing();
			}
		});
		returnBookbt.setToolTipText("");
		returnBookbt.setForeground(new Color(154, 205, 50));
		returnBookbt.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		returnBookbt.setBounds(235, 544, 190, 74);
		add(returnBookbt);
		
		JLabel lblNewLabel = new JLabel("个人借阅记录");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 48));
		lblNewLabel.setBounds(450, 11, 333, 120);
		add(lblNewLabel);
		

	}
}
