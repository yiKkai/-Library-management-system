package com.Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.ImplService.BooksDAOImpl;
import com.ImplService.UsersDAOImpl;
import com.eltima.components.ui.DatePicker;
import com.library.Book;
import com.users.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class booksBorrowing extends JFrame {
	
//	static booksBorrowing bb;
	
	JTree bookTree = new JTree();
	private JTextField nametextField;
	private JTextField authortextField;
	private JTextField publishtextField;
	private JTextField typetextField;
	private JTextField IDtextField;
	DatePicker datepick;
	JTextField amounttextField;
	JTextArea abouttextArea;
	JPanel contentPane;
	
	
	static List<Book> list = BooksDAOImpl.getBookList();
	static List AllBooktypeList = BooksDAOImpl.getTypeList();
	static List BooktypeList;
	static List<Book> typeColList = new ArrayList<Book>();
	Vector v = new Vector();
	
	Vector v1 = new Vector();
	ArrayList<String> searchType = new ArrayList();
	
	
	DefaultMutableTreeNode typeRootNode;
	DefaultMutableTreeNode bookNode;
	private JTextField searchtextField;
	
//public void setTreeModel(JTree t) {
//	
//	BooktypeList = BooksDAOImpl.getTypeList();
//	
//	bookTree.setModel(new DefaultTreeModel(
//			new DefaultMutableTreeNode("所有书籍") {
//				{
//					String s;
//					for(int i=0;i<BooktypeList.size();i++) {
//						s = BooktypeList.get(i).toString();
//						typeRootNode = new DefaultMutableTreeNode(s);
//						for(int j=0;j<list.size();j++) {
//							Book bk = list.get(j);
//							if(bk.getBooktype().equalsIgnoreCase(s)) {
//									bookNode = new DefaultMutableTreeNode(bk.getBookname());
//									typeRootNode.add(bookNode);
//								}
//								getContentPane().add(typeRootNode);
//							}
//						
//					}	
//				}
//			}
//		));
//	
//}
	private static ArrayList getSignalElement(ArrayList arr) {
		ArrayList temp = new ArrayList();
		Iterator it = arr.iterator();
		while (it.hasNext()) {
			String p = (String) it.next();

			if (!temp.contains(p)) {
				temp.add(p);
			}
		}
		return temp;
	}
	
	public void removeActionListeners(JTree tt)
	{
	    TreeSelectionListener[] listeners = tt.getTreeSelectionListeners();
	    for(TreeSelectionListener listener : listeners) {
	        	tt.removeTreeSelectionListener(listener);
	    }
	}
	
	public void getOriginBack() {
		v.clear();
		BooktypeList = BooksDAOImpl.getTypeList();
		list = BooksDAOImpl.getBookList();
		bookTree.setModel(null);
		bookTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("所有书籍") {
			{
				String s;
				for (int i = 0; i < BooktypeList.size(); i++) {
					s = BooktypeList.get(i).toString();
					typeRootNode = new DefaultMutableTreeNode(s);
					for (int j = 0; j < list.size(); j++) {
						Book bk = list.get(j);
						if (bk.getBooktype().equalsIgnoreCase(s)) {
							bookNode = new DefaultMutableTreeNode(bk.getBookname());
							typeRootNode.add(bookNode);
							v.add(bookNode);
						}
						add(typeRootNode);
					}

				}
			}
		}));

		removeActionListeners(bookTree);
		bookTree.setSelectionRow(0);
		
		bookTree.addTreeSelectionListener(new TreeSelectionListener() {
			// 当条目选中变化后，这个方法会执行
			public void valueChanged(TreeSelectionEvent e) {
				// 得到当前选中的结点对象
				Object lastPathComponent = e.getPath().getLastPathComponent();

				String bn = null;
				for (int i = 0; i < v.size(); i++) {
					bookNode = (DefaultMutableTreeNode) v.get(i);
					if (bookNode.equals(lastPathComponent)) {
						bn = bookNode.toString(); // 好好玩
						System.out.println(bn);
						break;
					}
				}

				Book bk1 = null;
				for (int j = 0; j < list.size(); j++) {
					bk1 = list.get(j);
					if (bk1.getBookname().equalsIgnoreCase(bn)) {
						break;
					}
				}

				typetextField.setText(bk1.getBookname());
				nametextField.setText(bk1.getBookname());
				typetextField.setText(bk1.getBooktype());
				IDtextField.setText(bk1.getBookid());
				datepick.getInnerTextField().setText(bk1.getDate());
				amounttextField.setText(String.valueOf(bk1.getAmount()));
				abouttextArea.setText(bk1.getAbout());
				authortextField.setText(bk1.getAuthor());
				publishtextField.setText(bk1.getPlace());

			}
		});

	}
	
	
	
	public void searchBook() {
		String info = searchtextField.getText();
		List<Book> searchList = BooksDAOImpl.getSearchedBookListByName(info);
		//找到的相关书籍
		
		if (info.equals("")) {
			getOriginBack();
		} else {
			
			System.out.println("Type是否为空："+searchList.isEmpty());
			if(searchList.isEmpty()) {	
				searchList.forEach(System.out::println);
				bookTree.setModel(null);
				bookTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("找不到相关书籍") {
					{
						String s="";
						typeRootNode = new DefaultMutableTreeNode(s);
						bookNode = new DefaultMutableTreeNode("");
						
						add(typeRootNode);
					}
				}));

				typetextField.setText("");
				nametextField.setText("");
				typetextField.setText("");
				IDtextField.setText("");
				datepick.getInnerTextField().setText("");
				amounttextField.setText("");
				abouttextArea.setText("");
				authortextField.setText("");
				publishtextField.setText("");
				
				removeActionListeners(bookTree);
			}else {
				
				//需要将TYPE分出来
				v1.clear();
				searchType.clear();
				String s;
				Book bk;
				String Type;
				
				for (int i = 0; i < searchList.size(); i++) {
					bk = searchList.get(i);
					Type = bk.getBooktype();
					searchType.add(Type);
				}
				
				searchType = getSignalElement(searchType);
				searchType.forEach(System.out::println);
//				System.out.println("空？"+searchType.isEmpty());
				
				
				bookTree.setModel(null);
				bookTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("查询书籍") {
					{
						
						String s;
						Book bk;
						for (int i = 0; i < searchType.size(); i++) {
//							bk = searchList.get(i);
							s = searchType.get(i).toString();
							typeRootNode = new DefaultMutableTreeNode(s);
							for(int j=0;j<searchList.size();j++) {
								bk = searchList.get(j);
								
								if (bk.getBooktype().equalsIgnoreCase(s)) {
									bookNode = new DefaultMutableTreeNode(bk.getBookname());
									typeRootNode.add(bookNode);
									v1.add(bookNode);
								}
									add(typeRootNode);
							}
							
							

						}
					}
				}));
				
				removeActionListeners(bookTree);
				bookTree.addTreeSelectionListener(new TreeSelectionListener() {
					// 当条目选中变化后，这个方法会执行
					public void valueChanged(TreeSelectionEvent e) {
						// 得到当前选中的结点对象
						Object lastPathComponent = e.getPath().getLastPathComponent();

						String bn = null;
						for (int i = 0; i < v1.size(); i++) {
							bookNode = (DefaultMutableTreeNode) v1.get(i);
							if (bookNode.equals(lastPathComponent)) {
								bn = bookNode.toString(); // 好好玩
								System.out.println(bn);
								break;
							}
						}

						//为了显示
						Book bk1 = null;
						for (int j = 0; j < searchList.size(); j++) {
							bk1 = searchList.get(j);
							if (bk1.getBookname().equalsIgnoreCase(bn)) {
								break;
							}
						}

						typetextField.setText(bk1.getBookname());
						nametextField.setText(bk1.getBookname());
						typetextField.setText(bk1.getBooktype());
						IDtextField.setText(bk1.getBookid());
						datepick.getInnerTextField().setText(bk1.getDate());
						amounttextField.setText(String.valueOf(bk1.getAmount()));
						abouttextArea.setText(bk1.getAbout());
						authortextField.setText(bk1.getAuthor());
						publishtextField.setText(bk1.getPlace());

					}
				});
			}
			
			
		}
	}

	public void borrowBook() {
		user uer = login.window.uer;
		String name = uer.getUserName();
		String id = uer.getUserId();
		String ownedBk = uer.getuOwned();
		String shu = amounttextField.getText();
		int amount = Integer.parseInt(shu);
		if (amount <= 0) {
			JOptionPane.showMessageDialog(this, "该书目前库存为0,无法借阅！", "", JOptionPane.NO_OPTION);
		} else {
//				if(!ownedBk.equals("")) {
					
				
			boolean isContain = ownedBk.contains(nametextField.getText()); // 是否持有该书
			if (isContain) {
				JOptionPane.showMessageDialog(this, "已借阅该书，无法再次借阅 请先归还！", "", JOptionPane.NO_OPTION);
			} else {
				int n = JOptionPane.showConfirmDialog(null, "是否借阅书籍  《" + nametextField.getText() + "》?", "书籍借阅",
						JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
				if (n == 0) {
					String newOwned = uer.getuOwned() + nametextField.getText();
					login.window.uer.setuOwned(newOwned);
					login.window.uer.setBrtimes((login.window.uer.getBrtimes()+1));
					amount = amount - 1;
					amounttextField.setText(String.valueOf(amount));
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//					Date curdate = new Date(datepick.getInnerTextField().getText());
//					String date = formatter.format(curdate).toString();
					String date =datepick.getInnerTextField().getText();
					String record = "用户" + name + "(ID为：" + id + ")于" + date + "借走图书《" + nametextField.getText()
							+ "》(编号为" + IDtextField.getText() + ")书籍一本";
					String newRe = uer.getUserRecord()+record;
					uer.setUserRecord(newRe);
					String borrower = name + "(ID:" + id + ")";
					UsersDAOImpl.borrowingBook(record, id, nametextField.getText(), borrower, date);
					BooksDAOImpl.getBookBorrowed(IDtextField.getText());

					JOptionPane.showMessageDialog(this, "借书成功√,本次借的书籍为：《" + nametextField.getText() + "》", "Success",
							JOptionPane.NO_OPTION);

				}
			}
//		}
		}

	}
	
	
	public static void main(String[] agrs)
    {
    	skr.pifu();
        
    	booksBorrowing bb = new booksBorrowing();
        
        bb.setSize(1200,2000);
        bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        bb.setVisible(true);
    }
	
	
	
	public booksBorrowing() {
		setTitle("借书系统");
		this.getContentPane().setBackground(new Color(51, 204, 255));
		this.setBackground(new Color(135, 206, 250));
		setBackground(new Color(175, 238, 238));
		
		
		setBounds(100, 100, 1407, 939);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton borrowButton = new JButton("借阅");
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrowBook();
				
			}
		});
		borrowButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		borrowButton.setBounds(930, 749, 164, 86);
		borrowButton.setToolTipText("");
		borrowButton.setForeground(new Color(0, 0, 139));
		borrowButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 40));
		getContentPane().add(borrowButton);
		
		nametextField = new JTextField();
		nametextField.setEditable(false);
		nametextField.setToolTipText("*必填");
		nametextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nametextField.setColumns(10);
		nametextField.setBounds(487, 287, 156, 46);
		getContentPane().add(nametextField);
		
		JLabel accountlabel = new JLabel("书名：");
		accountlabel.setForeground(Color.BLACK);
		accountlabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		accountlabel.setBounds(401, 287, 90, 42);
		getContentPane().add(accountlabel);
		
		JLabel ssx = new JLabel("作者：");
		ssx.setForeground(Color.BLACK);
		ssx.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx.setBounds(401, 361, 90, 41);
		getContentPane().add(ssx);
		
		JLabel ssx_1 = new JLabel("数量：");
		ssx_1.setForeground(Color.BLACK);
		ssx_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1.setBounds(401, 430, 90, 41);
		getContentPane().add(ssx_1);
		
		JLabel ssx_1_1 = new JLabel("出版社：");
		ssx_1_1.setForeground(Color.BLACK);
		ssx_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1.setBounds(371, 506, 120, 41);
		getContentPane().add(ssx_1_1);
		
		authortextField = new JTextField();
		authortextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		authortextField.setColumns(10);
		authortextField.setBounds(487, 361, 156, 46);
		getContentPane().add(authortextField);
		
		amounttextField = new JTextField();
		amounttextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		amounttextField.setEditable(false);
		amounttextField.setColumns(10);
		amounttextField.setBounds(487, 430, 77, 46);
		getContentPane().add(amounttextField);
		
		publishtextField = new JTextField();
		publishtextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		publishtextField.setColumns(10);
		publishtextField.setBounds(487, 505, 156, 46);
		getContentPane().add(publishtextField);
		
		datepick = new DatePicker((Date) null, "yyyy-MM-dd HH:mm:ss", new Font("Times New Roman", Font.BOLD, 18), new Dimension(177, 24));
		datepick.getInnerTextField().setText("");
		datepick.setBounds(487, 594, 177, 45);
		getContentPane().add(datepick);
		
		JLabel ssx_1_1_1 = new JLabel("借阅日期：");
		ssx_1_1_1.setForeground(Color.BLACK);
		ssx_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1.setBounds(345, 594, 177, 41);
		getContentPane().add(ssx_1_1_1);
		
		JLabel ssx_1_1_1_1 = new JLabel("内容摘要：");
		ssx_1_1_1_1.setForeground(Color.BLACK);
		ssx_1_1_1_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		ssx_1_1_1_1.setBounds(338, 671, 177, 41);
		getContentPane().add(ssx_1_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(483, 681, 305, 155);
		getContentPane().add(scrollPane);
		
		abouttextArea = new JTextArea();
		abouttextArea.setFont(new Font("微软雅黑 Light", Font.PLAIN, 22));
		scrollPane.setViewportView(abouttextArea);
		
		typetextField = new JTextField();
		typetextField.setToolTipText("*必填");
		typetextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		typetextField.setColumns(10);
		typetextField.setBounds(487, 129, 156, 46);
		getContentPane().add(typetextField);
		
		IDtextField = new JTextField();
		IDtextField.setEditable(false);
		IDtextField.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		IDtextField.setColumns(10);
		IDtextField.setBounds(487, 210, 156, 46);
		getContentPane().add(IDtextField);
		
		JLabel lblId = new JLabel("图书类别：");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId.setBounds(342, 127, 162, 46);
		getContentPane().add(lblId);
		
		JLabel lblId_1 = new JLabel("图书编号：");
		lblId_1.setForeground(Color.BLACK);
		lblId_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
		lblId_1.setBounds(342, 208, 156, 46);
		getContentPane().add(lblId_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 145, 225, 494);
		getContentPane().add(scrollPane_1);
		
		getOriginBack();
		
		bookTree.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTree.setBackground(new Color(0, 191, 255));
		scrollPane_1.setViewportView(bookTree);
		
		DefaultTreeCellRenderer myRenderer = new DefaultTreeCellRenderer();
	     Color color = new Color(0,204,255);
	           myRenderer.setBackgroundNonSelectionColor(color);
	           myRenderer.setBackgroundSelectionColor(new Color(140,140,140));
	           bookTree.setCellRenderer(myRenderer);
	           
	           JLabel lblId_2 = new JLabel("图书检索：");
	           lblId_2.setForeground(new Color(240, 255, 255));
	           lblId_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 30));
	           lblId_2.setBounds(692, 127, 162, 46);
	           getContentPane().add(lblId_2);
	           
	           searchtextField = new JTextField();
	           searchtextField.setToolTipText("*必填");
	           searchtextField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
	           searchtextField.setColumns(10);
	           searchtextField.setBounds(834, 129, 156, 46);
	           getContentPane().add(searchtextField);
	           
	           JButton searchButton = new JButton("搜一搜");
	           searchButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		searchBook();
	           		
	           	}
	           });
	           searchButton.setFont(new Font("等线", Font.PLAIN, 21));
	           searchButton.setBounds(1011, 133, 100, 42);
	           getContentPane().add(searchButton);
	           
	           JButton OButton = new JButton("图书列表");
	           OButton.addActionListener(new ActionListener() {
	           	public void actionPerformed(ActionEvent e) {
	           		getOriginBack();
	           		
	           	}
	           });
	           OButton.setFont(new Font("等线", Font.PLAIN, 19));
	           OButton.setBounds(109, 687, 132, 46);
	           getContentPane().add(OButton);
		
	           


	}
}
