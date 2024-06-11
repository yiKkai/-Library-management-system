package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.users.user;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import com.DAO.BooksDAO;
import com.ImplService.BooksDAOImpl;
import com.eltima.components.ui.DatePicker;
import com.library.Book;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import java.text.Format;


public class booksEditor extends JFrame {

	private JPanel contentPane;
	
	static JTextField typetextField;
	static JTextField nametextField;
	static JTextField IDtextField;
	static JTextField authortextField;
	static JFormattedTextField amounttextField;
	static JTextField placetextField;
	static JTextArea aboutTextArea;
	static JComboBox comboBox;
	static DatePicker datepick;
	
	
	//按钮类
	JButton addBkButton;
	JButton redButton;
	static int getSelectedRow;
	
	
	
	public static JTextArea getAboutTextArea() {
		return aboutTextArea;
	}


	public static JTextField getTypetextField() {
		return typetextField;
	}

	
	

	public static  DatePicker getDatepick() {
		return datepick;
	}


	public static JTextField getNametextField() {
		return nametextField;
	}



	public static JTextField getIDtextField() {
		return IDtextField;
	}



	public static JTextField getAuthortextField() {
		return authortextField;
	}



	public static JFormattedTextField getAmounttextField() {
		return amounttextField;
	}

	

	public static JTextField getPlacetextField() {
		return placetextField;
	}



	

	

	public static JComboBox getComboBox() {
		return comboBox;
	}

	

	booksEditor frame;
	
	String slected = "";
	
	String date="";
	String id;
	String name;
	String author;
	String publisher;
	String type;
	String about;
	int amount;
	List typeList;
	JButton emptyButton;
	
//	user uer = login.window.uer;
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booksEditor frame = new booksEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean isEmpty(String id,String name,String type) {
		if(id.equals("")||name.equals("")||type.equals("")) {
			return false;
		}
		return true;
	}
	
	
	public void putTypeOnSel(List typeList) {
//		System.out.println("界面层开始了");
		typeList = BooksDAOImpl.getTypeList();
		for(int i=0;i<typeList.size();i++) {
			comboBox.addItem((typeList.get(i)).toString());
		}
//		System.out.println("界面结束");
		
	}
	
