package com.Interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.TextAnchor;

import com.ImplService.BooksDAOImpl;
import com.ImplService.UsersDAOImpl;
import com.library.Book;
import com.users.user;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

public class showChart extends JFrame {

	Canvas imageCanva;
	JToolBar toolBar;

	
	
	public void setTheme() {
		// 创建主题样式 (可以解决中文乱码问题)
				StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
				// 设置标题字体
				standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 25));
				// 设置图例的字体
				standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 20));
				// 设置轴向的字体
				standardChartTheme.setLargeFont(new Font("黑体", Font.PLAIN, 40));
				// 应用主题样式
				ChartFactory.setChartTheme(standardChartTheme);
	}
	
	public static void main(String[] args) {
		skr.pifu();
		try {
			showChart dialog = new showChart();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CategoryDataset GetDataset() // 获取数据集
	{
		List dList = UsersDAOImpl.getAllborrowDate();
		List list = (List) dList.get(0);
		List coList = (List) dList.get(1);

		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.size(); i++) {
			long v = (long) coList.get(i);
			mDataset.addValue(v, "借阅变动情况", list.get(i).toString());
		}
		return mDataset;
	}

	public void drawZhe() {
		setTheme();
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart3D("借阅书次日期", // 图名字
				"日期", // 横坐标
				"借阅书籍总数量", // 纵坐标
				mDataset, // 数据集
				PlotOrientation.VERTICAL, true, // 显示图例
				true, // 采用标准生成器
				false);// 是否生成超链接

		CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) mPlot.getRenderer();
		DecimalFormat decimalformat1 = new DecimalFormat("#0"); // 数据点显示数据值的格式
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(" {2} ", decimalformat1));
		// 上面这句是设置数据项标签的生成器
		renderer.setItemLabelsVisible(true); // 设置项标签显示
		renderer.setBaseItemLabelsVisible(true); // 基本项标签显示
		// 上面这几句就决定了数据点按照设定的格式显示数据值
		renderer.setShapesFilled(Boolean.TRUE); // 在数据点显示实心的小图标
		renderer.setShapesVisible(true); // 设置显示小图标
		renderer.setBaseItemLabelFont(new Font("Dialog", 1, 25));

		/*
		 * Y轴设置
		 */
		ValueAxis vn = mPlot.getRangeAxis();
		vn.setUpperMargin(1);
		vn.setLowerMargin(1);
		vn.setAutoRangeMinimumSize(1);// 最小跨度
		vn.setLowerBound(0);// 最小值显示
		vn.setLabelFont(new Font("宋书", Font.PLAIN, 25)); // 设置横轴字体
		vn.setTickLabelFont(new Font("宋书", Font.PLAIN, 25));// 设置坐标轴标尺值字体

		LineAndShapeRenderer lasp = (LineAndShapeRenderer) mPlot.getRenderer();// 获取显示线条的对象
		lasp.setBaseShapesVisible(true);// 设置拐点是否可见/是否显示拐点
		lasp.setDrawOutlines(true);// 设置拐点不同用不同的形状
		lasp.setUseFillPaint(true);// 设置线条是否被显示填充颜色
		lasp.setBaseFillPaint(Color.red);//// 设置拐点颜色

		/*
		 * X轴
		 */
		org.jfree.chart.axis.CategoryAxis domainAxis = mPlot.getDomainAxis();
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setLowerMargin(0);

		// domainAxis.setCategoryMargin(0.5);;
		// System.out.println(domainAxis.getCategoryMargin());;

		domainAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15)); // 设置横轴字体
		domainAxis.setTickLabelFont(new Font("宋书", Font.PLAIN, 15));// 设置坐标轴标尺值字体
		domainAxis.setLowerMargin(0.01);// 左边距 边框距离
		domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。

		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
		mPlot.setOutlinePaint(Color.RED);// 边界线
		mPlot.setDomainGridlinesVisible(true); // 显示横坐标格线
		mPlot.setRangeGridlinesVisible(true); // 显示纵坐标格线

		BufferedImage buff = mChart.createBufferedImage(imageCanva.getWidth(), imageCanva.getHeight());

		try {
			System.out.println("读取image成功");
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			imageCanva.getGraphics().drawImage(buff, 1, 1, this);

		} catch (Exception e) {
			System.out.println("画图ing....");
		}
	}

	public void showBookSpread() {
		// TODO 显示各类图书分布
		setTheme();
		// 构造数据
		DefaultPieDataset dataset = new DefaultPieDataset();
		List typeList = BooksDAOImpl.getTypeList();
		List<Book> booksList = BooksDAOImpl.getBookList();
		Vector amountList = new Vector<>();
		int a;
		for (int i = 0; i < typeList.size(); i++) {
			a = 0;
			String t = typeList.get(i).toString();
			for (int j = 0; j < booksList.size(); j++) {
				if (booksList.get(j).getBooktype().equalsIgnoreCase(t)) {
					a++;
				}
			}
			amountList.add(a);
		}

		for (int i = 0; i < typeList.size(); i++) {
			dataset.setValue(typeList.get(i).toString(), (Number) amountList.get(i));
		}

		JFreeChart chart = ChartFactory.createPieChart3D("图书分布", // 图表标题
				(PieDataset) dataset, true, // 是否显示图例
				false, false);
		PiePlot pieplot = (PiePlot) chart.getPlot(); // 通过JFreeChart 对象获得
		pieplot.setNoDataMessage("无数据可供显示！"); // 没有数据的时候显示的内容
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}: ({2})"), NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));

		BufferedImage buff = chart.createBufferedImage(imageCanva.getWidth(), imageCanva.getHeight());
		try {
			System.out.println("读取image成功");
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			imageCanva.getGraphics().drawImage(buff, 1, 1, this);

		} catch (Exception e) {
			System.out.println("画图ing....");
		}

	}
	
	private void readerStatic() {
		// TODO Auto-generated method stub
		setTheme();
//		List dList = UsersDAOImpl.getAllborrowTime();
//		List ulist = (List) dList.get(0);
//		List btList = (List) dList.get(1);
		List list = UsersDAOImpl.getAllborrowTime1();

		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		user uer;
		for (int i = 0; i < list.size(); i++) {
			uer = (user) list.get(i);
			int v = uer.getBrtimes();
			String s = uer.getUserName();
//			long v = (long) btList.get(i);
//			mDataset.addValue(v,ulist.get(i).toString(),ulist.get(i).toString());
			if(i==0) {
				mDataset.addValue(v,s,"最佳沉迷读者:"+s);
			}else {
				mDataset.addValue(v,s,s);
			}
			
		}
		
		
		JFreeChart chart = ChartFactory.createBarChart3D("最佳阅读者评比", "用户名","借阅量/ 次", mDataset,
				PlotOrientation.HORIZONTAL,
				true, false, false);
		
		CategoryPlot plot = chart.getCategoryPlot();//设置图的高级属性 
		BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
		   renderer.setBaseItemLabelsVisible(true); 
		   renderer.setBaseItemLabelPaint(Color.BLUE);//设置数值颜色，默认黑色 
			
			DecimalFormat decimalformat1 = new DecimalFormat("#0"); // 数据点显示数据值的格式
			renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(" {2} ", decimalformat1));
			renderer.setBaseItemLabelsVisible(true);
			renderer.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
			
			renderer.setBasePositiveItemLabelPosition(
					new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
			renderer.setItemLabelAnchorOffset(10);

			renderer.setBaseItemLabelFont(new Font("Dialog", 1, 30)); // 标签大小
			
		plot.setRenderer(renderer);
		
		
		
		BufferedImage buff = chart.createBufferedImage(imageCanva.getWidth(), imageCanva.getHeight());
		try {
			System.out.println("读取image成功");
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			imageCanva.getGraphics().drawImage(buff, 1, 1, this);

		} catch (Exception e) {
			System.out.println("画图ing....");
		}
		
		
		
	}

	
	private void clearReaderSort() {
		// TODO 6.6 清空借阅次数
		int n = JOptionPane.showConfirmDialog(null, "确认清空读者排行榜?", "清空排行榜",
				JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
		if (n == 0) {
			UsersDAOImpl.clearAllborrowTime();
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			JOptionPane.showMessageDialog(this, "清空记录成功√", "Success",
					JOptionPane.NO_OPTION);
			
		}
		
	}
	
	private void clearBookSort() {
		// TODO 6.6 清空热门书籍排行榜
		int n = JOptionPane.showConfirmDialog(null, "确认清空热门书籍排行榜?", "清空排行榜",
				JOptionPane.YES_NO_OPTION);// 返回的是按钮的index i=0或者1
		if (n == 0) {
			BooksDAOImpl.clearAllgetborrowTime();
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			JOptionPane.showMessageDialog(this, "清空记录成功√", "Success",
					JOptionPane.NO_OPTION);
		}
		
	}
	
//	6/6
	private void hotBookStatic() {
		// TODO Auto-generated method stub
		setTheme();
//		List hList = BooksDAOImpl.getHotbookList();
//		List blist = (List) hList.get(0);
//		List tList = (List) hList.get(1);
		List blist = BooksDAOImpl.getHotbookList1();
		
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		Book bk;
		for (int i = 0; i < blist.size(); i++) {
			bk = (Book) blist.get(i);
			int v = bk.getGetBorrowedTime();
			String name = bk.getBookname();
//			long v = (long) tList.get(i);
//			mDataset.addValue(v, "最热门书籍", blist.get(i).toString());
			if(i==0) {
				mDataset.addValue(v, "借阅最火爆书籍！！", name);
			}else {
				mDataset.addValue(v, "其他热门书籍", name);
			}
			
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D("最热门书籍评比", "书名","借阅次数/ 次", mDataset,
				PlotOrientation.VERTICAL,
				true, false, false);
		
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		BarRenderer3D renderer = new BarRenderer3D();// 3D属性修改
		
		DecimalFormat decimalformat1 = new DecimalFormat("#0"); // 数据点显示数据值的格式
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(" {2} ", decimalformat1));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		
		renderer.setBasePositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		renderer.setItemLabelAnchorOffset(10);

		// 将类型放到上面
		plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		
		// 将默认放到左边的数值放到右边
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		
		renderer.setBaseItemLabelFont(new Font("Dialog", 1, 30)); // 标签大小
		
		
		
		
		plot.setRenderer(renderer);// 将修改后的属性值保存到图中
		BufferedImage buff = chart.createBufferedImage(imageCanva.getWidth(), imageCanva.getHeight());
		try {
			System.out.println("读取image成功");
			imageCanva.getGraphics().clearRect(0, 0, imageCanva.getWidth(), imageCanva.getHeight());
			imageCanva.getGraphics().drawImage(buff, 1, 1, this);

		} catch (Exception e) {
			System.out.println("画图ing....");
		}
		
		
	}
	
	
	/**
	 * Create the dialog.
	 */
	public showChart() {
		setTitle("数据统计");
		setBounds(100, 100, 1500, 900);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("借阅日期统计");
				okButton.setFont(new Font("宋体", Font.PLAIN, 27));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawZhe();
					}

				});
				{
					JButton okButton_1 = new JButton("图书分布统计");
					okButton_1.setForeground(new Color(224, 255, 255));
					okButton_1.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
					okButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showBookSpread();
						}

					});
					{
						toolBar = new JToolBar("统计栏");
						buttonPane.add(toolBar);
						{
							JButton okButton_1_1 = new JButton("清空书籍排行榜");
							okButton_1_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									clearBookSort();
								}
							});
							okButton_1_1.setFont(new Font("宋体", Font.PLAIN, 27));
							okButton_1_1.setActionCommand("OK");
							toolBar.add(okButton_1_1);
						}
						{
							JButton okButton_1_1 = new JButton("清空读者排行榜");
							okButton_1_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									clearReaderSort();
								}
							});
							okButton_1_1.setFont(new Font("宋体", Font.PLAIN, 27));
							okButton_1_1.setActionCommand("OK");
							toolBar.add(okButton_1_1);
						}
					}
					{
						JButton okButton_1_1 = new JButton("读者排行榜");
						okButton_1_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								readerStatic();
							}

							
						});
						{
							JButton okButton_1_1_1 = new JButton("热门书籍排行榜");
							okButton_1_1_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									hotBookStatic();
								}

								
							});
							okButton_1_1_1.setForeground(Color.WHITE);
							okButton_1_1_1.setFont(new Font("宋体", Font.PLAIN, 27));
							okButton_1_1_1.setActionCommand("OK");
							okButton_1_1_1.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
							buttonPane.add(okButton_1_1_1);
						}
						okButton_1_1.setForeground(Color.DARK_GRAY);
						buttonPane.add(okButton_1_1);
						okButton_1_1.setFont(new Font("宋体", Font.PLAIN, 27));
						okButton_1_1.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
						okButton_1_1.setActionCommand("OK");
					}
					okButton_1.setFont(new Font("宋体", Font.PLAIN, 27));
					okButton_1.setActionCommand("OK");
					buttonPane.add(okButton_1);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			imageCanva = new Canvas();
			imageCanva.setFont(new Font("Arial", Font.BOLD, 20));
			imageCanva.setBackground(Color.GRAY);
			getContentPane().add(imageCanva, BorderLayout.CENTER);
		}
	}

}
