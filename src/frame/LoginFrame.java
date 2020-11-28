/*
 * 2020年2月12日
 * 创建登录面板，完成登录面板初步布局（后期可适度修饰），解决运行后需要点击才显示布局控件的问题，添加了窗口皮肤（jdk1.8才行）
 * 暂未完成按钮事件的监听（鼠标和回车），需数据库配合，等候主面板和数据库搭建
 */
package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.Connect;
import MD5.MD5;

import entity.AtmosphereDB;
import entity.User;


public class LoginFrame extends JFrame {
	/*
	 * 声明控件
	 */
	private JPanel loginPanel;// 登录面板
	private JButton loginBtn;// 登录按钮
	private JLabel usernameLabel;// 用户名标签
	private JLabel passwordLabel;// 密码标签
	private JTextField usernameField;// 用户名输入框
	private JPasswordField passwordField;// 密码框
	private Connect dbconnect=new Connect();

	/*
	 * 构造方法
	 */
	public LoginFrame() {
		initialize();
		LoginAction();
	}
	public void initialize() {
		/*
		 * 实例化
		 */
		loginPanel =new JPanel();//主容器面板
		loginBtn =new JButton("登录");// 登录按钮
		usernameLabel = new JLabel("账号");
		passwordLabel = new JLabel("密码");
		usernameField = new JTextField();
		passwordField = new JPasswordField();

		/*
		 * 设置窗体
		 */
		setTitle("登录");// 设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭后停止程序
		setSize(310, 210);// 窗体宽高
		Toolkit tool = Toolkit.getDefaultToolkit();// 创建系统该默认组件工具包
		Dimension d = tool.getScreenSize();// 获取屏幕尺寸，赋给一个二维坐标对象
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);// 让主窗体在屏幕中间显示

		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));// 祝主容器面板使用宽度和间距都为5像素的空边框
		setContentPane(loginPanel);// 将主容器面板载入到主容器中

		/*
		 * 布局设计
		 * loginPanel为最外层面板，使用边界布局
		 * centerPanel面板放置于loginPanel面板中部,网格布局2行1列
		 * southPanel面板放置于loginPanel面板南部，使用流布局
		 * aFloor/bFloor分别放置centerPanel第一行和第二行，使用流布局
		 * 账号和密码标签以及输入框，分别放置aFloor/bFloor
		 * 登录btn放置southPanel
		 */
		loginPanel.setLayout(new BorderLayout());// 主容器面板使用边界布局
		JPanel centerPanel = new JPanel();// 创建中部面板
		centerPanel.setLayout(new GridLayout(2, 1));// 使用2行1列网格布局的
		loginPanel.add(centerPanel,BorderLayout.CENTER);// 中部面板放到主容器面板中央位置

		FlowLayout centerLayout = new FlowLayout();// 创建流布局
		centerLayout.setHgap(10);// 布局中组件间隔10像素

		JPanel aFloor=new JPanel();//创建中部面板的第一行面板
		JPanel bFloor=new JPanel();//创建中部面板的第二行面板
		aFloor.setLayout(centerLayout);//设置布局为之前创建好的流布局
		bFloor.setLayout(centerLayout);//设置布局为之前创建好的流布局
		centerPanel.add(aFloor);//添加至中部面板
		centerPanel.add(bFloor);//添加至中部面板

		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);//标签居中
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//标签居中
		aFloor.add(usernameLabel);//将用户名标签添加至面板
		bFloor.add(passwordLabel);//将密码标签添加至面板
		usernameField.setColumns(20);// 用户名输入框长度为20个字符
		passwordField.setColumns(20);// 密码栏输入框长度为20个字符
		aFloor.add(usernameField);// 第一行面板添加用户名输入框
		bFloor.add(passwordField);// 第二行面板添加  密 码 输入框

		loginBtn.setPreferredSize(new Dimension(120,30));// 设置按钮大小
		JPanel southPanel = new JPanel(centerLayout);// 创建南部面板
		southPanel.add(loginBtn);//南部面板添加登录按钮
		loginPanel.add(southPanel, BorderLayout.SOUTH);// 将南部面板放入主容器面板南部


		/*
		 * 窗体可见设置于最后 不然控件显示需要改变窗体大小重绘时出现
		 */
		setVisible(true);// 设置窗体可见
	}


	public void LoginAction() {
		/*
		 * 登录按钮
		 */
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dao dao = new DaoImplDbPlay();// 创建数据库接口对象

				String name = usernameField.getText().trim();// 获取账号输入框中的内容，去掉两边空格
				String password = new String(passwordField.getPassword());// 获取密码框中的内容
				boolean flag=true;
				try {
					//password=MD5.md5(password, "Zz");
					flag=true;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "MD5转换失败请重试");
					flag=false;
				}
				if (flag) {
					User user= dbconnect.Login(name, password);// 将账号密码交给数据库进行判断
					if (null == user) {// 如果获得的用户是空的
						// 弹出对话框告诉用户账号密码不对
						JOptionPane.showMessageDialog(null, "您输入的账号密码不正确！");
					} else {// 如果存在此用户
						//MainFrame.setUser(user);// 将此用户设为当前操作用户
						atmospheredb atm=new atmospheredb();// 创建主窗体对象
						atm.setVisible(true);
						//System.out.println(user.getStatus());
						dispose();// 销毁本窗体
					} // else结束
				}
			}// actionPerformed()结束
		});
	}
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();// 启用BeautyEye主题
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LoginFrame();
	}
}