	public void checkAndAddNewType() {
		String s = typetextField.getText();
		int co = comboBox.getItemCount();
		for(int i=0;i<co;i++) {
			String item = comboBox.getItemAt(i).toString();
			if(s.equalsIgnoreCase(item)) {
				break;
			}else {
				if(i==(co-1)) {
					System.out.println("新增图书类型为："+s);
					comboBox.addItem(s);
					bookList.comboBox.addItem(s);
				}
			}
		}
	}
	
	
	public void addBk() {
		id = IDtextField.getText();
		name = nametextField.getText();
		type = typetextField.getText();
		
		String a = amounttextField.getText();
		
		boolean point = isEmpty(id, name, type);
		boolean checkID = BooksDAOImpl.whetherBkIDisExist(id);
		if(point) {
			int n = JOptionPane.showConfirmDialog(null, "确认新增书籍  《" + name + "》所有相关信息?", "新增书本",
					JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
		if (n == 0) {
			if(checkID) {
				BooksDAOImpl.insertBook();
				if(!(a.equals(""))) {
					amount = Integer.parseInt(a);
				}else {
					amount = 0;
				}
				Book bk =new Book(nametextField.getText(), typetextField.getText(), datepick.getText(),amount, authortextField.getText(),
						aboutTextArea.getText(), IDtextField.getText(), placetextField.getText());
				
				bookList.list.add(bk);
				String ad[]= {IDtextField.getText(),nametextField.getText(),authortextField.getText(),datepick.getText(),
						String.valueOf(amount),aboutTextArea.getText(),typetextField.getText(),placetextField.getText()};
				bookList.tableModel.addRow(ad);
				JOptionPane.showMessageDialog(this, "新增图书成功√","新增",JOptionPane.INFORMATION_MESSAGE);
				checkAndAddNewType();
//				bookList.dialog.putListOnTable();
			}else {
				JOptionPane.showMessageDialog(this, "编号为"+id+"的图书已存在！","编号重复",JOptionPane.WARNING_MESSAGE);
			}
		}
		}else {
			JOptionPane.showMessageDialog(this, "有必填项未填写,请确认有无疏漏信息！","提示",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public boolean compareEdit(Book n,Book o) {
		String s1= n.toString();
		String s2 = o.toString();
		if(s1.equalsIgnoreCase(s2)) {
			return false;
		}else {
			System.out.println("书籍信息变动");
			return true;
		}
	}
	
	public void changeBk() {
		String id= IDtextField.getText();
		String date=datepick.getText();
		String name= nametextField.getText();
		String author = authortextField.getText();
		String publisher = placetextField.getText();
		String type = typetextField.getText();
		String about= aboutTextArea.getText();
		int amount;
		String as= amounttextField.getText();
		if(!(as.equals(""))) {
			amount = Integer.parseInt(as);
		}else {
			amount = 0;
		}
		
		Book Bafter = new Book(name, type, date, amount, author, about, id, publisher); 
//		int select=bookList.table.getSelectedRow();
		Book before = bookList.list.get(getSelectedRow);
		boolean compare =compareEdit(Bafter,before); //信息是否更改？
		if(compare) {
			
			int n = JOptionPane.showConfirmDialog(null, "确认修改书籍id为：" + id + "的书籍相关信息?", "edit",
					JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
			if (n == 0) {
				BooksDAOImpl.editBooksByID(id);

				bookList.list.set(getSelectedRow, Bafter); //将更改后的信息置换
				
				bookList.table.removeAll();
				bookList.tableModel.setRowCount(0);
				bookList.putListOnTable(bookList.list);
//				bookList.table.setValueAt(IDtextField.getText(), getSelectedRow, 0); //更新图书表
//				bookList.table.setValueAt(nametextField.getText(), getSelectedRow, 1);
//				bookList.table.setValueAt(authortextField.getText(), getSelectedRow, 2);
//				bookList.table.setValueAt(datepick.getText(), getSelectedRow, 3);
//				bookList.table.setValueAt(amounttextField.getText(), getSelectedRow, 4);
//				bookList.table.setValueAt(aboutTextArea.getText(), getSelectedRow, 5);
//				bookList.table.setValueAt(typetextField.getText(), getSelectedRow, 6);
//				bookList.table.setValueAt(placetextField.getText(), getSelectedRow, 7);
				JOptionPane.showMessageDialog(this, "修改id为：" + id + "的书籍信息成功", "", JOptionPane.DEFAULT_OPTION);
				checkAndAddNewType(); 
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "无变动信息，无需修改！","不用改",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	
	
	
	public void testShu() {
		String value = amounttextField.getText();
		int v = Integer.parseInt(value);
	}
	
	/**
	 * Create the frame.
	 */
	public booksEditor() {
		setTitle("图书管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 1003);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		typetextField = new JTextField();
		typetextField.setToolTipText("*必填");
		typetextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		typetextField.setColumns(10);
		typetextField.setBounds(275, 153, 156, 46);
		contentPane.add(typetextField);
		
		nametextField = new JTextField();
		nametextField.setToolTipText("*必填");
		nametextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nametextField.setColumns(10);
		nametextField.setBounds(275, 314, 156, 46);
		contentPane.add(nametextField);
		
		IDtextField = new JTextField();
		IDtextField.setToolTipText("*必填");
		IDtextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		IDtextField.setColumns(10);
		IDtextField.setBounds(275, 234, 156, 46);
		contentPane.add(IDtextField);
		
		JLabel lblId = new JLabel("图书类别：");
		lblId.setForeground(new Color(240, 255, 240));
		lblId.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId.setBounds(130, 151, 162, 46);
		contentPane.add(lblId);
		
		JLabel accountlabel = new JLabel("书名：");
		accountlabel.setForeground(new Color(240, 255, 240));
		accountlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		accountlabel.setBounds(189, 314, 90, 42);
		contentPane.add(accountlabel);
		
		JLabel ssx = new JLabel("作者：");
		ssx.setForeground(new Color(240, 255, 240));
		ssx.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx.setBounds(189, 388, 90, 41);
		contentPane.add(ssx);
		
		redButton = new JButton("确认修改");
		redButton.setForeground(new Color(255, 250, 205));
		
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBk();
			}
		});
		redButton.setToolTipText("");
		redButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 33));
		redButton.setBounds(763, 795, 162, 71);
		redButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		contentPane.add(redButton);
		
		JLabel lblId_1 = new JLabel("图书编号：");
		lblId_1.setForeground(new Color(240, 255, 240));
		lblId_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId_1.setBounds(130, 232, 156, 46);
		contentPane.add(lblId_1);
		
		JLabel ssx_1 = new JLabel("数量：");
		ssx_1.setForeground(new Color(240, 255, 240));
		ssx_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1.setBounds(189, 470, 90, 41);
		contentPane.add(ssx_1);
		
		JLabel ssx_1_1 = new JLabel("出版社：");
		ssx_1_1.setForeground(new Color(240, 255, 240));
		ssx_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1.setBounds(159, 546, 120, 41);
		contentPane.add(ssx_1_1);
		
		JLabel ssx_1_1_1 = new JLabel("入库日期：");
		ssx_1_1_1.setForeground(new Color(240, 255, 240));
		ssx_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1.setBounds(130, 620, 177, 41);
		contentPane.add(ssx_1_1_1);
		
		JLabel ssx_1_1_1_1 = new JLabel("内容摘要：");
		ssx_1_1_1_1.setForeground(new Color(240, 255, 240));
		ssx_1_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1_1.setBounds(130, 686, 177, 41);
		contentPane.add(ssx_1_1_1_1);
		
		authortextField = new JTextField();
		authortextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		authortextField.setColumns(10);
		authortextField.setBounds(275, 388, 156, 46);
		contentPane.add(authortextField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 696, 305, 155);
		contentPane.add(scrollPane);
		
		aboutTextArea = new JTextArea();
		aboutTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		scrollPane.setViewportView(aboutTextArea);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typetextField.setText((comboBox.getSelectedItem()).toString());
			}
		});
		comboBox.setBounds(470, 145, 224, 60);
		putTypeOnSel(typeList);
		contentPane.add(comboBox);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		
		amounttextField=new JFormattedTextField(new java.text.DecimalFormat("#0"));
		amounttextField.addKeyListener(new java.awt.event.KeyAdapter() {
	           public void keyReleased(java.awt.event.KeyEvent evt) {
	               String old = amounttextField.getText();
	               JFormattedTextField.AbstractFormatter formatter = amounttextField.getFormatter();
	               if (!old.equals("")) { 
	                   if (formatter != null) {
	                       String str = amounttextField.getText();
	                       try {
	                           long page = (Long) formatter.stringToValue(str);
	                           amounttextField.setText(page + "");
	                       } catch (ParseException pe) {
	                    	   amounttextField.setText("0");//解析异常直接将文本框中值设置为1
	                       }
	                   }
	               }
	           }
	       });
		amounttextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		amounttextField.setColumns(10);
		amounttextField.setBounds(275, 470, 77, 46);
		contentPane.add(amounttextField);
		
		placetextField = new JTextField();
		placetextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		placetextField.setColumns(10);
		placetextField.setBounds(275, 545, 156, 46);
		contentPane.add(placetextField);
		
		
		
		datepick = new DatePicker((Date) null, "yyyy-MM-dd HH:mm:ss", new Font("Times New Roman", Font.BOLD, 18), new Dimension(177, 24));
		datepick.setBounds(275, 620, 177, 45);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		datepick.getInnerTextField().setText(formatter.format(date).toString());
		contentPane.add(datepick);
		
		
//		if(!(as.equals(""))) {
//			amount = Integer.parseInt(as);
//		}else {
//			amount = 0;
//		}
		
		
		
		addBkButton = new JButton("新增图书");
		addBkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBk();
			}
		});
		addBkButton.setToolTipText("点击新增");
		addBkButton.setForeground(new Color(204, 153, 204));
		addBkButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 33));
		addBkButton.setBounds(964, 798, 162, 71);
		contentPane.add(addBkButton);
		
		emptyButton = new JButton("重置");
		emptyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDtextField.setText("");
				nametextField.setText("");
				typetextField.setText("");
				amounttextField.setText("");
				placetextField.setText("");
				aboutTextArea.setText("");
				authortextField.setText("");
				
			}
		});
		emptyButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		emptyButton.setToolTipText("change");
		emptyButton.setForeground(Color.WHITE);
		emptyButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 34));
		emptyButton.setBounds(763, 701, 112, 65);
		contentPane.add(emptyButton);
		
		
	}
}
