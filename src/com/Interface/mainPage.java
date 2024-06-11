package com.Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import com.ImplService.UsersDAOImpl;
import com.ImplService.Utils;
import com.library.Book;
import com.users.borrowRecording;
import com.users.user;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class mainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static mainPage frame;
	private JLabel Userwellabel;
	private JLabel UserwellabelName;
	private JButton changePswButton;
	private Panel panel;
	private mangePanel mpan;
	private usersPanel upan;
	
	String s= login.window.getUserText();
	
	user uer=login.window.uer;
	String type=uer.getUserType();

	//将界面中的空白 Panel 替换为 MPanel 1 
		private void replaceManPanel() { //替换成管理员面板
			Rectangle r = panel.getBounds();
			if( mpan == null) {
				panel.setVisible(false);
				mpan = new mangePanel();
				mpan.setBounds(r);
				contentPane.add(mpan);
				mpan.setVisible(true); 
			}
			mpan.setMain(this);
		}
		
		private void replacereaderPanel() { //读者面板
			Rectangle r = panel.getBounds();
			if( mpan == null) {
				panel.setVisible(false);
				upan = new usersPanel();
				upan.setBounds(r);
				contentPane.add(upan);
				upan.setVisible(true); 
			}
			upan.setMain(this);
		}
		
	
	public static void main(String[] args) {
		skr.pifu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new mainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void showdistribution() {
		showChart sc = new showChart();
		sc.setVisible(true);
		if(uer.getUserType().equals("读者")) {
			sc.toolBar.setVisible(false);
		}
		
	}
	
	
	public mainPage() {
		
		setTitle("主页面");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1708, 930);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton changeUerButton = new JButton("账号状态设置");
		
		changeUerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateChange stch = new stateChange();
				stch.setVisible(true);
//				frame.setVisible(false);
//				frame.dispose();
				stch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		changeUerButton.setToolTipText("注销、锁定、解冻");
		changeUerButton.setForeground(Color.WHITE);
		changeUerButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		changeUerButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		changeUerButton.setBounds(1489, 705, 190, 74);
		contentPane.add(changeUerButton);
		
		Userwellabel = new JLabel("欢迎登录,");
		Userwellabel.setForeground(Color.WHITE);
		Userwellabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 40));
		Userwellabel.setBounds(84, 34, 190, 77);
		contentPane.add(Userwellabel);
		
		
		UserwellabelName = new JLabel(uer.getUserName());
		UserwellabelName.setForeground(Color.WHITE);
		UserwellabelName.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 40));
		UserwellabelName.setBounds(268, 34, 247, 77);
		contentPane.add(UserwellabelName);
		
		changePswButton = new JButton("用户密码修改");
		changePswButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pswChange chGframe = new pswChange();
				chGframe.setVisible(true);
				chGframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String name= uer.getUserName();
				String id = uer.getUserId();
				chGframe.IDtext.setText(id);
				chGframe.nameText.setText(name);
				chGframe.nameText.setEditable(false);
				chGframe.IDtext.setEditable(false);
			}
		});
		changePswButton.setToolTipText("");
		changePswButton.setForeground(new Color(0, 204, 255));
		changePswButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		changePswButton.setBounds(1489, 805, 190, 74);
		contentPane.add(changePswButton);
		
		panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(34, 117, 1433, 619);
		contentPane.add(panel);
		
		JButton historyRecordButton = new JButton("历史借阅记录");
		historyRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showHistory();
				
			}

			private void showHistory() {
				historybrRecord hr  = new historybrRecord();
					System.out.println("历史借阅记录展示：");
					hr.setVisible(true);
					hr.tableModel.setRowCount(0);
					List history = UsersDAOImpl.getHistoryRecord();
					
					if(history.isEmpty()) {
						String[] rowValues = {"暂无历史记录"}; 
						hr.tableModel.addRow(rowValues);
					}else {
						for (int i = 0; i < history.size(); i++) {
						String br;
						br = (String) history.get(i);
						//"书名","借阅者","借阅日期","是否归还"
						String[] rowValues = {br};
						hr.tableModel.addRow(rowValues);
						}
					}
					
				
				
			}
		});
		historyRecordButton.setToolTipText("");
		historyRecordButton.setForeground(SystemColor.textHighlight);
		historyRecordButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		historyRecordButton.setBounds(1489, 610, 190, 74);
		contentPane.add(historyRecordButton);
		
		JButton bookdistribution = new JButton("数据统计");
		bookdistribution.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		bookdistribution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showdistribution();
			}
		});
		bookdistribution.setToolTipText("");
		bookdistribution.setForeground(new Color(176, 224, 230));
		bookdistribution.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 24));
		bookdistribution.setBounds(1149, 779, 190, 74);
		contentPane.add(bookdistribution);
		if(type.equalsIgnoreCase("管理员")) {
			replaceManPanel();
		}else {
			replacereaderPanel();
		}
	}
}
