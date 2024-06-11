package com.Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.management.StringValueExp;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.ImplService.BooksDAOImpl;
import com.library.Book;

import javax.swing.ListSelectionModel;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class bookList extends JFrame {
	static JTable table;
	static DefaultTableModel tableModel; 
	static List<Book> list = BooksDAOImpl.getBookList();;
	static List<Book> typeColList = new ArrayList<Book>() ;
	private JTextField textField;
	static bookList dialog;
	static JComboBox comboBox;
	JButton delNewBookButton;
	JButton editButton;
	
	List typeList;
	List searchList;
	List listSel; //功能之间用于展示的表
	
	static Vector vCol= new Vector<String>();
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
	

	public static void putTypeOnSel(List typeList) {
		
//			comboBox.removeAllItems();
			vCol.clear();
//			comboBox.addItem("全部书籍");
			vCol.add("全部书籍");
			typeList = BooksDAOImpl.getTypeList();
			for (int i = 0; i < typeList.size(); i++) {
				vCol.add(typeList.get(i));
//				comboBox.addItem((typeList.get(i)).toString());
				System.out.println("东西为：" + vCol.get(i));
			}
			ComboBoxModel aModel =new DefaultComboBoxModel(vCol);
			comboBox.setModel(aModel);
			comboBox.setSelectedIndex(0);
			System.out.println("1："+comboBox.getItemAt(0));
	}
	
	public void showListByChoice() {
		String s = (String) comboBox.getSelectedItem();
		System.out.println("S内容:"+s);
		System.out.println("选中是否为空："+s.equalsIgnoreCase(null));
		if(s.equalsIgnoreCase(null)) {
			comboBox.setSelectedItem("全部书籍");
			s="全部书籍";
		}
		System.out.println("S2内容:"+s);
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

	public static void putListOnTable(List list) {
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
	
	public void removeActionListeners(JButton bt)
	{
	    ActionListener[] listeners = bt.getActionListeners();
	    for(ActionListener listener : listeners) {
	        	bt.removeActionListener(listener);
	    }
	}
	
	public void editSelectedBook() {
		 int selectedRow = table.getSelectedRow();//获得选中行的索引
         if(selectedRow!= -1) { //是否存在选中行
        	 
        	 booksEditor bE = new booksEditor();
				bE.setVisible(true);
				bE.addBkButton.setVisible(false);
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
				
				bE.getSelectedRow = selectedRow;
				
				removeActionListeners(bE.emptyButton);
				bE.emptyButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						bE.nametextField.setText(bk.getBookname());
						bE.amounttextField.setText(a);
						bE.typetextField.setText(bk.getBooktype());
						bE.authortextField.setText(bk.getAuthor());
						bE.placetextField.setText(bk.getEditPlace());
						bE.aboutTextArea.setText(bk.getAbout());
						bE.datepick.getInnerTextField().setText(bk.getDate());
						
					}
				});
				
         }
		
		
	}
	
	public void delSelectedBook() {
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) { // 是否存在选中行
			Book bk = (Book) listSel.get(selectedRow);
			int n = JOptionPane.showConfirmDialog(null, "确认删除书籍  《" + bk.getBookname() + " 》所有相关信息?", "删除书本",
					JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
			if (n == 0) {
				System.out.println("——————————删除操作——————————");
				BooksDAOImpl.delBookbyID(bk.getBookid());
				list.remove(selectedRow);
				tableModel.removeRow(selectedRow); // 删除行
				
//				typeList= BooksDAOImpl.getTypeList();
//				list = BooksDAOImpl.getBookList();
				putTypeOnSel(typeList);
				JOptionPane.showMessageDialog(this, "删除书籍  《" + bk.getBookname() + " 》成功√", "Success",
						JOptionPane.NO_OPTION);
			}

		}
		
		
	}
	
	public void searchBook() {
		String info = textField.getText();
		searchList = BooksDAOImpl.getSearchedBookListByName(info);
		if(searchList.isEmpty()) {
			tableModel.setRowCount(0);
			String[] rowValues = {"暂无相关书籍"}; 
			tableModel.addRow(rowValues);
			delNewBookButton.setEnabled(false);
			editButton.setEnabled(false);
			
		}else {
			delNewBookButton.setEnabled(true);
			editButton.setEnabled(true);
			listSel = searchList;
			tableModel.setRowCount(0);
			putListOnTable(searchList);
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public bookList() {
		setTitle("书库维护");
		setBounds(100, 100, 1027, 503);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton editButton_1 = new JButton("数据统计");
				editButton_1.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
				editButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showSpreadPict();
					}

					private void showSpreadPict() {
						showChart sc = new showChart();
						sc.setVisible(true);
					}
				});
				editButton_1.setFont(new Font("等线", Font.PLAIN, 20));
				buttonPane.add(editButton_1);
			}
			{
				JLabel label = new JLabel("检索图书：");
				label.setFont(new Font("宋体", Font.PLAIN, 20));
				buttonPane.add(label);
			}
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
				editButton = new JButton("修改");
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
				delNewBookButton = new JButton("删除图书");
				delNewBookButton.setForeground(Color.WHITE);
				delNewBookButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
				delNewBookButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delSelectedBook();
						
					}
				});
				delNewBookButton.setBackground(Color.RED);
				delNewBookButton.setFont(new Font("等线", Font.PLAIN, 18));
				buttonPane.add(delNewBookButton);
			}
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
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			scrollPane.setViewportView(table);
			
			 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //单选
			 
		}
		{
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delNewBookButton.setEnabled(true);
					editButton.setEnabled(true);
					showListByChoice();
				}
			});
			
			putTypeOnSel(typeList);
			comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			getContentPane().add(comboBox, BorderLayout.NORTH);
		}
	}

}
