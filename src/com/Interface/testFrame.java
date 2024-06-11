package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ImplService.BooksDAOImpl;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

public class testFrame extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox = new JComboBox();
	/**
	 * 借阅记录模块
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFrame frame = new testFrame();
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
	public testFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1513, 864);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		ComboBoxModel aModel =1;
//		comboBox.setModel(aModel);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s;
				
				s = (String) comboBox.getSelectedItem();
				try {
					
					System.out.println("S内容:"+s);
					System.out.println("选中是否为空："+s.equalsIgnoreCase(null));
				}catch (NullPointerException e1) {
					// TODO: handle exception
				}finally {
					comboBox.setSelectedItem("全部书籍");
					s = "2";
				}
				System.out.println("S2:"+s);
				if(s.equals("2")) {
					System.out.println("哈哈哈哈哈");
				}
				
			}
		});
		
		comboBox.removeAllItems();
	 
		System.out.println("combox");
	
		comboBox.addItem("全部书籍");
//		typeList = BooksDAOImpl.getTypeList();
		for (int i = 0; i < 5; i++) {
			comboBox.addItem("1");
			System.out.println("东西为：" + comboBox.getItemAt(i));
		}
		comboBox.setSelectedIndex(0);
		System.out.println("1："+comboBox.getItemAt(0));
		
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		comboBox.setBounds(179, 10, 468, 25);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		ComboBoxModel aModel =new DefaultComboBoxModel(new String[] {"sad", "def", "cee", "221"});
		comboBox_1.setModel(aModel);
//		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"sad", "def", "cee", "221"}));
		comboBox_1.setBounds(790, 32, 66, 32);
		contentPane.add(comboBox_1);
		
		Canvas imageCanva = new Canvas();
		imageCanva.setFont(new Font("Arial", Font.BOLD, 20));
		imageCanva.setBackground(Color.GRAY);
		imageCanva.setBounds(277, 84, 970, 284);
		contentPane.add(imageCanva);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(152, 462, 106, 44);
		contentPane.add(lblNewLabel);
		
		JSplitPane sp = new JSplitPane();
		sp.setBounds(704, 446, 458, 324);
		contentPane.add(sp);
		
		JScrollPane scrollPane = new JScrollPane();
		sp.setLeftComponent(scrollPane);
		
		JTree functree = new JTree((TreeNode) null);
		functree.setFont(new Font("宋体", Font.PLAIN, 20));
		functree.setBackground(new Color(0, 204, 255));
		scrollPane.setViewportView(functree);
		
		JLabel label = new JLabel("这里进行管理功能模块...");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		sp.setRightComponent(label);
	
}
}
