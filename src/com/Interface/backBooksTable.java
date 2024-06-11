package com.Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.ImplService.BooksDAOImpl;
import com.ImplService.UsersDAOImpl;
import com.sun.glass.events.WindowEvent;
import com.users.borrowRecording;
import com.users.user;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class backBooksTable extends JFrame {
	private JTable table;
	static DefaultTableModel tableModel;
	JButton backButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			backBooksTable dialog = new backBooksTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void backBooks(){
		user uer = login.window.uer;
		String owned = uer.getuOwned();
		String borrower = uer.getUserName()+"(ID:"+uer.getUserId()+")";
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		List ll = usersPanel.re;
		if (selectedRow != -1) { // 是否存在选中行
			borrowRecording be = (borrowRecording) ll.get(selectedRow);
			String bookName = be.getBorrowedBookName();
			
			if(be.getWhetherBack().equalsIgnoreCase("已归还")) {
				JOptionPane.showMessageDialog(this, "该书籍  《" + be.getBorrowedBookName() + " 》已归还，无需归还！", "已归还",
						JOptionPane.NO_OPTION);
			}else {
				int n = JOptionPane.showConfirmDialog(null, "确认归还书籍  《" + bookName + " 》?", "归还书本",
						JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
				if (n == 0) {
					String newOwned = owned.replaceAll(bookName,"");
					login.window.uer.setuOwned(newOwned);
					UsersDAOImpl.backBorrowingBook(bookName, borrower,uer.getUserId());
					BooksDAOImpl.getBookBacked(bookName);
					be.setWhetherBack("已归还");
					ll.set(selectedRow, be);
					table.setValueAt(be.getWhetherBack(), selectedRow,3); // 
					
					JOptionPane.showMessageDialog(this, "归还书籍  《" + be.getBorrowedBookName() + " 》成功√", "Success",
							JOptionPane.NO_OPTION);
				}
			}
		}
	}
	/**
	 * Create the glish
	 */
	public backBooksTable() {
		setTitle("个人借阅记录");
		setBounds(100, 100, 865, 394);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				backButton = new JButton("归还图书");
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						backBooks();
					}
				});
				{
					JButton btnNewButton = new JButton("New button");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
//							this.setVisible(false);
//							this.dispose();
//							 ((mainPage)this.getOwner()).exitLogin();
						}
					});
					buttonPane.add(btnNewButton);
				}
				backButton.setFont(new Font("楷体", Font.PLAIN, 25));
				buttonPane.add(backButton);
			}
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchType();
				}

				private void switchType() {
					// TODO 全部记录(按时间序列排列)", "已归还图书", "待归还图书"
					String s = (String) comboBox.getSelectedItem();
					List ll = usersPanel.re;
					String s1="已归还";
					List v1 = new ArrayList(),v2 = new ArrayList();
					for(int i=0;i<ll.size();i++) {
						borrowRecording br;
						br = (borrowRecording) ll.get(i);
						if(br.getWhetherBack().equals(s1)) {
							v1.add(br);
						}else {
							v2.add(br);
						}
					}
					if (s.equals("全部记录(按时间序列排列)")) {
						tableModel.setRowCount(0);
						table.removeAll();
						if(ll.isEmpty()) {
							String[] rowValues = {"暂无借阅书籍"}; 
							tableModel.addRow(rowValues);
						}else {
							for (int i = 0; i < ll.size(); i++) {
							borrowRecording br;
							br = (borrowRecording) ll.get(i);
							//"书名","借阅者","借阅日期","是否归还"
							String[] rowValues = {br.getBorrowedBookName(),br.getBorrowerName(),br.getBorrowDate(),br.getWhetherBack() };
							tableModel.addRow(rowValues);
							}
						}
						
					} else if(s.equals("已归还图书")) {
						tableModel.setRowCount(0);
						table.removeAll();
						for (int i = 0; i < v1.size(); i++) {
							borrowRecording br;
							br = (borrowRecording) v1.get(i);
							//"书名","借阅者","借阅日期","是否归还"
							String[] rowValues = {br.getBorrowedBookName(),br.getBorrowerName(),br.getBorrowDate(),br.getWhetherBack() };
							tableModel.addRow(rowValues);
							}
					}else {
						tableModel.setRowCount(0);
						table.removeAll();
						for (int i = 0; i < v2.size(); i++) {
							borrowRecording br;
							br = (borrowRecording) v2.get(i);
							//"书名","借阅者","借阅日期","是否归还"
							String[] rowValues = {br.getBorrowedBookName(),br.getBorrowerName(),br.getBorrowDate(),br.getWhetherBack() };
							tableModel.addRow(rowValues);
						}
					}
					
				}
			});
			comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			getContentPane().add(comboBox, BorderLayout.NORTH);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"全部记录(按时间序列排列)", "已归还图书", "待归还图书"}));
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				
				 String[] columnNames = {"书名","借阅者","借阅日期","是否归还"};   //列名
				 tableModel = new DefaultTableModel();
				 tableModel.setColumnIdentifiers(columnNames);
				 table = new JTable(tableModel);
				 table.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
			     table.setRowHeight(55);//指定每一行的行高50
			     table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //单选
				scrollPane.setViewportView(table);
			}
		}
	}

}
