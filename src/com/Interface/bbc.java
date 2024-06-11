package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import com.ImplService.BooksDAOImpl;
import com.library.Book;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTree;

public class bbc extends JFrame {

	private JPanel contentPane;
	private JTextField nametextField;
	private JTextField typetextField;
	private JTextField IDtextField;
	private JLabel ssx;
	private JLabel ssx_1;
	private JLabel ssx_1_1;
	private JTextField authortextField;
	private JFormattedTextField amounttextField;
	private JTextField publishtextField;
	private JTextField DatetextField;
	private JLabel ssx_1_2;
	private JLabel ssx_1_1_1;
	private JScrollPane scrollPane;
	private JTextField searchtextField;
	
	JTree bookTree;

	static List<Book> list = BooksDAOImpl.getBookList();
	static List BooktypeList;
	static List<Book> typeColList = new ArrayList<Book>();
	Vector v = new Vector();
	
	DefaultMutableTreeNode typeRootNode;
	DefaultMutableTreeNode bookNode;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					skr.pifu();
					bbc frame = new bbc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public bbc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1407, 939);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nametextField = new JTextField();
		nametextField.setToolTipText("*必填");
		nametextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nametextField.setColumns(10);
		nametextField.setBounds(554, 329, 156, 46);
		contentPane.add(nametextField);
		
		JLabel accountlabel = new JLabel("书名：");
		accountlabel.setForeground(Color.BLACK);
		accountlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		accountlabel.setBounds(468, 329, 90, 42);
		contentPane.add(accountlabel);
		
		typetextField = new JTextField();
		typetextField.setToolTipText("*必填");
		typetextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		typetextField.setColumns(10);
		typetextField.setBounds(554, 171, 156, 46);
		contentPane.add(typetextField);
		
		IDtextField = new JTextField();
		IDtextField.setToolTipText("*必填");
		IDtextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		IDtextField.setColumns(10);
		IDtextField.setBounds(554, 252, 156, 46);
		contentPane.add(IDtextField);
		
		JLabel lblId = new JLabel("图书类别：");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId.setBounds(409, 169, 162, 46);
		contentPane.add(lblId);
		
		JLabel lblId_1 = new JLabel("图书编号：");
		lblId_1.setForeground(Color.BLACK);
		lblId_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId_1.setBounds(409, 250, 156, 46);
		contentPane.add(lblId_1);
		
		ssx = new JLabel("作者：");
		ssx.setForeground(Color.BLACK);
		ssx.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx.setBounds(468, 399, 90, 41);
		contentPane.add(ssx);
		
		ssx_1 = new JLabel("数量：");
		ssx_1.setForeground(Color.BLACK);
		ssx_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1.setBounds(468, 468, 90, 41);
		contentPane.add(ssx_1);
		
		ssx_1_1 = new JLabel("出版社：");
		ssx_1_1.setForeground(Color.BLACK);
		ssx_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1.setBounds(438, 544, 120, 41);
		contentPane.add(ssx_1_1);
		
		authortextField = new JTextField();
		authortextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		authortextField.setColumns(10);
		authortextField.setBounds(554, 399, 156, 46);
		contentPane.add(authortextField);
		
		amounttextField = new JFormattedTextField((Format) null);
		amounttextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		amounttextField.setColumns(10);
		amounttextField.setBounds(554, 468, 77, 46);
		contentPane.add(amounttextField);
		
		publishtextField = new JTextField();
		publishtextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		publishtextField.setColumns(10);
		publishtextField.setBounds(554, 543, 156, 46);
		contentPane.add(publishtextField);
		
		DatetextField = new JTextField();
		DatetextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		DatetextField.setColumns(10);
		DatetextField.setBounds(554, 619, 185, 46);
		contentPane.add(DatetextField);
		
		ssx_1_2 = new JLabel("入库时间：");
		ssx_1_2.setForeground(Color.BLACK);
		ssx_1_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_2.setBounds(415, 620, 156, 41);
		contentPane.add(ssx_1_2);
		
		ssx_1_1_1 = new JLabel("内容摘要：");
		ssx_1_1_1.setForeground(Color.BLACK);
		ssx_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1.setBounds(393, 694, 177, 41);
		contentPane.add(ssx_1_1_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(538, 704, 305, 155);
		contentPane.add(scrollPane);
		
		JTextArea abouttextArea = new JTextArea();
		abouttextArea.setFont(new Font("宋体", Font.PLAIN, 24));
		scrollPane.setViewportView(abouttextArea);
		
		JLabel lblId_2 = new JLabel("图书检索：");
		lblId_2.setForeground(new Color(240, 255, 255));
		lblId_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId_2.setBounds(396, 89, 162, 46);
		contentPane.add(lblId_2);
		
		JButton borrowButton = new JButton("借阅");
		borrowButton.setToolTipText("");
		borrowButton.setForeground(new Color(0, 0, 139));
		borrowButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 40));
		borrowButton.setBounds(971, 729, 164, 86);
		contentPane.add(borrowButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 156, 241, 552);
		contentPane.add(scrollPane_1);
		
		bookTree = new JTree();
		bookTree.setForeground(new Color(0, 0, 0));
		bookTree.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTree.setBackground(new Color(0, 191, 255));
		scrollPane_1.setViewportView(bookTree);
		DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
	     Color color = new Color(0,204,255);
	           myRenderer.setBackgroundNonSelectionColor(color);
	           myRenderer.setBackgroundSelectionColor(new Color(140,140,140));
	           bookTree.setCellRenderer(myRenderer);
		
	           BooktypeList = BooksDAOImpl.getTypeList();
	   		
	   		bookTree.setModel(new DefaultTreeModel(
	   				new DefaultMutableTreeNode("所有书籍") {
	   					{
	   						String s;
	   						for(int i=0;i<BooktypeList.size();i++) {
	   							s = BooktypeList.get(i).toString();
	   							typeRootNode = new DefaultMutableTreeNode(s);
	   							for(int j=0;j<list.size();j++) {
	   								Book bk = list.get(j);
	   								if(bk.getBooktype().equalsIgnoreCase(s)) {
	   										bookNode = new DefaultMutableTreeNode(bk.getBookname());
	   										typeRootNode.add(bookNode);
	   										v.add(bookNode);
	   									}
	   									this.add(typeRootNode);
	   								}
	   							
	   						}	
	   					}
	   				}
	   			));
	           
	           
	           
	           
	           
		searchtextField = new JTextField();
		searchtextField.setToolTipText("*必填");
		searchtextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		searchtextField.setColumns(10);
		searchtextField.setBounds(538, 91, 156, 46);
		contentPane.add(searchtextField);
		
		JButton searchButton = new JButton("搜一搜");
		searchButton.setFont(new Font("等线", Font.PLAIN, 21));
		searchButton.setBounds(715, 95, 100, 42);
		contentPane.add(searchButton);
	}
}
