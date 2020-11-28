package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.*;
import javafx.beans.binding.ObjectExpression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTabbedPane;

public class PaintFrame extends JFrame {

	private final JPanel contentPane;
	private final JPanel chartpanel;//折线图
	private final JTabbedPane tabbedPane;

	static List<Object> list2=new ArrayList<>();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// 启用BeautyEye主题
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PaintFrame frame = new PaintFrame(list2);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PaintFrame(List<Object> list) {
		list2= new ArrayList<> (list);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, "name_1999208996577100");

		JPanel zhexianpanel = new JPanel();
		tabbedPane.addTab("\u6298\u7EBF\u56FE", null, zhexianpanel, null);
		zhexianpanel.setLayout(new CardLayout(0, 0));

		DefaultCategoryDataset ds=setDefaultCategoryDataset();//图表数据源调用（自定义函数
		chartpanel=Chart(ds);//创建折线图（自定义函数
		zhexianpanel.add(chartpanel);

	}

	public JPanel Chart(DefaultCategoryDataset ds) {
		JPanel panel = new JPanel();
		try {

			// 创建柱状图.标题,X坐标,Y坐标,数据集合,orientation,是否显示legend,是否显示tooltip,是否使用url链接
			String title = ds.getRowKey(0)+"";
			if (title.compareTo("Cn2")==0){
				title="log(Cn2)";
			}
			System.out.print(title);
			JFreeChart chart = ChartFactory.createLineChart("大气监测信息", "", title, ds, PlotOrientation.VERTICAL,
					true, true, true);
			chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  //legend图例中文乱码
			LegendTitle legend = chart.getLegend();
			legend.setPosition(RectangleEdge.TOP);//图例置于上方

			chart.setBackgroundPaint(null);
//			chart.setBackgroundPaint(Color.WHITE);

			// 配置字体（解决中文乱码的通用方法）
			Font xfont = new Font("仿宋", Font.BOLD, 12); // X轴
			Font yfont = new Font("黑体", Font.BOLD, 13); // Y轴
			Font titleFont = new Font("黑体", Font.BOLD, 14); // 图片标题
			CategoryPlot categoryPlot = chart.getCategoryPlot();
			categoryPlot.getDomainAxis().setLabelFont(xfont);
			categoryPlot.getRangeAxis().setLabelFont(yfont);
			chart.getTitle().setFont(titleFont);
			categoryPlot.setBackgroundPaint(Color.WHITE);
			// x轴 // 分类轴网格是否可见
			categoryPlot.setDomainGridlinesVisible(true);
			// y轴 //数据轴网格是否可见
			categoryPlot.setRangeGridlinesVisible(true);
			// 设置网格竖线颜色
			categoryPlot.setDomainGridlinePaint(Color.LIGHT_GRAY);
			// 设置网格横线颜色
			categoryPlot.setRangeGridlinePaint(Color.LIGHT_GRAY);
			// 没有数据时显示的文字说明
			categoryPlot.setNoDataMessage("没有数据显示");
			// 设置曲线图与xy轴的距离
			categoryPlot.setAxisOffset(new RectangleInsets(0d, 0d, 0d, 0d));
			// 设置面板字体
			Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
			// 取得Y轴
			NumberAxis rangeAxis = (NumberAxis) categoryPlot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			rangeAxis.setAutoRangeIncludesZero(true);
			rangeAxis.setUpperMargin(0.20);
//			rangeAxis.setLabelAngle(Math.PI / 2.0);

			// 取得X轴
			CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
			categoryAxis.setTickLabelsVisible(false);
//			//是否显示横坐标
//			categoryAxis.setVisible(true);
//			// 设置X轴坐标上的文字
//			categoryAxis.setTickLabelFont(labelFont);
//			// 设置X轴的标题文字
//			categoryAxis.setLabelFont(labelFont);
//			// 横轴上的 Lable 45度倾斜
//			categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			// 设置距离图片左端距离
			categoryAxis.setLowerMargin(0.0);
			// 设置距离图片右端距离
			categoryAxis.setUpperMargin(0.0);
			// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
			LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
			// 是否显示折点
			lineandshaperenderer.setBaseShapesVisible(false);
			// 是否显示折线
			lineandshaperenderer.setBaseLinesVisible(true);
			// series 点（即数据点）间有连线可见 显示折点数据
			lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			lineandshaperenderer.setBaseItemLabelsVisible(false);

			panel = new ChartPanel(chart, true);

			/*设置ChartPanel大小，但似乎用不上了，现在ChartPanel可以直接添加到JPanel
			Toolkit toolkit = Toolkit.getDefaultToolkit();
    	    Dimension scrnsize = toolkit.getScreenSize();
    	    int w = (int) (scrnsize.width*0.1);
    	    int h = (int) (scrnsize.height*0.1);
    	    panel.setDomainZoomable(true);
    	    panel.setPreferredSize(new Dimension(w, h));*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return panel;
	}
	public DefaultCategoryDataset setDefaultCategoryDataset() {
//		DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化一下时间
//		Date dNow = new Date(); //当前时间
//		Calendar calendar = Calendar.getInstance();//得到日历
//		Date dBefore = new Date();
//
//		Dao dao = new DaoImplDbPlay();// 创建数据库接口对象
//		List<Integer> list = dao.getChart();
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
//		int i=0;
//		for(i=0;i<10;i++) {
//			calendar.setTime(dNow);//把当前时间赋给日历
//			calendar.add(Calendar.DATE, -(10-i));
//			dBefore = calendar.getTime();
//			String defaultStartDate = dateFmt.format(dBefore); // 格式化前一天
//			defaultStartDate=defaultStartDate.substring(5, 10);
//			ds.addValue(list.get(i*2), "入场", defaultStartDate);
//			ds.addValue(list.get(i*2+1), "出场", defaultStartDate);
//		}
//		System.out.println(list2.size()+"sss");
//		for(AtmosphereDB a:list2) {
//			System.out.println(a.getAir_temperature());
//		}

		Object obj= list2.get(0);
		if(obj.getClass().equals(QdAod.class)){
			JPanel JpAOD400 = new JPanel();
			JPanel JpAOD500 = new JPanel();
			JPanel JpAOD675 = new JPanel();
			JPanel JpAOD870 = new JPanel();
			JPanel JpAOD940 = new JPanel();
			JPanel JpAOD1020 = new JPanel();
			JPanel JpAOD1627 = new JPanel();
			JPanel JpAOD2200 = new JPanel();
			JPanel JpPWV = new JPanel();
			JPanel JpAOD550 = new JPanel();
			JPanel JpArfa = new JPanel();
			JPanel JpBeta = new JPanel();
			tabbedPane.addTab("JpAOD400", null, JpAOD400, null);
			tabbedPane.addTab("JpAOD500", null, JpAOD500, null);
			tabbedPane.addTab("JpAOD675", null, JpAOD675, null);
			tabbedPane.addTab("JpAOD870", null, JpAOD870, null);
			tabbedPane.addTab("JpAOD940", null, JpAOD940, null);
			tabbedPane.addTab("JpAOD1020", null, JpAOD1020, null);
			tabbedPane.addTab("JpAOD1627", null, JpAOD1627, null);
			tabbedPane.addTab("JpAOD2200", null, JpAOD2200, null);
			tabbedPane.addTab("JpPWV", null, JpPWV, null);
			tabbedPane.addTab("JpAOD550", null, JpAOD550, null);
			tabbedPane.addTab("JpArfa", null, JpArfa, null);
			tabbedPane.addTab("JpBeta", null, JpBeta, null);
			JpAOD400.setLayout(new CardLayout(0, 0));
			JpAOD500.setLayout(new CardLayout(0, 0));
			JpAOD675.setLayout(new CardLayout(0, 0));
			JpAOD870.setLayout(new CardLayout(0, 0));
			JpAOD940.setLayout(new CardLayout(0, 0));
			JpAOD1020.setLayout(new CardLayout(0, 0));
			JpAOD1627.setLayout(new CardLayout(0, 0));
			JpAOD2200.setLayout(new CardLayout(0, 0));
			JpPWV.setLayout(new CardLayout(0, 0));
			JpAOD550.setLayout(new CardLayout(0, 0));
			JpArfa.setLayout(new CardLayout(0, 0));
			JpBeta.setLayout(new CardLayout(0, 0));
			for (Object t : list2) {
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds.addValue(Double.parseDouble(item.getAOD400()), "AOD400", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD500()), "AOD500", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD675()), "AOD675", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD870()), "AOD870", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD940()), "AOD940", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD1020()), "AOD1020", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD1627()), "AOD1627", ckey+"");
				ds.addValue(Double.parseDouble(item.getPWV()), "PWV", ckey+"");
				ds.addValue(Double.parseDouble(item.getAOD550()), "AOD550", ckey+"");
				ds.addValue(Double.parseDouble(item.getArfa()), "Arfa", ckey+"");
				ds.addValue(Double.parseDouble(item.getBeta()), "Beta", ckey+"");
			}
			//****************************************AOD400*******************************
			DefaultCategoryDataset ds_AOD400 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD400.addValue(Double.parseDouble(item.getAOD400()), "AOD400", ckey+"");
			}
			JPanel chartAOD400=Chart(ds_AOD400);//创建折线图
			JpAOD400.add(chartAOD400);
			//****************************************AOD500*******************************
			DefaultCategoryDataset ds_AOD500 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD500.addValue(Double.parseDouble(item.getAOD500()), "AOD500", ckey+"");
			}
			JPanel chartAOD500=Chart(ds_AOD500);//创建折线图
			JpAOD500.add(chartAOD500);
			//****************************************AOD675*******************************
			DefaultCategoryDataset ds_AOD675 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD675.addValue(Double.parseDouble(item.getAOD675()), "AOD675", ckey+"");
			}
			JPanel chartAOD675=Chart(ds_AOD675);//创建折线图
			JpAOD675.add(chartAOD675);
			//****************************************AOD870*******************************
			DefaultCategoryDataset ds_AOD870 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD870.addValue(Double.parseDouble(item.getAOD870()), "AOD870", ckey+"");
			}
			JPanel chartAOD870=Chart(ds_AOD870);//创建折线图
			JpAOD870.add(chartAOD870);
			//****************************************AOD940*******************************
			DefaultCategoryDataset ds_AOD940 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD940.addValue(Double.parseDouble(item.getAOD940()), "AOD940", ckey+"");
			}
			JPanel chartAOD940=Chart(ds_AOD940);//创建折线图
			JpAOD940.add(chartAOD940);
			//****************************************AOD1020*******************************
			DefaultCategoryDataset ds_AOD1020 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD1020.addValue(Double.parseDouble(item.getAOD1020()), "AOD1020", ckey+"");
			}
			JPanel chartAOD1020=Chart(ds_AOD1020);//创建折线图
			JpAOD1020.add(chartAOD1020);
			//****************************************AOD1627*******************************
			DefaultCategoryDataset ds_AOD1627 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD1627.addValue(Double.parseDouble(item.getAOD1627()), "AOD1627", ckey+"");
			}
			JPanel chartAOD1627=Chart(ds_AOD1627);//创建折线图
			JpAOD1627.add(chartAOD1627);
			//****************************************AOD2200*******************************
			DefaultCategoryDataset ds_AOD2200 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD2200.addValue(Double.parseDouble(item.getAOD2200()), "AOD2200", ckey+"");
			}
			JPanel chartAOD2200=Chart(ds_AOD2200);//创建折线图
			JpAOD2200.add(chartAOD2200);
			//****************************************PWV*******************************
			DefaultCategoryDataset ds_PWV = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_PWV.addValue(Double.parseDouble(item.getPWV()), "PWV", ckey+"");
			}
			JPanel chartPWV=Chart(ds_PWV);//创建折线图
			JpPWV.add(chartPWV);
			//****************************************AOD550*******************************
			DefaultCategoryDataset ds_AOD550 = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_AOD550.addValue(Double.parseDouble(item.getAOD550()), "AOD550", ckey+"");
			}
			JPanel chartAOD550=Chart(ds_AOD550);//创建折线图
			JpAOD550.add(chartAOD550);
			//****************************************Arfa*******************************
			DefaultCategoryDataset ds_Arfa = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_Arfa.addValue(Double.parseDouble(item.getArfa()), "Arfa", ckey+"");
			}
			JPanel chartArfa=Chart(ds_Arfa);//创建折线图
			JpArfa.add(chartArfa);
			//****************************************Beta*******************************
			DefaultCategoryDataset ds_Beta = new DefaultCategoryDataset();
			for(Object t :list2){
				QdAod item=(QdAod)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_Beta.addValue(Double.parseDouble(item.getBeta()), "Beta", ckey+"");
			}
			JPanel chartBeta=Chart(ds_Beta);//创建折线图
			JpBeta.add(chartBeta);
		}
		if(obj.getClass().equals(SsTurb.class)){
			for (Object t : list2) {
				SsTurb item=(SsTurb)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds.addValue(Math.log10(Double.parseDouble(item.getCn2())), "Cn2", ckey+"");
			}
		}
		if(obj.getClass().equals(SsVis.class)){
			for (Object t : list2) {
				SsVis item=(SsVis)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds.addValue(Double.parseDouble(item.getVis()), "vis", ckey+"");
			}
		}
		if(obj.getClass().equals(SsWs.class)){
			JPanel JpT = new JPanel();
			JPanel JpRH = new JPanel();
			JPanel JpP = new JPanel();
			JPanel JpWS = new JPanel();
			JPanel JpWD = new JPanel();
			tabbedPane.addTab("JpT", null, JpT, null);
			tabbedPane.addTab("JpRH", null, JpRH, null);
			tabbedPane.addTab("JpP", null, JpP, null);
			tabbedPane.addTab("JpWS", null, JpWS, null);
			tabbedPane.addTab("JpWD", null, JpWD, null);
			JpT.setLayout(new CardLayout(0, 0));
			JpRH.setLayout(new CardLayout(0, 0));
			JpP.setLayout(new CardLayout(0, 0));
			JpWS.setLayout(new CardLayout(0, 0));
			JpWD.setLayout(new CardLayout(0, 0));
			for (Object t : list2) {
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds.addValue(Double.parseDouble(item.getT()), "T", ckey+"");
				ds.addValue(Double.parseDouble(item.getRH()), "RH", ckey+"");
				ds.addValue(Double.parseDouble(item.getP()), "P", ckey+"");
				ds.addValue(Double.parseDouble(item.getWS()), "WS", ckey+"");
				ds.addValue(Double.parseDouble(item.getWD()), "WD", ckey+"");
			}
			//****************************************T********************************
			DefaultCategoryDataset ds_T = new DefaultCategoryDataset();
			for(Object t :list2){
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_T.addValue(Double.parseDouble(item.getT()), "T", ckey+"");
			}
			JPanel chartT=Chart(ds_T);//创建折线图
			JpT.add(chartT);
			//****************************************RH*******************************
			DefaultCategoryDataset ds_RH = new DefaultCategoryDataset();
			for(Object t :list2){
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_RH.addValue(Double.parseDouble(item.getRH()), "RH", ckey+"");
			}
			JPanel chartRH=Chart(ds_RH);//创建折线图
			JpRH.add(chartRH);
			//****************************************P********************************
			DefaultCategoryDataset ds_P = new DefaultCategoryDataset();
			for(Object t :list2){
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_P.addValue(Double.parseDouble(item.getP()), "P", ckey+"");
			}
			JPanel chartP=Chart(ds_P);//创建折线图
			JpP.add(chartP);
			//****************************************WS*******************************
			DefaultCategoryDataset ds_WS = new DefaultCategoryDataset();
			for(Object t :list2){
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_WS.addValue(Double.parseDouble(item.getWS()), "WS", ckey+"");
			}
			JPanel chartWS=Chart(ds_WS);//创建折线图
			JpWS.add(chartWS);
			//****************************************WD*******************************
			DefaultCategoryDataset ds_WD = new DefaultCategoryDataset();
			for(Object t :list2){
				SsWs item=(SsWs)t;
				String ckey=item.getDate()+" "+item.getTime();
				ds_WD.addValue(Double.parseDouble(item.getWD()), "WD", ckey+"");
			}
			JPanel chartWD=Chart(ds_WD);//创建折线图
			JpWD.add(chartWD);
		}
		return ds;
	}

}
