package com.Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ImplService.BooksDAOImpl;
import com.ImplService.UsersDAOImpl;
import com.library.Book;
import com.users.user;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class usersList extends JFrame {

	private final JPanel contentPanel = new JPanel();
	static DefaultTableModel tableModel; 
	static JTable table;
	JComboBox comboBox;
	
	static List<user> uList = UsersDAOImpl.getUserList();
	static List utypeList = UsersDAOImpl.getStaList();
	
	List selList;
	List typeList;
	List typeCollectList = new ArrayList();
	private JTextField textField;
	
	public void putTypeOnSel(List typeList) {
//		System.out.println("界面层开始了");
		typeList = utypeList;
		comboBox.removeAllItems();
		comboBox.addItem("全部读者");
		typeList = utypeList;
		for(int i=0;i<typeList.size();i++) {
			comboBox.addItem((typeList.get(i)).toString());
		}
//		System.out.println("界面结束");
		
	}
	
	public void getuType() {
		comboBox.removeAllItems();
		comboBox.addItem("全部读者");
		for(int i=0;i<utypeList.size();i++) {
			comboBox.addItem((utypeList.get(i)).toString());
		}
//		System.out.println("界面结束");
		
	}
	
	public void switchType() {
		String s = (String) comboBox.getSelectedItem();
		if (s.equals("全部读者")) {
			selList= uList;
			putusersListOnTable(uList);
		} else {
			typeCollectList.clear();
			
			for (int i = 0; i < uList.size(); i++) {
				if (uList.get(i).getuState().equalsIgnoreCase(s)) {
					typeCollectList.add(uList.get(i));
				}
			}
			selList = typeCollectList;
			
			user uer;
			tableModel.setRowCount(0);
			table.removeAll();

			for (int j = 0; j < selList.size(); j++) {
				uer = (user) selList.get(j);

				String[] rowValues = { uer.getUserId(), uer.getUserName(), uer.getAboutMe(), uer.getUserRecord(),
						uer.getSignDate(), uer.getuState() };
				tableModel.addRow(rowValues);

			}
		}

	}
	
	public void searchUser() {
		String info = textField.getText();
		List searchList = UsersDAOImpl.getSearcheduserListByName(info);
		if(searchList.isEmpty()) {
			tableModel.setRowCount(0);
			String[] rowValues = {"暂无相关人员"}; 
			tableModel.addRow(rowValues);
		}else {
			selList = searchList;
			tableModel.setRowCount(0);
			putusersListOnTable(searchList);
		}
	}
	
	public void ManageBorrowing() {
		//管理读者借阅相关
		
	}
	
	
	public void sataeMagning() {
		// 更改读者状态
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) { // 是否存在选中行

			userManaging um = new userManaging();
			um.setVisible(true);
			user uer = (user) selList.get(selectedRow);

			um.NameTextField.setText(uer.getUserName());
			um.IDtextField.setText(uer.getUserId());
			um.textArea.setText(uer.getAboutMe());
			um.statetextField.setText(uer.getuState());
			um.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			String sta = uer.getuState();
			switch (sta) {
			case "冻结状态":
				um.freezeUerButton.setEnabled(false);
				um.unFreezeButton.setEnabled(true);
				break;

			case "非冻结状态":
				um.unFreezeButton.setEnabled(false);
				um.freezeUerButton.setEnabled(true);
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		skr.pifu();
		try {
			usersList dialog = new usersList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public usersList() {
		setTitle("读者用户管理");
		setBounds(100, 100, 1035, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchType();
				}
			});
			comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			contentPanel.add(comboBox, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String[] columnNames = {"ID","用户名","简介","借阅记录","注册日期","用户状态"};   //列名
				tableModel = new DefaultTableModel();
				 tableModel.setColumnIdentifiers(columnNames);
				 
				table = new JTable(tableModel);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setRowHeight(55);
				table.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
				scrollPane.setViewportView(table);
				
				putusersListOnTable(uList);
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton editButton = new JButton("状态管理");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sataeMagning();
						
					}
				});
				{
					JLabel label = new JLabel("检索用户：");
					label.setFont(new Font("宋体", Font.PLAIN, 20));
					buttonPane.add(label);
				}
				{
					textField = new JTextField();
					textField.setFont(new Font("宋体", Font.PLAIN, 20));
					textField.setColumns(10);
					buttonPane.add(textField);
				}
				{
					JButton searchButton = new JButton("搜一搜");
					searchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							searchUser();
							
						}
					});
					searchButton.setFont(new Font("等线", Font.PLAIN, 18));
					buttonPane.add(searchButton);
				}
				{
					JLabel label = new JLabel("                                                ");
					label.setFont(new Font("宋体", Font.PLAIN, 15));
					buttonPane.add(label);
				}
				editButton.setFont(new Font("楷体", Font.PLAIN, 25));
				buttonPane.add(editButton);
			}
			{
				JButton editButton = new JButton("借阅记录");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ManageBorrowing();
						
					}

					
				});
				
				putTypeOnSel(typeList);
				editButton.setFont(new Font("楷体", Font.PLAIN, 25));
				buttonPane.add(editButton);
			}
		}
	}

	public void putusersListOnTable(List<user> uList) {
		
			
			user uer;
			tableModel.setRowCount(0);
			for (int i = 0; i < uList.size(); i++) {
				uer = uList.get(i);
//				"ID","用户名","简介","借阅记录","注册日期","用户状态"
				String[] rowValues = { uer.getUserId(),uer.getUserName(),uer.getAboutMe(),
						uer.getUserRecord(),uer.getSignDate(),uer.getuState() };
				tableModel.addRow(rowValues);

			}	
	}

}
