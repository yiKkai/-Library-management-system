package com.Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class historybrRecord extends JFrame {
	private JTable table;
	static DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			historybrRecord dialog = new historybrRecord();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public historybrRecord() {
		setTitle("借阅记录");
		setBounds(100, 100, 1056, 468);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JComboBox comboBox = new JComboBox();
			getContentPane().add(comboBox, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				
				String[] columnNames = {"历史借阅记录"};   //列名
				tableModel = new DefaultTableModel();
				tableModel.setColumnIdentifiers(columnNames);
				table = new JTable(tableModel);
				table.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
			    table.setRowHeight(55);//指定每一行的行高50
				scrollPane.setViewportView(table);
			}
		}
	}

}
