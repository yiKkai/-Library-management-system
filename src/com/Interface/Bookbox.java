package com.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.ImplService.BooksDAOImpl;
import com.library.Book;


public class Bookbox extends Box{
	JFrame jf = null;
       
	public Bookbox(JFrame jf) {
		 //垂直布局
        super(BoxLayout.Y_AXIS);
        //组装视图
        this.jf = jf;
        
		
			JPanel buttonPane = new JPanel();
			
				JLabel label = new JLabel("检索图书：");
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				buttonPane.add(label);
			
			{
				textField = new JTextField();
				textField.setFont(new Font("宋体", Font.PLAIN, 20));
				buttonPane.add(textField);
				textField.setColumns(10);
			}
			{
				JButton searchButton = new JButton("搜一搜");
				searchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchBook();
					}
				});
				searchButton.setFont(new Font("等线", Font.PLAIN, 18));
				buttonPane.add(searchButton);
			}
			{
				JLabel lblNewLabel = new JLabel("            ");
				lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
				buttonPane.add(lblNewLabel);
			}
			{
				JButton editButton = new JButton("修改");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						editSelectedBook();
						
					}
				});
				editButton.setFont(new Font("等线", Font.PLAIN, 20));
				buttonPane.add(editButton);
			}
			{
				JButton addNewBookButton = new JButton("新增图书");
				addNewBookButton.setBackground(Color.GREEN);
				addNewBookButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						booksEditor bE = new booksEditor();
						bE.setVisible(true);
						bE.redButton.setEnabled(false);
						bE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
				});
				addNewBookButton.setFont(new Font("等线", Font.PLAIN, 18));
				buttonPane.add(addNewBookButton);
			}
			{
				JButton delNewBookButton = new JButton("删除图书");
				delNewBookButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delSelectedBook();
						
					}
				});
				delNewBookButton.setBackground(Color.RED);
				delNewBookButton.setFont(new Font("等线", Font.PLAIN, 18));
				buttonPane.add(delNewBookButton);
			}
		
		{
			 String[] columnNames = {"图书编号","书名","作者","入库日期","数量","简介摘要","类型","出版社"};   //列名
			 tableModel = new DefaultTableModel();
			 tableModel.setColumnIdentifiers(columnNames);
		     table = new JTable(tableModel);
		     table.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		     table.setRowHeight(55);//指定每一行的行高50
		     
			JScrollPane scrollPane = new JScrollPane(table);
			putListOnTable(list);
			
			
			scrollPane.setViewportView(table);
			
			 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //单选
			 
		}
		{
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showListByChoice();
				}
			});
			
			putTypeOnSel(typeList);
			comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			
		}
        	
	}
	
	static JTable table;
	static DefaultTableModel tableModel; 
	static List<Book> list = BooksDAOImpl.getBookList();;
	static List<Book> typeColList = new ArrayList<Book>() ;
	private JTextField textField;
	static bookList dialog;
	static JComboBox comboBox;
	
	List typeList;
	List searchList;
	List listSel; //功能之间用于展示的表
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		skr.pifu();
		try {
			dialog = new bookList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void putTypeOnSel(List typeList) {
//		System.out.println("界面层开始了");
		comboBox.removeAllItems();
		comboBox.addItem("全部书籍");
		typeList = BooksDAOImpl.getTypeList();
		for(int i=0;i<typeList.size();i++) {
			comboBox.addItem((typeList.get(i)).toString());
		}
//		System.out.println("界面结束");
		
	}
	
	public void showListByChoice() {
		String s = (String) comboBox.getSelectedItem();
		if(s.equals("全部书籍")) {
			listSel = list;
			putListOnTable(list);
		}else {
			typeColList.clear();
			
			for(int i=0;i<list.size();i++) {
			if(list.get(i).getBooktype().equalsIgnoreCase(s)) {
				typeColList.add(list.get(i));
				}
			}
			
			listSel = typeColList;
			
			int shu;
			Book bk;
			tableModel.setRowCount(0);
			table.removeAll();
			
			for (int j = 0; j < typeColList.size(); j++) {
				bk = typeColList.get(j);
				shu = bk.getAmount();

				String[] rowValues = { bk.getBookid(), bk.getBookname(), bk.getAuthor(), bk.getDate(), String.valueOf(shu),
						bk.getAbout(), bk.getBooktype(), bk.getEditPlace() };
				tableModel.addRow(rowValues);
				

			}
		}
		
		
	}

	public void putListOnTable(List list) {
		int shu;
		Book bk;
		tableModel.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			bk = (Book) list.get(i);
			shu = bk.getAmount();

			String[] rowValues = { bk.getBookid(), bk.getBookname(), bk.getAuthor(), bk.getDate(), String.valueOf(shu),
					bk.getAbout(), bk.getBooktype(), bk.getEditPlace() };
			tableModel.addRow(rowValues);

		}

	}
	
	public void editSelectedBook() {
		 int selectedRow = table.getSelectedRow();//获得选中行的索引
         if(selectedRow!= -1) { //是否存在选中行
        	 
        	 booksEditor bE = new booksEditor();
				bE.setVisible(true);
				bE.addBkButton.setEnabled(false);
				bE.redButton.setEnabled(true);
				bE.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				Book bk = (Book) listSel.get(selectedRow);
				int shu = bk.getAmount();
				String a =String.valueOf(shu); //转为字符
				bE.nametextField.setText(bk.getBookname());
				bE.amounttextField.setText(a);
				bE.typetextField.setText(bk.getBooktype());
				bE.authortextField.setText(bk.getAuthor());
				bE.IDtextField.setText(bk.getBookid());
				bE.IDtextField.setEditable(false);
				bE.placetextField.setText(bk.getEditPlace());
				bE.aboutTextArea.setText(bk.getAbout());
				bE.datepick.getInnerTextField().setText(bk.getDate());
				
         }
		
		
	}
	
	public void delSelectedBook() {
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) { // 是否存在选中行
			Book bk = (Book) listSel.get(selectedRow);
			int n = JOptionPane.showConfirmDialog(null, "确认删除书籍  《" + bk.getBookname() + " 》所有相关信息?", "删除书本",
					JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
			if (n == 0) {

				BooksDAOImpl.delBookbyID(bk.getBookid());
				list.remove(selectedRow);
				tableModel.removeRow(selectedRow); // 删除行
				
				putTypeOnSel(typeList);
				JOptionPane.showMessageDialog(this, "删除书籍  《" + bk.getBookname() + " 》成功√", "Success",
						JOptionPane.NO_OPTION);
			}

		}
		
		
	}
	
	public void searchBook() {
		String info = textField.getText();
		if(info.equals("")) {
//			tableModel.setRowCount(0);
			
		}else {
			searchList = BooksDAOImpl.getSearchedBookListByName(info);
			listSel = searchList;
			tableModel.setRowCount(0);
			putListOnTable(searchList);
		}
	}
	

	

}
